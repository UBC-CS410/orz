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
	private List<Availability> proposedDates;
	private Date finalDate;
	
	
	/**
	 * No Arg Constructor for Scheduler
	 */
	public Scheduler() {
		
	}
	
	
	/**
	 * Constructor for Scheduler
	 * 
	 * @pre		true
	 * @post	true
	 * 
	 * @param 	pEventId			the id of the event for which the scheduler is responsible for
	 * 			pMonthContainers	the list of month containers that contains the proposed times for the event
	 * 
	 */
	public Scheduler(Long pEventId, List<MonthContainer> pMonthContainers) {
		this.eventId = pEventId;
		
		/*
		for(MonthContainer mc : pMonthContainers)
		{
			
		}
		*/
	}
	
	@Entity
	private class Availability implements Serializable {
		
		@Id private Long id;
		private Date time;
		private Integer votes;
		
		private Availability() {
			
		}
	}	

}
