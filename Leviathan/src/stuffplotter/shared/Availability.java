package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import stuffplotter.views.events.MonthPanel.Month;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Availability implements Serializable
{	
	@Id private Long id;
	private Date time;
	private int year;
	private Month month;
	private int day;
	private int hour;
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
	 * Get the year of this time slot
	 * @pre true;
	 * @post true;
	 * @return year
	 */
	public int getYear()
	{
		return this.year;
	}
	
	/**
	 * Get the month of this time slot
	 * @pre true;
	 * @post true;
	 * @return month
	 */
	public Month getMonth()
	{
		return this.month;
	}
	
	/**
	 * Get the day of this time slot
	 * @pre true;
	 * @post true;
	 * @return day
	 */
	public int getDay()
	{
		return this.day;
	}
	
	/**
	 * Get the hour of this time slot
	 * @pre true;
	 * @post true;
	 * @return hour
	 */
	public int getHour()
	{
		return this.hour;
	}
	
	/**
	 * Sets the time fields of this time slot
	 * @pre true;
	 * @post true;
	 */
	public void setTimeFields(int year, Month month, int day, int hour)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
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
}	

