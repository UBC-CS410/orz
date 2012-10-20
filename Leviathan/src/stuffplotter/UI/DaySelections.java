package stuffplotter.UI;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the selectable times for a given day.
 */
public class DaySelections extends VerticalPanel
{
	private final String[] timeIntervals = new String[]
	{
		"12AM - 1AM", "1AM - 2AM", "2AM - 3AM",	"3AM - 4AM", "4AM - 5AM",
		"5AM - 6AM", "6AM - 7AM", "7AM - 8AM", "8AM - 9AM", "9AM - 10AM",
		"10AM - 11AM", "11AM - 12PM", "12PM - 1PM", "1PM - 2PM", "2PM - 3PM",
		"3PM - 4PM", "4PM - 5PM", "5PM - 6PM", "6PM - 7PM", "7PM - 8PM",
		"8PM - 9PM", "9PM - 10PM", "10PM - 11PM", "11PM - 12AM"
	};

	/**
	 * Constructor for DaySelections, populates all time slots.
	 * @param dayOfMonth - the day of the month to display.
	 */
	public DaySelections(String dayOfMonth)
	{
		this.add(new Label(dayOfMonth));
		for (int i = 0; i < timeIntervals.length; i++)
		{
			this.add(new CheckBox(timeIntervals[i]));
		}
	}
	
	/**
	 * Constructor for DaySelections, populates all time slots based
	 * on the given indices.
	 * @param dayOfMonth - the day of the month to display.
	 * @param intervalIndexValues - the list of intervals to display based on
	 * 								the their index value from the list
	 * 								timeIntervals.
	 */
	public DaySelections(String dayOfMonth, int[] intervalIndexValues)
	{
		this.add(new Label(dayOfMonth));
		for (int i = 0; i < intervalIndexValues.length; i++)
		{
			this.add(new CheckBox(timeIntervals[intervalIndexValues[i]]));
		}		
	}
	
	/**
	 * Inner class for individual time slots in the DaySelections component.
	 */
	public class TimeSlot extends HorizontalPanel
	{
		private CheckBox checkBox;
		private int intervalIndexValue;
		
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
