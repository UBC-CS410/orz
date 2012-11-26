package stuffplotter.client;

import java.util.List;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AppController;
import stuffplotter.server.AchievementChecker;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.GoogleAPIException;
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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	private final String url = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	
	private ServiceRepository applicationServices = new ServiceRepository();
	private HandlerManager eventBus = new HandlerManager(null);
	
	private Account account = null;
	
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
        		applicationServices.getAccountService().saveProfile(account, new AsyncCallback<Account>()
        		{

					@Override
					public void onFailure(Throwable caught)
					{
						if (caught instanceof GoogleAPIException)
						{
							Window.alert("Failed to retrieve Google profile...");
						}
					}

					@Override
					public void onSuccess(Account result)
					{
						account = result;
						startApplication();
					}	
        		});	
			}
			
		});
		
		applicationServices.getAccountService().load(url, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	Window.alert("Failed to load user account...");
	        }

	        public void onSuccess(Account result)
	        {	
	        	account = result;
	        	
	        	final DialogBox introductionDialogBox = new DialogBox();
	        	
	        	if (account.getAccessToken() == null)
	    		{
	        		Button startButton = new Button("Continue");
	        		startButton.addClickHandler(new ClickHandler()
	        		{

						@Override
						public void onClick(ClickEvent event)
						{
							authorizeAccount();
			        		introductionDialogBox.hide();
						}
	        			
	        		});
	        		
	        		introductionDialogBox.setTitle("stuffplotter");
	        		introductionDialogBox.setText("Welcome to stuffplotter.");
	        		
	        		introductionDialogBox.setPixelSize(320, 240);
	        		introductionDialogBox.center();
	        		introductionDialogBox.setGlassEnabled(true);
	        		introductionDialogBox.setAnimationEnabled(true);
	        		introductionDialogBox.setModal(true);
	        		//introductionDialogBox.setStyleName("introductionPopup");
	        		
	        		VerticalPanel contentPanel = new VerticalPanel();
	        		contentPanel.add(new Label("This is a social event management system dedicated to small social circles..."));
	        		contentPanel.add(startButton);
	        		
	        		introductionDialogBox.add(contentPanel);
	        		introductionDialogBox.show();
	        		
	        		//RootPanel.get().add(introductionDialogBox);
	    		}
	        	else
	        	{      	
	        		startApplication();
	        	} 	
	        }
		});	
	}
	
	public void authorizeAccount()
	{
		String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
		/**
		 * API Access
		 * To prevent abuse, Google places limits on API requests. Using a valid OAuth token or API key allows you to exceed anonymous limits by connecting requests back to your project.
		 * 
		 * Product name:	stuffplotter
		 * Google account:	allenylzhou@gmail.com
		 * 
		 * Client ID:	1024938108271.apps.googleusercontent.com
		 * Email address:	1024938108271@developer.gserviceaccount.com
		 * Client secret:	xNa3bfxI67U9rGJypDVcMZ34
		 * Redirect URIs:	http://127.0.0.1:8888/Leviathan.html?gwt.codesvr=127.0.0.1:9997
		 * 					stuffplotter.appspot.com
		 * JavaScript origins:	none
		 */
		String CLIENT_ID = "1024938108271.apps.googleusercontent.com"; // available from the APIs console
		String GOOGLE_PROFILE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
		String GOOGLE_CALENDAR_SCOPE = "https://www.googleapis.com/auth/calendar";
		
		AuthRequest oauth2Request = new AuthRequest(AUTH_URL, CLIENT_ID)
	    .withScopes(GOOGLE_PROFILE_SCOPE, GOOGLE_CALENDAR_SCOPE); // Can specify multiple scopes here

		Auth.get().login(oauth2Request, new Callback<String, Throwable>() {
			
			@Override
			public void onSuccess(String token) {
				applicationServices.getAccountService().authorize(token,  new AsyncCallback<Account>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Failed to authorize user account...");
					}

					@Override
					public void onSuccess(Account result)
					{
						account = result;
						eventBus.fireEvent(new AccountAuthorizedEvent());	
						System.out.println("authorized");
					}	
				});
			}
		  
			@Override
			public void onFailure(Throwable caught) {
				Window.Location.assign(url);
			}
		});
			
		/*
		String oauth2Request = AUTH_URL + "?";
		oauth2Request += "scope=" + GOOGLE_PROFILE_SCOPE + "&";
		oauth2Request += "redirect_uri=" + REDIRECT_URL + "&";
		oauth2Request += "response_type=token&";
		oauth2Request += "client_id=" + CLIENT_ID + "&";
		Window.Location.assign(oauth2Request);
		*/
	}
	
	public void startApplication()
	{		
		//DO NOT REMOVE THIS CODE, USED FOR TESTING GOOGLE CALENDARS
		/*
		Button button = new Button("Testing");
		button.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent ignore)
			{
				final com.google.api.gwt.services.calendar.shared.Calendar testCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
				testCalendar.initialize(new SimpleEventBus(), new GoogleApiRequestTransport("stuffplotter", "AIzaSyC5oA892h66JjK4MFqUM68ZMLSzuNwXSYk"));
				
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
		RootPanel.get().add(button);
		*/
		
		/*
		Window.alert("ASDF");
		System.out.println("ASDF");
		final com.google.api.gwt.services.calendar.shared.Calendar testCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
		testCalendar.initialize(new SimpleEventBus(), new GoogleApiRequestTransport("stuffplotter", "AIzaSyC5oA892h66JjK4MFqUM68ZMLSzuNwXSYk"));
		
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
		*/

		applicationServices.getStatsService().getStats(account.getUserEmail(), new AsyncCallback<AccountStatistic>(){

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
				applicationServices.getStatsService().save(accountStats, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Failed to save user statistics..");
						
					}

					@Override
					public void onSuccess(Void result)
					{
						// TODO Auto-generated method stub
					}
					
				});
			}	
		});	
		
		AppController appViewer = new AppController(applicationServices, eventBus, account);
		appViewer.go(RootPanel.get());

	}
}
