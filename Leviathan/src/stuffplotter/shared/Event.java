package stuffplotter.shared;

import java.util.List;

import javax.persistence.Id;
import com.googlecode.objectify.annotation.Entity;;

/**
 * Class to hold all the information for an event.
 */
@Entity
public class Event {
	
	@Id private Long eventID;
	private String name;
	private List<String> participants;
	
	/**
	 * Emtpy constructor for the Event class.
	 */
	public Event()
	{
		// do nothing
	}
	
	/**
	 * Constructor for events.
	 * @param name - the name of the event.
	 * @param participants - the participants for an event.
	 */
	public Event(String name, List<String> participants)
	{
		this.name = name;
		this.participants = participants;
	}
	
	/**
	 * Method to retrieve the unique ID of an event.
	 * @pre true;
	 * @post true; 
	 * @return the ID of an event.
	 */
	public Long getID()
	{
		return eventID;
	}
	
	/**
	 * Method to retrieve the name of the event.
	 * @pre true;
	 * @post true;
	 * @return the name of the event.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Method to retrieve the list of participants of the event.
	 * @pre true;
	 * @post true;
	 * @return a list of participants.
	 */
	public List<String> getParticipants()
	{
		return this.participants;
	}
}
