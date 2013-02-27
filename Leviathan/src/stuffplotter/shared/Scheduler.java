package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold information about the proposed dates and finalized dates
 * pertaining to an Event
 */
@SuppressWarnings("serial")
@Entity
public class Scheduler implements Serializable
{	
	@Id private Long id;
	private List<Long> availabilities;
	private List<String> submitters;
	
	/**
	 * No arg Constructor for Scheduler
	 * 
	 * @pre		true
	 * @post 	true
	 * 
	 */
	public Scheduler()
	{
		this.availabilities = new ArrayList<Long>();
		this.submitters = new ArrayList<String>();
	}
	
	/**
	 * Method to get the id of the scheduler
	 * @pre		true
	 * @post	true
	 * @return	the data store generated id of the scheduler
	 */
	public Long getId() 
	{
		return this.id;
	}
	
	/**
	 * Method to get the list of ids for availabilities
	 * @return 
	 * @pre true;
	 * @post true;
	 * @return availabilities
	 */
	public List<Long> getAvailabilities()
	{
		return this.availabilities;
	}
	
	/**
	 * Method to add an availability to the scheduler
	 * @pre		true
	 * @post	this.availabilities size is increased by 1
	 * @param 	pAvailability		another possible time slot for the invited to submit their availability
	 */
	public void addAvailability(Availability pAvailability)
	{
		this.availabilities.add(pAvailability.getId());
	}
	
	/**
	 * Method to get the list of ids of users who submitted their availabilities
	 * @pre true;
	 * @post true;
	 * @return submitters
	 */
	public List<String> getSubmitters()
	{
		return this.submitters;
	}
	
	/**
	 * Method to add a user id to the list of submitters
	 * @pre userId != null;
	 * @post submitters.contains(userId) == true;
	 * @param userId - the id of the user who submitted availabilities
	 */
	public void addSubmitter(String userId)
	{
		this.submitters.add(userId);
	}
	
}
