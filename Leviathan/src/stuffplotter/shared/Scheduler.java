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
	private Long eventId;
	private List<List<Date>> proposedDates;
	private Date finalDate;
	
	/**
	 * Constructor for Scheduler
	 * 
	 * @pre		true
	 * @post	true
	 * 
	 * @param 	pEventId	the id of the event for which the scheduler is responsible for
	 * 			pEventTimes	the list of month containers that contains the proposed times for the event
	 * 
	 */
	public Scheduler(Long pEventId, List<MonthContainer> pEventTimes) {
		this.eventId = pEventId;
	}
	
	
	
	

}
