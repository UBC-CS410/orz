package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import com.googlecode.objectify.annotation.Entity;;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event implements Serializable {
	
	@Id private Long eventId;
	private String eventOwner;
	private String eventName;
	
	private Date eventDate;
	private double eventCost;
	
	private List<String> eventHosts;
	private List<String> eventGuests;
	
	/**
	 * Event constructor.
	 */
	public Event()
	{
		// do nothing
	}
	
	/**
	 * Event constructor that sets basic information
	 * @param eventName - the eventName of the event.
	 * 
	 * @param participants - the participants for an event.
	 */
	public Event(String pOwner, String pName, Date pDate, double pCost)
	{
		this.eventOwner = pOwner;
		this.eventName = pName;
		this.eventDate = pDate;
		this.eventCost = pCost;
	}
	
	/**
	 * Method to retrieve the unique ID of an event.
	 * @pre true;
	 * @post true; 
	 * @return the ID of an event.
	 */
	public Long getEventId()
	{
		return eventId;
	}
	
	/**
	 * Method to retrieve the eventName of the event.
	 * @pre true;
	 * @post true;
	 * @return the eventName of the event.
	 */
	public String getEventName()
	{
		return this.eventName;
	}
	
}
