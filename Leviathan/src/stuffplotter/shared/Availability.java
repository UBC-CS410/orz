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
	 * @return 	id
	 */
	public Long getId()
	{
		return this.id;
	}
	
	/**
	 * Get the date of this time slot
	 * @pre true;
	 * @post true;
	 * @return date
	 */
	public Date getTime()
	{
		return this.time;
	}
	
	/**
	 * Increment the vote of this time slot
	 * @pre		true;
	 * @post	this.votes++;
	 */
	public void incrementVote()
	{
		this.votes++;
	}	
	
	/**
	 * Serial version for the Availability.
	 */
	private static final long serialVersionUID = 7656857795437807838L;
}	

