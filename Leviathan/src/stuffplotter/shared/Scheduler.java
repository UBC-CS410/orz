package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold information about the proposed dates and finalized dates
 * pertaining to an Event
 */
@Entity
public class Scheduler implements Serializable
{	
	@Id private Long id;
	private List<Long> availabilities;
	private Date result;
	
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
	}
	
	/**
	 * Method to get the id of the scheduler
	 * 
	 * @pre		true
	 * @post	true
	 * 
	 * @return	the data store generated id of the scheduler
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Method to add an availability to the scheduler
	 * 
	 * @pre		true
	 * @post	this.availabilities size is increased by 1
	 * 
	 * @param 	pAvailability		another possible time slot for the invited to submit their availability
	 */
	public void addAvailability(Availability pAvailability)
	{
		this.availabilities.add(pAvailability.getId());
	}
}
