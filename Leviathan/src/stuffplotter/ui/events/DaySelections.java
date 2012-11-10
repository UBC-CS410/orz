package stuffplotter.ui.events;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.shared.DayContainer;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the selectable times for a given day.
 */
public class DaySelections extends VerticalPanel
{
	private String dayOfMonth;
	
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
	 * @pre dayOfMonth != null;
	 * @post this.isVisible() == true;
	 * @param dayOfMonth - the day of the month to display.
	 */
	public DaySelections(String dayOfMonth)
	{
		super();
		this.dayOfMonth = dayOfMonth;
		this.add(new Label(dayOfMonth));
		for (int i = 0; i < timeIntervals.length; i++)
		{
			this.add(new TimeSlot(timeIntervals[i], i));
		}
	}
	
	/**
	 * Constructor for DaySelections, populates all time slots based
	 * on the given indices.
	 * @pre dayOfMonth != null && intervalIndexValues != null;
	 * @post this.isVisible() == true;
	 * @param dayOfMonth - the day of the month to display.
	 * @param intervalIndexValues - the list of intervals to display based on
	 * 								the their index value from the list
	 * 								timeIntervals.
	 */
	public DaySelections(String dayOfMonth, int[] intervalIndexValues)
	{
		super();
		this.dayOfMonth = dayOfMonth;
		this.add(new Label(dayOfMonth));
		for (int i = 0; i < intervalIndexValues.length; i++)
		{
			int intervalGiven = intervalIndexValues[i];
			this.add(new TimeSlot(timeIntervals[intervalGiven], intervalGiven));
		}		
	}
	
	/**
	 * Method to return the selected values, based on their indices from
	 * the list timeIntervals.
	 * @pre true;
	 * @post true;
	 * @return a Day containing the indices of the selected time intervals.
	 */
	public DayContainer retrieveSelectedValues()
	{
		List<Integer> selectedValues = new ArrayList<Integer>();
		
		// for each loop to find all the selected time intervals
		for (Widget timeSlot : this.getChildren())
		{
			if(timeSlot instanceof TimeSlot)
			{
				if(((TimeSlot) timeSlot).getValue())
				{
					selectedValues.add(((TimeSlot) timeSlot).getIndexValue());
				}
			}
		}
		
		return new DayContainer(this.dayOfMonth, selectedValues);
	}
	
	/**
	 * Inner class for individual time slots in the DaySelections component.
	 */
	public class TimeSlot extends CheckBox
	{
		private int intervalIndexValue;
		
		/**
		 * Constructor for a time interval in the DaySelections component.
		 * @pre timeInterval != null && indexValue != null &&
		 * indexValue >= 0 && indexValue < timeIntervals.length;
		 * @post this.isVisible() == true;
		 * @param timeInterval - the time interval to display.
		 */
		public TimeSlot(String timeInterval, int indexValue)
		{
			super(timeInterval);
			this.intervalIndexValue = indexValue;
			this.setWordWrap(false);
		}
		
		/**
		 * Method to retrieve the index value of the time interval selected.
		 * @pre true;
		 * @post true;
		 * @return the index value of the time interval selected.
		 */
		public int getIndexValue()
		{
			return intervalIndexValue;
		}
	}
	
	/**
	 * Method to get the day represented by the panel.
	 * @pre true;
	 * @post true;
	 * @return the day of the month the panel represents.
	 */
	public String getDay()
	{
		return this.dayOfMonth;
	}
}
