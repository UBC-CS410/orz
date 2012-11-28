package stuffplotter.client;

import java.util.List;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AppController;
import stuffplotter.server.AchievementChecker;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.InvalidAccessTokenException;
import stuffplotter.signals.AccountAuthorizedEvent;
import stuffplotter.signals.AccountAuthorizedEventHandler;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarAuthScope;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext.ListRequest;
import com.google.api.gwt.services.calendar.shared.model.CalendarList;
import com.google.api.gwt.services.calendar.shared.model.Event;
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	/**
	 * API Access
	 * To prevent abuse, Google places limits on API requests. Using a valid OAuth token or API key allows you to exceed anonymous limits by connecting requests back to your project.
	 * 
	 * Product name:	stuffplotter
	 * Google account:	allenylzhou@gmail.com
	 * 
	 * Client ID:			1024938108271.apps.googleusercontent.com
	 * Email address:		1024938108271@developer.gserviceaccount.com
	 * Client secret:		xNa3bfxI67U9rGJypDVcMZ34
	 * Redirect URIs:		http://127.0.0.1:8888/Leviathan.html?gwt.codesvr=127.0.0.1:9997
	 *						http://127.0.0.1:8888/oauth2callback
	 *						http://127.0.0.1:8888/leviathan/oauthWindow.html
	 *						http://stuffplotter.appspot.com/
	 *						http://stuffplotter.appspot.com/oauth2callback
	 *						http://stuffplotter.appspot.com/leviathan/oauthWindow.html
	 *						http://stuffplotter3.appspot.com/
	 *						http://stuffplotter3.appspot.com/oauth2callback
	 *						http://stuffplotter3.appspot.com/leviathan/oauthWindow.html
	 *						http://rmar3a05.appspot.com/
	 *						http://rmar3a05.appspot.com/oauth2callback
	 *						http://rmar3a05.appspot.com/leviathan/oauthWindow.html
	 * JavaScript origins:	http://127.0.0.1:8888/
	 * 						http://stuffplotter.appspot.com/
	 * 						http://stuffplotter3.appspot.com/
	 * 						http://rmar3a05.appspot.com/
	 */
	
	/**
	 * Key for browser apps (with referers)
	 * API key:	AIzaSyC5oA892h66JjK4MFqUM68ZMLSzuNwXSYk
	 * Referers:	Any referer allowed
	 * Activated on:	Nov 25, 2012 5:33 PM
	 * Activated by:	 allenylzhou@gmail.com – you
	 */
	private final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private final String REDIRECT_URI = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	private final String RESPONSE_TYPE = "token";
	private final String CLIENT_ID = "1024938108271.apps.googleusercontent.com"; // available from the APIs console
	private final String GOOGLE_PROFILE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	private final String GOOGLE_CALENDAR_SCOPE = "https://www.googleapis.com/auth/calendar";
	
	private Account applicationUser = null;
	private ServiceRepository applicationServices = new ServiceRepository();
	private HandlerManager eventBus = new HandlerManager(null);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{	
		this.eventBus.addHandler(AccountAuthorizedEvent.TYPE, new AccountAuthorizedEventHandler() 
		{
			@Override
			public void onAuthorizeAccount()
			{
        		applicationServices.getAccountService().storeUserinfo(new AsyncCallback<Void>()
        		{
        			
					@Override
					public void onFailure(Throwable caught)
					{
						if (caught instanceof InvalidAccessTokenException)
						{
							Window.alert("Invalid access token.");
				        	requestAccessToken();
						}
						else
						{
							Window.alert(caught.toString());
						}
					}

					@Override
					public void onSuccess(Void result)
					{
						//TODO: Revoke token when we are done with it.
						//https://accounts.google.com/o/oauth2/revoke?token={token}
						Window.Location.assign(REDIRECT_URI);
					}	
					
        		});	
			}
			
		});
		
		applicationServices.getAccountService().startSession(REDIRECT_URI, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	Window.alert(error.toString());
	        }

	        public void onSuccess(Account result)
	        {	
	        	applicationUser = result;
	        	
	        	if(Window.Location.getHref().length() > REDIRECT_URI.length())
	        	{
	        		String fragment = Window.Location.getHash();
	        		String token = fragment.substring(fragment.indexOf('=') + 1, fragment.indexOf('&'));
	        		applicationServices.getAccountService().storeAccessToken(token,  new AsyncCallback<Account>() {

						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert(caught.toString());
						}

						@Override
						public void onSuccess(Account result)
						{
							applicationUser = result;
							eventBus.fireEvent(new AccountAuthorizedEvent());	
						}	
					});
	        	}
	        	else if (applicationUser.getAccessToken() == null)
	        	{
	        		requestAccessToken();
	        	}
	        	else
	        	{
	        		startApplication();
	        	}
	        }
		});	
	}
	
	public void requestAccessToken()
	{
		String oauth2Request = AUTH_URL + "?";
		oauth2Request += "scope=" + GOOGLE_PROFILE_SCOPE + '+' + GOOGLE_CALENDAR_SCOPE + "&";
		oauth2Request += "redirect_uri=" + REDIRECT_URI + "&";
		oauth2Request += "response_type=" + RESPONSE_TYPE + "&";
		oauth2Request += "client_id=" + CLIENT_ID + "&";
		Window.Location.assign(oauth2Request);
		
		/*
		AuthRequest oauth2Request = new AuthRequest(AUTH_URL, CLIENT_ID)
	    .withScopes(GOOGLE_PROFILE_SCOPE, GOOGLE_CALENDAR_SCOPE); // Can specify multiple scopes here

		Auth.get().login(oauth2Request, new Callback<String, Throwable>() {
			
			@Override
			public void onSuccess(String token) {

			}
		  
			@Override
			public void onFailure(Throwable caught) {

			}
		});
		*/		
	}
	
	public void startApplication()
	{		
		//DO NOT REMOVE CODE BELOW, USED FOR TESTING GOOGLE CALENDARS		
		final com.google.api.gwt.services.calendar.shared.Calendar testCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
		testCalendar.initialize(new SimpleEventBus(), new GoogleApiRequestTransport("stuffplotter", "AIzaSyC5oA892h66JjK4MFqUM68ZMLSzuNwXSYk"));
		
		Button testButton1 = new Button("Authorize Calendar");
		testButton1.addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event)
			{
				OAuth2Login.get().authorize("1024938108271.apps.googleusercontent.com", CalendarAuthScope.CALENDAR, new Callback<Void, Exception>()
						{
							@Override
							public void onFailure(Exception reason)
							{
								// TODO Auto-generated method stub
								Window.alert(reason.getMessage());				
							}

							@Override
							public void onSuccess(Void result)
							{

							}	
						});	
			}		
		});
		
		Button testButton2 = new Button("Test Calendar");
		testButton2.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				Window.alert("Retrieving Google Calendar events...");
				testCalendar.calendarList().list().setMinAccessRole(MinAccessRole.OWNER).fire(new Receiver<CalendarList>()
				{
					@Override
					public void onSuccess(CalendarList response) 
					{
						String calendarID = response.getItems().get(0).getId();
						ListRequest calRequest = testCalendar.events().list(calendarID);
						calRequest.fire(new Receiver<Events>()
						{
							@Override
							public void onSuccess(Events response)
							{
								String result = "Events Found: ";
								List<Event> events = response.getItems();
								if(events != null)
								{
									for(Event event : events)
									{
										result += " " + event.getCreated();
									}
								}
								Window.alert(result);
							}
						});
					}
				});	
			}
			
		});
		RootPanel.get().add(testButton1);
		RootPanel.get().add(testButton2);
		//DO NOT REMOVE CODE ABOVE, USED FOR TESTING GOOGLE CALENDARS
		
		applicationServices.getStatsService().getStats(applicationUser.getUserEmail(), new AsyncCallback<AccountStatistic>()
		{

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Failed to retrieve user statistics...");
				
			}

			@Override
			public void onSuccess(AccountStatistic result)
			{
				AccountStatistic accountStats = result;
				accountStats.accept(new AchievementChecker());

			}	
		});	
		
		AppController appViewer = new AppController(applicationServices, eventBus, applicationUser);
		appViewer.go(RootPanel.get());

	}
}
