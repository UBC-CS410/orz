package stuffplotter.client;

import java.util.Date;
import java.util.List;

import stuffplotter.ui.events.EventCreationDialogBox;
import stuffplotter.ui.TopRightPanel;
import stuffplotter.ui.AccountPanel;
import stuffplotter.ui.events.AvailabilitySubmitterDialogBox;
import stuffplotter.ui.FriendFinderDialogBox;
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
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
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
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy");
				String[] calendarValues = dayFormat.format(event.getTarget()).toString().split(",");
				Window.alert(calendarValues[1]);
			}
		});
						
		final Button createEventBtn = new Button("Create Event");
		createEventBtn.addClickHandler(new ClickHandler()
		{
			EventCreationDialogBox eventCreation = null;
			@Override
			public void onClick(ClickEvent event)
			{
				 eventCreation = new EventCreationDialogBox(account);
			}
		});
		
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
		
		// testing top right panel for logged in user
		TopRightPanel topRightPanel = new TopRightPanel(account);
		
		HorizontalPanel calMapHolder = new HorizontalPanel();
		calMapHolder.add(calendar);
		
		RootPanel.get("calMapContainter").add(calMapHolder);
		RootPanel.get("eventCreation").add(createEventBtn);
		RootPanel.get("topRightPanel").add(topRightPanel);
	}
}
