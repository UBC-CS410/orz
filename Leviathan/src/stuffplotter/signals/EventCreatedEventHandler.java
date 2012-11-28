package stuffplotter.signals;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for handling RefreshPageEvents.
 */
public interface EventCreatedEventHandler extends EventHandler
{
	/**
	 * Run code when EventCreatedEvent received.
	 * @pre true;
	 * @post true;
	 * @param event - the EventCreatedEvent.
	 */
	public void onEventCreated(EventCreatedEvent event);
}
