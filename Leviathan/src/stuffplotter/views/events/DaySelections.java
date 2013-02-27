package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.views.util.DateSplitter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	private int dayOfMonth;
	
	/**
	 * Constructor for DaySelections, populates all time slots.
	 * @pre date != null;
	 * @post this.isVisible() == true;
	 * @param date - the date containing the day of the month to display.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	public DaySelections(Date date, List<Date> conflictDates)
	{
		super();
		this.initializeUI(date, conflictDates);
	}
	
	/**
	 * Constructor for DaySelections, populates all time slots based
	 * on the given Dates.
	 * @pre dates != null && !dates.isEmpty();
	 * @post this.isVisible() == true;
	 * @param dates - the list of time slots to display for the DaySelections panel.
	 * @param conflictDates - the list of time slots that are already taken. 
	 */
	public DaySelections(List<Date> dates, List<Date> conflictDates)
	{
		super();
		this.initializeUI(dates, conflictDates);		
	}
	
	/**
	 * Helper method to display all the time slots when given a single Date.
	 * @pre true;
	 * @post true;
	 * @param date - the Date containing the day to display all the time slots for.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	@SuppressWarnings("deprecation")
	private void initializeUI(Date date, List<Date> conflictDates)
	{
		DateSplitter splitter = new DateSplitter(date);
		this.dayOfMonth = splitter.getDay();
		HorizontalPanel topHolder = new HorizontalPanel();
		topHolder.add(new Label(splitter.getDayAsString()));
		Anchor close = new Anchor("X");
		close.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				removeFromParent();
			}
		});
		topHolder.add(close);
		this.add(topHolder);
		
		// for loop to add all the time slots
		for (int i = 0; i < timeIntervals.length; i++)
		{
			Date timeSlotDate = new Date(splitter.getYear() - CORRECTION_FACTOR_Y,
					 					 splitter.getMonth() - CORRECTION_FACTOR_M,
					 					 splitter.getDay(),
					 					 i,
					 					 0);
			
			TimeSlot slotToAdd = new TimeSlot(timeIntervals[i], timeSlotDate);
			
			if(conflictDates.contains(timeSlotDate))
			{
				slotToAdd.addStyleName("conflicting-time");
				slotToAdd.setTitle("You've already got stuff going on at this time.");
			}
			
			this.add(slotToAdd);
		}
	}
	
	/**
	 * Helper method to display all the time slots in the given list of Dates.
	 * @pre dates != null && !dates.isEmpty();
	 * @post true;
	 * @param dates - the list of dates containing the time slots to add.
	 * @param conflictDates - the list of time slots that are already taken. 
	 */
	private void initializeUI(List<Date> dates, List<Date> conflictDates)
	{
		if(!dates.isEmpty())
		{
			DateSplitter splitter = new DateSplitter(dates.get(0));
			this.dayOfMonth = splitter.getDay();
			this.add(new Label(splitter.getDayAsString()));
		}
		
		// for loop to add all the given time slots
		for(Date date : dates)
		{
			DateSplitter splitter = new DateSplitter(date);
			int hour = splitter.getHour();
			
			TimeSlot slotToAdd = new TimeSlot(timeIntervals[hour], date);
			
			if(conflictDates.contains(date))
			{
				slotToAdd.setStyleName("conflicting-time");
			}
			
			this.add(slotToAdd);
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
	public int getDay()
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
		 * @param timeSlotDate - the date represented by the TimeSlot.
		 */
		public TimeSlot(String timeInterval, Date timeSlotDate)
		{
			super(timeInterval);
			this.initializeVariables(timeSlotDate);
			this.setWordWrap(false);
		}
		
		/**
		 * Helper method to initialize the variables for the timeslot.
		 * @pre timeSlotDate != null;
		 * @post this.isVisible() == true;
		 * @param timeSlotDate - the date represented by the TimeSlot.		 
		 */
		private void initializeVariables(Date timeSlotDate)
		{
			this.timeSlot = timeSlotDate;
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
