package stuffplotter.shared;

import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold information about a comment posted for an event.
 */
@Entity
public class Comment
{
	@Id private Long id;
	private String name;
	private Date time;
	private String content;
	
	/**
	 * Empty constructor
	 * @pre true;
	 * @post true;
	 */
	public Comment()
	{
		
	}
	
	/**
	 * Constructor
	 * @pre 
	 * @post
	 * @param name
	 * @param time
	 * @param content
	 */
	public Comment(String name, Date time, String content)
	{
		this.name = name;
		this.time = time;
		this.content = content;
	}
	
	/**
	 * Get the key of this comment
	 * @pre		true;
	 * @post	true;
	 */
	public Long getId()
	{
		return this.id;
	}

}
