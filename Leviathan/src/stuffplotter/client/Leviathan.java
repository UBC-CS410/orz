package stuffplotter.client;

import stuffplotter.client.services.AccountService;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.AccountStatsService;
import stuffplotter.client.services.AccountStatsServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AppController;
import stuffplotter.server.AchievementChecker;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.AuthenticationException;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarAuthScope;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext.ListRequest;
import com.google.api.gwt.services.calendar.shared.model.CalendarList;
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	private final String url = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	private final AccountServiceAsync accountService = GWT.create(AccountService.class);
	private Account account = null;
	private AccountStatistic accountStatistic = null;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{		
		accountService.login(url, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	Window.alert("login failed");
	        }

	        public void onSuccess(Account result)
	        {	
	        	account = result;
	        	if (account.getUserFullName() == null)
	    		{
	        		loadUP();
	    		}
	    		else
	    		{
	    			final AccountStatsServiceAsync aStatService = GWT.create(AccountStatsService.class);	
	    			aStatService.getStats(result.getUserEmail(), new AsyncCallback<AccountStatistic>(){

						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("Fail to retrieve User Stats...");
							
						}

						@Override
						public void onSuccess(AccountStatistic result)
						{
							accountStatistic = result;
							accountStatistic.accept(new AchievementChecker());
							aStatService.save(accountStatistic, new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable arg0)
								{
									Window.alert("Fail to save stats");
									
								}

								@Override
								public void onSuccess(Void arg0)
								{
									// TODO Auto-generated method stub
									
								}
								
							});
						}
	    				
	    			});
	    			
		        	loadUI();
	    		}
	        }
		});
		
	}
	
	public void loadUP()
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
	    			accountService.loadProfile(account, token, new AsyncCallback<Void>()
	    			{

	    				@Override
	    				public void onFailure(Throwable caught)
	    				{
	    					if (caught instanceof AuthenticationException)
	    					{
	    						Window.alert("authentication error");
	    					}
	    				}

	    				@Override
	    				public void onSuccess(Void result)
	    				{
	    					Window.Location.assign(url);
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
	
	public void loadUI()
	{						
		/*	
		// test code to read from user's Google Calendar
		final com.google.api.gwt.services.calendar.shared.Calendar testCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
		testCalendar.initialize(new SimpleEventBus(), new GoogleApiRequestTransport("stuffplotter", "AIzaSyBfOXf0_XRFIMvIY6Noqbkvodamr-dSw_M"));
		OAuth2Login.get().authorize("933841708791.apps.googleusercontent.com", CalendarAuthScope.CALENDAR, new Callback<Void, Exception>()
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

		ServiceRepository applicationServices = new ServiceRepository();
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(applicationServices, eventBus, account);
		appViewer.go(RootPanel.get());
	}
}
