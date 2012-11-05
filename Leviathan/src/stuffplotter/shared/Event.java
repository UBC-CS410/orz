package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.googlecode.objectify.annotation.Entity;;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event implements Serializable {
	
	public enum Field {
		OWNER,
		NAME,
		LOCATION,
		DATE,
		DURATION,
		COST,
		TYPE,
		DESCRIPTION	
	};
	
	@Id private Long eventId;
	private String eventOwner;
	private String eventName;
	private String eventLocation;
	
	private Date eventDate;
	private int eventDuration;
	private double eventCost;
	
	private enum eventState {CREATED, SCHEDULED};
	private enum eventLength {HOURS, DAYS};
	
	private List<String> eventHosts;
	private List<String> eventGuests;
	
	private String eventType;
	private String eventDescription;
	
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
	public Event(String pOwner, String pName, String pLocation, Date pDate, double pCost)
	{
		this.setEventOwner(pOwner);
		this.setEventName(pName);
		this.setEventLocation(pLocation);
		this.setEventDate(pDate);
		this.setEventCost(pCost);
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
	 * @return the eventOwner
	 */
	public String getEventOwner() {
		return eventOwner;
	}

	/**
	 * @param eventOwner the eventOwner to set
	 */
	public void setEventOwner(String eventOwner) {
		this.eventOwner = eventOwner;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	/**
	 * @return the eventLocation
	 */
	public String getEventLocation() {
		return eventLocation;
	}

	/**
	 * @param eventLocation the eventLocation to set
	 */
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the eventLength
	 */
	public int getEventDuration() {
		return eventDuration;
	}

	/**
	 * @param eventLength the eventLength to set
	 */
	public void setEventDuration(int eventDuration) {
		this.eventDuration = eventDuration;
	}

	/**
	 * @return the eventCost
	 */
	public double getEventCost() {
		return eventCost;
	}

	/**
	 * @param eventCost the eventCost to set
	 */
	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}
	
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
}
