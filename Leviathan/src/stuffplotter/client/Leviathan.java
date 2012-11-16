package stuffplotter.client;

import java.util.Date;
import java.util.List;

import stuffplotter.views.ApplicationPagingSimulator;
import stuffplotter.views.ApplicationPagingSimulator.View;
import stuffplotter.views.account.AccountPanel;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;
import stuffplotter.views.events.EventCreationDialogBox;
import stuffplotter.views.friends.FriendFinderDialogBox;
import stuffplotter.views.global.MenuBarPanel;

import stuffplotter.views.global.TopRightPanel;
import stuffplotter.client.services.AccountService;
import stuffplotter.client.services.AccountServiceAsync;

import stuffplotter.server.AchievementChecker;
import stuffplotter.server.AchievementRecordUpdater;
import stuffplotter.shared.Account;

import com.google.api.gwt.client.OAuth2Login;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	private String url = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	private Account account = null;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		final AccountServiceAsync accountService = GWT.create(AccountService.class);			
		accountService.login(url, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	Window.alert("login failed again");
	        }

	        public void onSuccess(Account result)
	        {	        	
	    		if (Window.Location.getHash().length() > 0)
	    		{
	    			accountService.loadProfile(result, Window.Location.getHash(), new AsyncCallback<Void>()
	    			{

	    				@Override
	    				public void onFailure(Throwable caught)
	    				{
	    					// TODO Auto-generated method stub
	    					
	    				}

	    				@Override
	    				public void onSuccess(Void result)
	    				{
	    					Window.Location.assign(url);
	    				}
	    		
	    			});
	    		}
	    		else if (!result.getUserAccessPermission())
	    		{
	        		requestProfile();
	    		}
	    		else
	    		{
	    			account = result;
	    			account.accept(new AchievementRecordUpdater().incrementLogin());
	    			account.accept(new AchievementChecker());
	    			accountService.saveAccount(account, new AsyncCallback<Void>()
	    			{
						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("save failed");
							
						}
		
						@Override
						public void onSuccess(Void result)
						{
							// TODO Auto-generated method stub
							
						}
		        	  
		        	});
		        	loadUI();
	    		}
	        }
		});
		
	}
	
	public void requestProfile()
	{
		String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
		String REDIRECT_URL = this.url;
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
		
		String oauth2Request = AUTH_URL + "?";
		oauth2Request += "scope=" + GOOGLE_PROFILE_SCOPE + "&";
		oauth2Request += "redirect_uri=" + REDIRECT_URL + "&";
		oauth2Request += "response_type=token&";
		oauth2Request += "client_id=" + CLIENT_ID + "&";
		Window.Location.assign(oauth2Request);
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
		
		// testing view selection and simulated pages
		MenuBarPanel viewSelections = new MenuBarPanel();
		final ApplicationPagingSimulator simulatedPages = new ApplicationPagingSimulator();
		viewSelections.getHomeBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				simulatedPages.showView(View.HOME);
			}	
		});
		viewSelections.getAccountBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				simulatedPages.showView(View.ACCOUNT);
			}	
		});
		viewSelections.getEventsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				simulatedPages.showView(View.EVENTS);
			}	
		});
		viewSelections.getFriendsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				simulatedPages.showView(View.FRIENDS);
			}	
		});
		viewSelections.getAchievementsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				simulatedPages.showView(View.ACHIEVEMENTS);
			}	
		});
		
		// testing top right panel for logged in user
		TopRightPanel topRightPanel = new TopRightPanel(account);

		RootPanel.get("topRightPanel").add(topRightPanel);
		RootPanel.get("viewSelections").add(viewSelections);
		RootPanel.get("simulatedPages").add(simulatedPages);
	}
}
