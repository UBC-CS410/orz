package stuffplotter.signals;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for handling RefreshPageEvents.
 */
public interface RefreshPageEventHandler extends EventHandler
{
	/**
	 * Run code when RefreshPageEvent received.
	 * @pre true;
	 * @post true;
	 * @param event - the RefreshPageEvent.
	 */
	public void onRefreshPage(RefreshPageEvent event);
}
