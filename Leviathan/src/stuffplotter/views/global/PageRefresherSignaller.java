package stuffplotter.views.global;

import stuffplotter.signals.RefreshPageEvent;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;

/**
 * Class to fire a PageRefreshEvent at a given set interval.
 */
public class PageRefresherSignaller
{
	/**
	 * The time interval (in ms), that the RefreshPageEvent is fired.
	 */
	private int fireInterval;
	private HandlerManager eventBus;
	private Timer refresherUnit;
	
	/**
	 * Constructor for the PageRefresherSignaller.
	 * @pre eventBus != null && fireInterval > 0;
	 * @post true;
	 * @param eventBus - the event bus for the application.
	 * @param fireInterval - the interval to fire the fresh event (in ms).
	 */
	public PageRefresherSignaller(HandlerManager eventBus, int fireInterval)
	{
		this.fireInterval = fireInterval;
		this.eventBus = eventBus;
		this.initializeTimer();
	}
	
	/**
	 * Helper method to initialize the variables, including the timer for the refresh event.
	 * @pre true;
	 * @post true;
	 */
	private void initializeTimer()
	{
		this.refresherUnit = new Timer()
		{
			@Override
			public void run()
			{
				eventBus.fireEvent(new RefreshPageEvent());
			}
		}; 
	}
	
	/**
	 * Starts the periodic firing of the RefreshPageEvent.
	 * @pre true;
	 * @post true;
	 */
	public void startPeriodicRefreshing()
	{
		this.refresherUnit.schedule(this.fireInterval);
		this.refresherUnit.scheduleRepeating(this.fireInterval);
	}
	
	/**
	 * Stops the periodic firing of the RefreshPageEvent.
	 * @pre true;
	 * @post true;
	 */
	public void stopPeriodicRefreshing()
	{
		this.refresherUnit.cancel();
	}
	
	/**
	 * Returns the fire interval for the RefreshPageEvent.
	 * @pre true;
	 * @post true;
	 */
	public int getFireInterval()
	{
		return this.fireInterval;
	}
	
	/**
	 * Set the new firing rate for the RefreshPageEvent.
	 * @pre true;
	 * @post true;
	 */
	public void setFireInterval(int fireInterval)
	{
		this.refresherUnit.scheduleRepeating(fireInterval);
		this.fireInterval = fireInterval;
	}
}
