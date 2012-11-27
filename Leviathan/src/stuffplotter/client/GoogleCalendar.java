package stuffplotter.client;

import com.google.api.gwt.client.GoogleApiRequestTransport;
import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;

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
}
