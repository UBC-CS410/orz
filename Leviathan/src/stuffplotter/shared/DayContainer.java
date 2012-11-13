package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold the time intervals selected for an event.
 */
public class DayContainer implements Serializable
{
	private String day;
	private List<Integer> timeSlots;
	
	/**
	 * No arg constructor for DayContainer
	 * @pre		true;
	 * @post 	true;
	 */
	public DayContainer() {
		
	}
	
	/**
	 * Constructor for a Day, containing the time slots selected for an event.
	 * @pre day != null && timeSlots != null;
	 * @post this.day.equals(day) && this.timeSlots.equals(timeSlots); 
	 */
	public DayContainer(String day, List<Integer> timeSlots)
	{
		this.day = day;
		this.timeSlots = timeSlots;
	}
	
	/**
	 * Retrieves the day.
	 * @pre true;
	 * @post true;
	 */
	public String getDay()
	{
		return this.day;
	}
	
	/**
	 * Retrieves the time slots for a given day.
	 * @pre true;
	 * @post true;
	 * @return the time slots selected for the day.
	 */
	public List<Integer> getTimeSlots()
	{
		return this.timeSlots;
	}
}
