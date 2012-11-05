package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

/**
 * Class to hold information about the proposed dates and finalized dates
 * pertaining to an Event
 */
@Entity
public class Scheduler implements Serializable {
	
	@Id private Long id;
	private Long event;
	private List<Date> dates;
	private List<Date> finalDates;
	
	/**
	 * Constructor for Scheduler
	 * 
	 * @pre		true
	 * @post	true
	 * 
	 */
	public Scheduler() {
		
	}
	
	

}
