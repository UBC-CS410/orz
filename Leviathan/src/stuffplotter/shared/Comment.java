package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold information about a comment posted for an event.
 */
@Entity
public class Comment implements Serializable
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
	 * @return 	id
	 */
	public Long getId()
	{
		return this.id;
	}
	
	/**
	 * Get the time that this comment was added
	 * @pre		true;
	 * @post	true;
	 * @return 	time
	 */
	public Date getTime()
	{
		return this.time;
	}
	
	/**
	 * Get the content of this comment
	 * @pre		true;
	 * @post	true;
	 * @return	
	 */
	public String getUsername()
	{
		return this.name;
	}
	
	/**
	 * Get the username of this comment
	 * @pre		true;
	 * @post	true;
	 * @return 	content
	 */
	public String getContent()
	{
		return this.content;
	}
	

}
