package stuffplotter.views.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.gwt.services.calendar.shared.model.Event;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Class to convert a given Event into Dates for TimeSlots.
 */
public class EventToDateConverter
{
	private static final int YEAR_CORRECTION = 1900;
	private static final int MONTH_CORRECTION = 1;
	private List<Date> timeSlotValues;
	private Date eventStartTime;
	private Date eventEndTime;
	
	/**
	 * Constructor for the EventToDateConverter.
	 * @pre googleCalendarEvent != null;
	 * @post true;
	 */
	public EventToDateConverter(Event googleCalendarEvent)
	{
		this.convertToTimeSlots(googleCalendarEvent);
	}
	
	/**
	 * Converts the given event into a list of Dates for comparing against TimeSlots.
	 * @pre googleCalendarEvent != null;
	 * @post true;
	 */
	@SuppressWarnings("deprecation")
	private void convertToTimeSlots(Event event)
	{
		DateTimeFormat rfcFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		DateSplitter startTime = new DateSplitter(rfcFormat.parse(event.getStart().getDateTime()));
		Date fullEndTime = rfcFormat.parse(event.getEnd().getDateTime());
		DateSplitter endTime = new DateSplitter(fullEndTime);
		this.eventStartTime = startTime.getDate();
		this.eventEndTime = endTime.getDate();
		
		this.timeSlotValues = new ArrayList<Date>();
		
		int startEndDifference = endTime.getHour() - startTime.getHour();
		
		if(startEndDifference == 0)
		{
			this.timeSlotValues.add(this.eventStartTime);
		}
		else
		{
			int endHour;
			
			Date endRoundedTime = new Date(endTime.getYear() - YEAR_CORRECTION,
										   endTime.getMonth() - MONTH_CORRECTION,
										   endTime.getDay(),
										   endTime.getHour(),
										   0);
			
			if(endRoundedTime.before(fullEndTime))
			{
				endHour = endTime.getHour();
			}
			else
			{
				endHour = endTime.getHour() - 1;
			}
			
			if(startTime.getHour() == endHour)
			{
				this.timeSlotValues.add(new Date(startTime.getYear() - YEAR_CORRECTION,
						 						 startTime.getMonth() - MONTH_CORRECTION,
						 						 startTime.getDay(),
						 						 startTime.getHour(),
						 						 0));
			}
			else
			{
				// for loop to generate all the time slots
				for(int i = startTime.getHour(); i < endHour + 1; i++)
				{
					this.timeSlotValues.add(new Date(startTime.getYear() - YEAR_CORRECTION,
													 startTime.getMonth() - MONTH_CORRECTION,
													 startTime.getDay(),
													 i,
													 0));
				}
			}
		}
	}
	
	/**
	 * Retrieve the time slots for the converted event.
	 * @pre true;
	 * @post true;
	 * @return the list of Dates that the event takes up.
	 */
	public List<Date> getTimeSlots()
	{
		return this.timeSlotValues;
	}
}
