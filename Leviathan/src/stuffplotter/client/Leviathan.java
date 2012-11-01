package stuffplotter.client;

import java.util.Date;
import java.util.List;

import stuffplotter.UI.AccountPanel;
import stuffplotter.UI.AvailabilitySubmitterDialogBox;
import stuffplotter.UI.EventCreationDialogBox;
import stuffplotter.UI.EventLocationSearchPanel;
import stuffplotter.UI.FriendFinderDialogBox;
import stuffplotter.UI.TopRightPanel;
import stuffplotter.shared.Account;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.client.OAuth2Login;
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
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	
	private final String redirectUrl = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	private Account account = null;
	private int friendCount = 0;
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		
		AccountServiceAsync accountService = GWT.create(AccountService.class);
		
		accountService.login(redirectUrl, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	
	        }

	        public void onSuccess(Account result)
	        {
	          account = result;
	          if(account.inSession())
	          {
	        	  loadUI();
	          }
	          else
	          {
	        	  Window.Location.assign(account.getLogin());
	          }
	        }
		});
	}
	
	public void loadUI()
	{
		// testing calendar stuff
		Calendar calendar = new Calendar();
		calendar.setWidth("500px");
		calendar.setHeight("400px");

		//Displays the month View
		calendar.setView(CalendarViews.MONTH);
		calendar.addTimeBlockClickHandler(new TimeBlockClickHandler<Date>()
		{
			@Override
			public void onTimeBlockClick(TimeBlockClickEvent<Date> event)
			{
				// TODO Auto-generated method stub
				Window.alert(Integer.toString(event.getTarget().getDate()));
			}
		});
		
		// testing map stuff
		MapWidget map = new MapWidget(LatLng.newInstance(49, -123), 12);
		map.setSize("500px", "500px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl3D());
		
		// testing search panel for map
		EventLocationSearchPanel eventLocationPanel = new EventLocationSearchPanel();
		eventLocationPanel.hide();
		eventLocationPanel.setPopupPosition(map.getAbsoluteLeft(), 100);
		eventLocationPanel.show();
		
		// testing Toy Level System
		final LevelSystem lvlSys = new LevelSystem();

		VerticalPanel lvlView = new VerticalPanel();
		final Button addExpBtn = new Button("Up Vote");

		final Label lvlLabel = new Label("Level: 1");
		final Label expLabel = new Label("Experience: 0");
		
		lvlView.add(lvlLabel);
		lvlView.add(expLabel);
		lvlView.add(addExpBtn);

	
		
		
		
		addExpBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				lvlSys.addExperience(50);
				expLabel.setText("Experience: " + Integer.toString(lvlSys.getCurrentExperience()));
				lvlLabel.setText("Level: " + Integer.toString(lvlSys.getCurrentLevel()));
			}	
		});
		
		final Button availBtn = new Button("Avail");
		availBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				AvailabilitySubmitterDialogBox availSubmitter = new AvailabilitySubmitterDialogBox();
				availSubmitter.show();
			}
		});
		
		final Button createEventBtn = new Button("Create Event");
		createEventBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				EventCreationDialogBox eventCreation = new EventCreationDialogBox();
			}
		});

		final Button findFriends = new Button("Find Friends");
		
		
		//Testing for adding friends and accepting
		final TextBox testInput = new TextBox();
		final Button addFriends = new Button("Add Friends");
		
		addFriends.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event) {
				
				
				AccountServiceAsync accountService = GWT.create(AccountService.class);
				accountService.addFriend(account, testInput.getText(), new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failure");
						
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Friend Request to "+testInput.getText()+" has been added.");
						
					}
					
				});
				
			}
			
		}
		);
		
		final Button getPending = new Button("See Requests");
		getPending.addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event) {
				
				
				AccountServiceAsync accountService = GWT.create(AccountService.class);
				accountService.getPendingFriends(account, new  AsyncCallback<List<String>>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Failure");
						
					}

					@Override
					public void onSuccess(List<String> result) {
						String pending = "";
						for(String user : result){
							pending = pending + user + "\n";
						}
						
						Window.alert(pending);
						
					}
					
				});
				
				
				
			}
			
		}
		);
		
		
		//END OF TEST
		

		findFriends.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				FriendFinderDialogBox friendDialog = new FriendFinderDialogBox();
				friendDialog.show();
				
				
			}	
		});
		
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
		
		// testing user account panel
		AccountPanel userAccountPanel = new AccountPanel(account);
		
		// testing top right panel for logged in user
		TopRightPanel topRightPanel = new TopRightPanel(account);
		
		HorizontalPanel calMapHolder = new HorizontalPanel();
		calMapHolder.add(calendar);
		calMapHolder.add(map);
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("calMapContainter").add(calMapHolder);
		RootPanel.get("addExp").add(lvlView);
		RootPanel.get("eventCreation").add(createEventBtn);
		RootPanel.get("availSub").add(availBtn);
		RootPanel.get("friendFinder").add(findFriends);
		RootPanel.get("friendFinder").add(testInput);
		RootPanel.get("friendFinder").add(addFriends);
		RootPanel.get("friendFinder").add(getPending);
		RootPanel.get("userAccount").add(userAccountPanel);
		RootPanel.get("topRightPanel").add(topRightPanel);
	}
}
