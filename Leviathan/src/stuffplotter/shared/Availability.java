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
	
	public Availability()
	{

	}
	
	public Availability(Date pDate)
	{
		this.time = pDate;
		this.votes = 0;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public void incrementVote()
	{
		this.votes++;
	}	
}	

