package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.views.util.DateSplitter;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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
	 * Definition of Date object year is y - 1900 where y is the year.
	 */
	private static final int CORRECTION_FACTOR_Y = 1900;
	/**
	 * Months need to be zero indexed, so m - 1 where m is the month.
	 */
	private static final int CORRECTION_FACTOR_M = 1;
	private String dayOfMonth;
	
	/**
	 * Constructor for DaySelections, populates all time slots.
	 * @pre date != null;
	 * @post this.isVisible() == true;
	 * @param date - the date containing the day of the month to display.
	 */
	public DaySelections(Date date)
	{
		super();
		this.initializeUI(date);
	}
	
	/**
	 * Constructor for DaySelections, populates all time slots based
	 * on the given Dates.
	 * @pre dates != null && !dates.isEmpty();
	 * @post this.isVisible() == true;
	 * @param dates - the list of time slots to display for the DaySelections panel.
	 */
	public DaySelections(List<Date> dates)
	{
		super();
		this.initializeUI(dates);		
	}
	
	/**
	 * Helper method to display all the time slots when given a single Date.
	 * @pre true;
	 * @post true;
	 * @param date - the Date containing the day to display all the time slots for.
	 */
	private void initializeUI(Date date)
	{
		DateSplitter splitter = new DateSplitter(date);
		this.dayOfMonth = splitter.getDayAsString();
		this.add(new Label(this.dayOfMonth));
		
		// for loop to add all the time slots
		for (int i = 0; i < timeIntervals.length; i++)
		{
			this.add(new TimeSlot(timeIntervals[i],
								  splitter.getMonth(),
								  splitter.getDay(),
								  splitter.getYear(),
								  i));
		}
	}
	
	/**
	 * Helper method to display all the time slots in the given list of Dates.
	 * @pre dates != null && !dates.isEmpty();
	 * @post true;
	 * @param dates - the list of dates containing the time slots to add.
	 */
	private void initializeUI(List<Date> dates)
	{
		if(!dates.isEmpty())
		{
			DateSplitter splitter = new DateSplitter(dates.get(0));
			this.dayOfMonth = splitter.getDayAsString();
			this.add(new Label(dayOfMonth));
		}
		
		// for loop to add all the given time slots
		for(Date date : dates)
		{
			DateSplitter splitter = new DateSplitter(date);
			int hour = splitter.getHour();
			this.add(new TimeSlot(timeIntervals[hour],
								  splitter.getMonth(),
								  splitter.getDay(),
								  splitter.getYear(),
								  hour));
		}
	}
	
	/**
	 * Method to return the selected values, based on their indices from
	 * the list timeIntervals.
	 * @pre true;
	 * @post true;
	 * @return a Day containing the indices of the selected time intervals.
	 */
	public List<Date> retrieveSelectedValues()
	{
		List<Date> selectedValues = new ArrayList<Date>();
		
		// for each loop to find all the selected time intervals
		for (Widget timeSlot : this.getChildren())
		{
			if(timeSlot instanceof TimeSlot)
			{
				if(((TimeSlot) timeSlot).getValue())
				{
					selectedValues.add(((TimeSlot) timeSlot).getDate());
				}
			}
		}
		return selectedValues;
	}
	
	/**
	 * Retrieve the day represented by the DaySelection.
	 * @pre true;
	 * @post true;
	 * @return the day represented by the DaySelection.
	 */
	public String getDay()
	{
		return this.dayOfMonth;
	}
	
	/**
	 * Inner class for individual time slots in the DaySelections component.
	 */
	public class TimeSlot extends CheckBox
	{
		private Date timeSlot;
		
		/**
		 * Constructor for a time interval in the DaySelections component.
		 * @pre timeInterval != null && month >= 0 && month <= 11 && day is valid day for month
		 * 		&& year >= 1900 && hour >= 0 && hour <= 23;
		 * @post this.isVisible() == true;
		 * @param timeInterval - the time interval to display for the time slot.
		 * @param month - the month the timeslot belongs to.
		 * @param day - the day the timeslot belongs to.
		 * @param year - the year the timeslot belongs to.
		 * @param hour - the starting hour the timeslot represents in 24 hour, base 0, time.
		 */
		public TimeSlot(String timeInterval, int month, int day, int year, int hour)
		{
			super(timeInterval);
			this.initializeVariables(month, day, year, hour);
			this.setWordWrap(false);
		}
		
		/**
		 * Helper method to initialize the variables for the timeslot.
		 * @pre timeInterval != null && month >= 0 && month <= 11 && day is valid day for month
		 * 		&& year >= 1900 && hour >= 0 && hour <= 23;
		 * @post this.isVisible() == true;
		 * @param timeInterval - the time interval to display for the time slot.
		 * @param month - the month the timeslot belongs to.
		 * @param day - the day the timeslot belongs to.
		 * @param year - the year the timeslot belongs to.
		 * @param hour - the starting hour the timeslot represents in 24 hour, base 0, time.		 */
		@SuppressWarnings("deprecation")
		private void initializeVariables(int month, int day, int year, int hour)
		{
			this.timeSlot = new Date(year - CORRECTION_FACTOR_Y,
									 month - CORRECTION_FACTOR_M,
									 day,
									 hour,
									 0);
		}
		
		/**
		 * Method to retrieve the Date the time interval represents.
		 * @pre true;
		 * @post true;
		 * @return the Date the time interval represents.
		 */
		public Date getDate()
		{
			return this.timeSlot;
		}
	}
}
