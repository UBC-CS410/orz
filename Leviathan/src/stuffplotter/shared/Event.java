package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.gwt.maps.client.geom.LatLng;
import com.googlecode.objectify.annotation.Entity;;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event implements Serializable
{	
	public enum Field
	{
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
	private LatLng eventCoordinates;
	
	private Date eventDate;
	private String eventDuration;
	private String eventCost;
	
	private enum eventState {CREATED, SCHEDULED};
	private enum eventLength {HOURS, DAYS};
	
	private List<MonthContainer> timeSheet;
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
	 * @pre eventOwner != null;
	 * @post this.eventOwner.equals(eventOwner);
	 * @param eventOwner - the owner of the event.
	 */
	public Event(String eventOwner)
	{
		this.eventOwner = eventOwner;
	}
	
	/**
	 * Method to retrieve the unique ID of an event.
	 * @pre true;
	 * @post true; 
	 * @return the ID of an event.
	 */
	public Long getEventId()
	{
		return this.eventId;
	}
	
	/**
	 * Retrieves the owner for the event.
	 * @pre true;
	 * @post true; 
	 * @return the owner of the event.
	 */
	public String getOwner()
	{
		return this.eventOwner;
	}

	/**
	 * Set the owner for the event.
	 * @pre eventOwner != null;
	 * @post this.eventOwner.equals(eventOwner);
	 * @param eventOwner the eventOwner to set
	 */
	public void setOwner(String eventOwner)
	{
		this.eventOwner = eventOwner;
	}

	/**
	 * Retrieve the name of the event.
	 * @pre true;
	 * @post true;
	 * @return the name of the event.
	 */
	public String getName()
	{
		return this.eventName;
	}

	/**
	 * Set the name of the event.
	 * @pre eventName != null;
	 * @post this.eventName.equals(eventName);
	 * @param eventName - the name of the event.
	 */
	public void setName(String eventName)
	{
		this.eventName = eventName;
	}
	
	/**
	 * Retrieves the address of the event.
	 * @pre true;
	 * @post true;
	 * @return the address of the event.
	 */
	public String getLocation()
	{
		return this.eventLocation;
	}

	/**
	 * Set the address of the event.
	 * @pre true;
	 * @post this.eventLocation.equals(eventLocation);
	 * @param eventLocation - the address of the event.
	 */
	public void setLocation(String eventLocation)
	{
		this.eventLocation = eventLocation;
	}

	/**
	 * Retrieves the coordinates for the event.
	 * @pre true;
	 * @post true;
	 * @return the coordinates of the event.
	 */
	public LatLng getCoordinates()
	{
		return this.eventCoordinates;
	}
	
	/**
	 * Set the coordinates for the event.
	 * @pre true;
	 * @post this.eventCoordinates.equals(coordinates);
	 * @param coordinates - the coordinates for the event.
	 */
	public void setCoordinates(LatLng coordinates)
	{
		this.eventCoordinates = coordinates;
	}
	
	/**
	 * Retrieves the date of the event.
	 * @pre true;
	 * @post true;
	 * @return the date of the event.
	 */
	public Date getDate()
	{
		return this.eventDate;
	}

	/**
	 * Set the date of the event.
	 * @pre true;
	 * @post this.eventDate.equals(eventDate);
	 * @param eventDate - the date of the event.
	 */
	public void setDate(Date eventDate)
	{
		this.eventDate = eventDate;
	}

	/**
	 * Retrieves the duration of the event.
	 * @pre true;
	 * @post true;
	 * @return the duration of the event.
	 */
	public String getDuration()
	{
		return this.eventDuration;
	}

	/**
	 * Set the duration of the event.
	 * @pre eventDuration >= 0;
	 * @post this.eventDuration == eventDuration;
	 * @param eventDuration - the duration of the event.
	 */
	public void setDuration(String eventDuration)
	{
		this.eventDuration = eventDuration;
	}

	/**
	 * Retrieves the cost of the event.
	 * @pre true;
	 * @post true;
	 * @return the eventCost
	 */
	public String getCost()
	{
		return this.eventCost;
	}

	/**
	 * Set the cost of the event.
	 * @pre eventCost >= 0;
	 * @post this.eventCost == eventCost;
	 * @param eventCost - the cost of the event.
	 */
	public void setCost(String eventCost)
	{
		this.eventCost = eventCost;
	}
	
	/**
	 * Retrieves the type of the event.
	 * @pre true;
	 * @post true;
	 * @return the type of the event.
	 */
	public String getType()
	{
		return this.eventType;
	}

	/**
	 * Set the type of the event.
	 * @pre eventType != null;
	 * @post this.eventType.equals(eventType);
	 * @param eventType - the type of the event.
	 */
	public void setType(String eventType)
	{
		this.eventType = eventType;
	}

	/**
	 * Retrieves the description of the event.
	 * @pre true;
	 * @post true;
	 * @return the description of the event.
	 */
	public String getDescription()
	{
		return this.eventDescription;
	}

	/**
	 * Set the description of the event.
	 * @pre true;
	 * @post this.eventDescription.equals(eventDescription);
	 * @param eventDescription - the description of the event.
	 */
	public void setDescription(String eventDescription)
	{
		this.eventDescription = eventDescription;
	}
	
	/**
	 * Retrieves the times suggested for an event.
	 * @pre true;
	 * @post true;
	 * @return the times suggested for an event.
	 */
	public List<MonthContainer> getTimeSheet()
	{
		return this.timeSheet;
	}
	
	/**
	 * Set the times suggested for an event.
	 * @pre true;
	 * @post this.timeSheet.equals(timeSheet);
	 * @param timeSheet - the times suggested for an event.
	 */
	public void setTimeSheet(List<MonthContainer> timeSheet)
	{
		this.timeSheet = timeSheet;
	}
	
	/**
	 * Retrieves the guests invited to the event.
	 * @pre true;
	 * @post true;
	 * @return the guests invited to the event.
	 */
	public List<String> getGuests()
	{
		return this.eventGuests;
	}
	
	/**
	 * Set the guests invited to the event.
	 * @pre true;
	 * @post this.eventGuests.equals(guestList);
	 * @param guestList - the list of guests for the event.
	 */
	public void setGuests(List<String> guestList)
	{
		this.eventGuests = guestList;
	}
}
