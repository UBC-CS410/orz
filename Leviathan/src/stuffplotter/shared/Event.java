package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Vector;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.gwt.maps.client.geom.LatLng;
import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event implements Serializable {
	
	public enum Status 
	{
		PROPOSED, 	// Scheduler is set up, invites can be sent
		SCHEDULED, 	// Date is finalized 
		FINISHED	// Event is completed and open to scoring
	};
	
	public enum Span 
	{
		HOURS, 		// Single day event
		DAYS		// Multiple day event
	};
	
	@Id private Long eventId;
	private String eventOwner;
	private String eventName;
	private String eventLocation;
	private Double[] eventCoordinates;
	
	private Date eventDate;
	private String eventDuration;
	private String eventCost;
	
	private Long eventScheduler;
	
	private Status eventStatus;
	private Span eventSpan;
	
	//private List<MonthContainer> timeSheet;
	private List<String> eventHosts;
	private List<String> eventGuests;
	
	private String eventType;
	private String eventDescription;
	private List<String> eventComments;
	
	private Double eventScore;
	
	
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
	public Long getId()
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
	public Double[] getCoordinates()
	{
		return this.eventCoordinates;
	}
	
	/**
	 * Set the coordinates for the event.
	 * @pre true;
	 * @post this.eventCoordinates.equals(coordinates);
	 * @param coordinates - the coordinates for the event.
	 */
	public void setCoordinates(Double[] coordinates)
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
	 * @pre true;
	 * @post true;
	 * @return the eventScheduler
	 */
	public Long getEventScheduler()
	{
		return eventScheduler;
	}

	/**
	 * @pre		eventScheduler is a valid data store id
	 * @post 	this.eventScheduler == eventScheduler
	 * @param 	eventScheduler 	the eventScheduler to set
	 */
	public void setEventScheduler(Long eventScheduler)
	{
		this.eventScheduler = eventScheduler;
	}

	/**
	 * Get the status of the event.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the eventStatus
	 */
	public Status getStatus()
	{
		return eventStatus;
	}

	/**
	 * Update the status of the event.
	 * @pre		true;
	 * @post	this.eventStatus == eventStatus
	 * @param 	eventStatus the eventStatus to set
	 */
	public void setStatus(Status eventStatus)
	{
		this.eventStatus = eventStatus;
	}
	
	/**
	 * Get the list of event hosts
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the eventHosts
	 */
	public List<String> getHosts()
	{
		return eventHosts;
	}

	/**
	 * Add a co-host to the event
	 * @pre		true;
	 * @post 	this.eventHosts.size() += 1;
	 * @param 	eventHosts the eventHosts to set
	 */
	public void addHost(String eventHost)
	{
		this.eventHosts.add(eventHost);
	}

	/**
	 * Get the list of guests invited to the event.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the guests invited to the event.
	 */
	public List<String> getGuests()
	{
		return this.eventGuests;
	}
	
	/**
	 * Invite a guest to the event.
	 * @pre 	true;
	 * @post 	this.eventGuests.size() += 1;
	 * @param 	guestList - the list of guests for the event.
	 */
	public void addGuest(String eventGuest)
	{
		this.eventGuests.add(eventGuest);
	}
	
	/**
	 * Invite a list of guests to the event
	 * @pre 	true;
	 * @post 	this.eventGuests.size() += eventGuests.size();
	 * @param 	guestList - the list of guests for the event.
	 */
	public void addGuests(List<String> eventGuests)
	{
		this.eventGuests.addAll(eventGuests);
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
	 * Get the list of comments for the event.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the eventComments
	 */
	public List<String> getComments()
	{
		return eventComments;
	}

	/**
	 * Add a comment to the event. 
	 * @pre		String != null && String != ""
	 * @post 	true
	 * @param 	eventComments 	an eventComment to add
	 */
	public void addComment(String eventComment)
	{
		this.eventComments.add(eventComment);
	}
}
