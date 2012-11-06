package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Availability implements Serializable
{	
	@Id private Long id;
	private Date time;
	private Integer votes;
	
	/**
	 * No Arg Constructor for Availability
	 * @pre		true;
	 * @post	true;
	 */
	public Availability()
	{

	}
	
	/**
	 * Constructor for Availability
	 * @pre		pDate != null;
	 * @post	true;
	 * @param 	pDate - date of the time slot
	 */
	public Availability(Date pDate)
	{
		this.time = pDate;
		this.votes = 0;
	}
	
	/**
	 * Get the key of this time slot 
	 * @pre		true;
	 * @post	true;
	 */
	public Long getId()
	{
		return this.id;
	}
	
	/**
	 * Increment the vote of this time slot
	 * @pre		true;
	 * @post	this.votes == this.votes + 1;
	 */
	public void incrementVote()
	{
		this.votes++;
	}	
}	

