package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import stuffplotter.client.EventCreationPageRetriever;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event implements Serializable
{	
	public enum Status 
	{
		/**
		 * Scheduler is set up, invites can be sent.
		 */
		PROPOSED,
		
		/**
		 * Date for event is finalized.
		 */
		SCHEDULED,
		
		/**
		 * Event is completed and open to scoring.
		 */
		FINISHED
	};
	
	public enum Frame 
	{
		/**
		 * For single day event.
		 */
		HOURS,
		
		/**
		 * For a multiple day event.
		 */
		DAYS
	};
	
	@Id private Long eventId;
	/**
	 * Full name of the event owner.
	 */
	private String eventOwner;
	/**
	 * E-mail of the event owner (unique ID).
	 */
	private String eventOwnerID;
	private String eventName;
	private Date eventDate;
	private String eventLocation;
	private Double[] eventCoordinates;
	private String eventDescription;
	
	private List<String> eventInvitees;
	private List<String> eventAttendees;
	
	private Long eventScheduler;
	private Status eventStatus;
	private Frame eventFrame;
	
	private String eventDuration;	
		
	private String eventCost;
	private List<Long> eventComments;
	
	private List<Double> eventRatings;
	
	/**
	 * Event constructor.
	 */
	public Event()
	{
		this.eventInvitees = new ArrayList<String>();
		this.eventAttendees = new ArrayList<String>();
		this.eventComments = new ArrayList<Long>();
		this.eventRatings = new ArrayList<Double>();
	}
	
	/**
	 * Event constructor that sets information from EventCreationDialogBox UI.
	 * @pre eventRetriever != null;
	 * @post true;
	 * @param eventRetriever - the EventCreationPageRetriever containing the information
	 * 						   for the event.
	 */
	public Event(EventCreationPageRetriever eventRetriever)
	{
		this.eventOwner = eventRetriever.getOwner();
		this.eventOwnerID = eventRetriever.getOwnerID();
		this.eventName = eventRetriever.getName();
		this.eventDate = null;
		this.eventLocation = eventRetriever.getLocation();
		this.eventCoordinates = eventRetriever.getCoordinates();
		this.eventDuration = eventRetriever.getDuration();
		this.eventCost = eventRetriever.getCost();
		this.eventDescription = eventRetriever.getDescription();
		
		this.eventStatus = Status.PROPOSED;
		this.eventFrame = eventRetriever.getFrame();
		
		this.eventInvitees = new ArrayList<String>();
		this.eventAttendees = new ArrayList<String>();
		this.eventComments = new ArrayList<Long>();
		this.eventRatings = new ArrayList<Double>();
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
	 * Retrieves the owner (full name) for the event.
	 * @pre true;
	 * @post true; 
	 * @return the owner (full name) of the event.
	 */
	public String getOwner()
	{
		return this.eventOwner;
	}
	
	/**
	 * Retrieves the owner ID (email) for the event.
	 * @pre true;
	 * @post true; 
	 * @return the owner ID (email) of the event.
	 */
	public String getOwnerID()
	{
		return this.eventOwnerID;
	}

	/**
	 * Set the owner for the event.
	 * @pre eventOwner != null;
	 * @post this.eventOwner.equals(eventOwner);
	 * @param eventOwner - the owner (full name) for the event.
	 */
	public void setOwner(String eventOwner)
	{
		this.eventOwner = eventOwner;
	}

	/**
	 * Set the owner ID for the event.
	 * @pre eventOwnerID != null;
	 * @post this.eventOwnerID.equals(eventOwnerID);
	 * @param eventOwnerID - the owner (email address) for the event.
	 */
	public void setOwnerID(String eventOwnerID)
	{
		this.eventOwnerID = eventOwnerID;
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
	 * Retrieves the starting date of the event.
	 * @pre true;
	 * @post true;
	 * @return the date of the event.
	 */
	public Date getDate()
	{
		return this.eventDate;
	}

	/**
	 * Set the starting date of the event.
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
	 * Retrieve the time frame for the event.
	 * @pre true;
	 * @post true;
	 * @return the time frame for the event.
	 */
	public Frame getTimeFrame()
	{
		return this.eventFrame;
	}
	
	/**
	 * Sets the time frame for the event.
	 * @pre timeFrame != null;
	 * @post true;
	 * @param timeFrame - the time frame for the event.
	 */
	public void setTimeFrame(Frame timeFrame)
	{
		this.eventFrame = timeFrame;
	}
	
	/**
	 * Set the duration of the event.
	 * @pre eventDuration >= 0;
	 * @post this.eventDuration.equals(eventDuration);
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
	 * @return the cost of the event.
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
	 * Get the ID of the EventSchedular associated with the event.
	 * @pre true;
	 * @post true;
	 * @return the eventScheduler
	 */
	public Long getEventScheduler()
	{
		return this.eventScheduler;
	}

	/**
	 * Set the EventSchedular for the event.
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
	 * @return 	the eventStatus.
	 */
	public Status getStatus()
	{
		return this.eventStatus;
	}

	/**
	 * Update the status of the event.
	 * @pre		true;
	 * @post	this.eventStatus == eventStatus
	 * @param 	eventStatus - the status of the event.
	 */
	public void setStatus(Status eventStatus)
	{
		this.eventStatus = eventStatus;
	}

	/**
	 * Get the list of invitees to the event.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the users invited to the event.
	 */
	public List<String> getInvitees()
	{
		return this.eventInvitees;
	}
	
	/**
	 * Add a user to the list of invitees.
	 * @pre 	eventInvitee != null;
	 * @post 	this.eventInvitees.size() += 1;
	 * @param 	eventInvitee - user to invite to the event.
	 */
	public void addInvitee(String eventInvitee)
	{
		this.eventInvitees.add(eventInvitee);
	}
	
	/**
	 * Sets the initial list of invitees to the event
	 * @pre 	eventInvitees != null;
	 * @post 	this.eventInvitees.size() += eventInvitees.size();
	 * @param 	eventInvitee - the list of user to invite to the event.
	 */
	public void setInvitees(List<String> eventInvitees)
	{
		this.eventInvitees = eventInvitees;
	}
	
	/**
	 * Get the list of event hosts.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the users attending the event
	 */
	public List<String> getAttendees()
	{
		return this.eventAttendees;
	}

	/**
	 * Add an attendee to the event.
	 * @pre		eventAttendee != null;
	 * @post 	this.eventAttendees.size() += 1;
	 * @param 	eventAttendee - the user who accepted an invite to the event
	 */
	public void addAttendee(String eventAttendee)
	{
		this.eventAttendees.add(eventAttendee);
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
	 * Add a rating to the event.
	 * @pre rating >= 0;
	 * @post true;
	 * @param rating - the rating to add for the event.
	 */
	public void addRating(Double rating)
	{
		this.eventRatings.add(rating);
	}
	
	/**
	 * Get the list of comments for the event.
	 * @pre 	true;
	 * @post 	true;
	 * @return 	the eventComments
	 */
	public List<Long> getComments()
	{
		return this.eventComments;
	}

	/**
	 * Add a comment to the event. 
	 * @pre		String != null && String != ""
	 * @post 	true
	 * @param 	eventComments - an eventComment to add.
	 */
	public void addComment(Long eventComment)
	{
		this.eventComments.add(eventComment);
	}
	
	/**
	 * Serial version for the Event.
	 */
	private static final long serialVersionUID = 5333378383196411639L;

}
