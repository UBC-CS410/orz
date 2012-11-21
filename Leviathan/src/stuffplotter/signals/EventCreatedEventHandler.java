package stuffplotter.signals;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for handling RefreshPageEvents.
 */
public interface EventCreatedEventHandler extends EventHandler
{
	public void onEventCreated(EventCreatedEvent event);
}
