package stuffplotter.client;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarsContext;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext;
import com.google.api.gwt.services.calendar.shared.model.CalendarListEntry;
import com.google.api.gwt.services.calendar.shared.model.Event;
import com.google.api.gwt.services.calendar.shared.model.EventDateTime;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Wrapper class to use the Google Calendar API.
 */
public class GoogleCalendar
{
	private static final String API_KEY = "AIzaSyC5oA892h66JjK4MFqUM68ZMLSzuNwXSYk";
	public static final String CLIENT_ID = "1024938108271.apps.googleusercontent.com";
	private static final String APP_NAME = "stuffplotter";
	boolean done;
	Calendar googleCalendar;
	
	/**
	 * Constructor for the GoogleCalendar wrapper class.
	 * Note: this object should only be created in a onClickHandler method call on the
	 * 		 client side.
	 * @pre true;
	 * @post true;
	 */
	public GoogleCalendar()
	{
		this.initializeCalendar();
	}

	/**
	 * Helper method to initialize the GoogleCalendar.
	 * @pre true;
	 * @post true;
	 */
	private void initializeCalendar()
	{
		this.googleCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
		this.googleCalendar.initialize(new SimpleEventBus(), new GoogleApiRequestTransport(APP_NAME, API_KEY));
	}
	
	/**
	 * Return the Google Calendar object to make calls with.
	 * @pre true;
	 * @post true;
	 * @return true;
	 */
	public Calendar getCalendar()
	{
		return this.googleCalendar;
	}
	
	/**
	 * Adds a stuffplotter calendar to the user's Google calendar.
	 * @pre true;
	 * @post true;
	 */
	public void addCalendar()
	{
		CalendarsContext calendarsCtx = this.googleCalendar.calendars();
		com.google.api.gwt.services.calendar.shared.model.Calendar calendar = 
				calendarsCtx.create(com.google.api.gwt.services.calendar.shared.model.Calendar.class)
					.setSummary("stuffplotter");

		
		calendarsCtx.insert(calendar).fire(new Receiver<com.google.api.gwt.services.calendar.shared.model.Calendar>(){

			@Override
			public void onSuccess(
					com.google.api.gwt.services.calendar.shared.model.Calendar inserted)
			{
				Window.alert(inserted.getId());
			}

		});
	}
	
	/**
	 * Adds an event to a calendar
	 * @pre true;
	 * @post true;
	 * @param eventName - name of the event
	 * @param calendarId - id of the calendar
	 */
	public void addEvent(String eventName, String calendarId)
	{
		//DATES MUST BE IN THIS FORMAT
		String summary = "New Event";
		String startDate = "2012-011-28T14:30:00.000-07:00";
		String endDate = "2012-011-28T15:00:00.000-07:00";
		
		EventsContext eventsCtx = this.googleCalendar.events();
	    Event event = eventsCtx.create(Event.class)
	        .setSummary(summary)
	        .setStart(eventsCtx.create(EventDateTime.class).setDateTime(startDate))
	        .setEnd(eventsCtx.create(EventDateTime.class).setDateTime(endDate));

	    // Note that the EventsContext used to insert the Event has to be the same one used to create
	    // it.
	    eventsCtx.insert(calendarId, event).fire(new Receiver<Event>()
	    {

			@Override
			public void onSuccess(Event inserted)
			{
				Window.alert(inserted.getSummary());
			}
	    	
	    });
	}
	
}
