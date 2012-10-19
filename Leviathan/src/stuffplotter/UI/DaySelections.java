package stuffplotter.UI;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Class to display the selectable times for a given day.
 */
public class DaySelections extends HorizontalPanel
{
	/**
	 * Constructor for DaySelections.
	 * @param dayOfMonth - the day of the month to display.
	 */
	public DaySelections(String dayOfMonth)
	{
		this.add(new Label(dayOfMonth));
		this.add(new TimeSlot("9 - 10"));
	}
	
	/**
	 * Inner class for individual time slots in the DaySelections component.
	 */
	public class TimeSlot extends HorizontalPanel
	{
		private CheckBox checkBox;
		
		/**
		 * Constructor for a time interval in the DaySelections component.
		 * @param timeInterval - the time interval to display.
		 */
		public TimeSlot(String timeInterval)
		{
			this.checkBox = new CheckBox(timeInterval);
			this.add(checkBox);
		}
		
		/**
		 * Method to retrieve whether or not the time slot has been selected.
		 * @return
		 */
		public boolean IsSelected()
		{
			return true;
		}
	}
	
}
