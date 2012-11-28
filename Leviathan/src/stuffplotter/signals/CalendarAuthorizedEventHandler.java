package stuffplotter.signals;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for handling CalendarAuthorizedEvents.
 */
public interface CalendarAuthorizedEventHandler extends EventHandler
{
	/**
	 * Run code when CalendarAuthorizedEvent received.
	 * @pre true;
	 * @post true;
	 * @param event - the CalendarAuthorizedEvent.
	 */
	public void onCalendarAuthorized(CalendarAuthorizedEvent event);
}
