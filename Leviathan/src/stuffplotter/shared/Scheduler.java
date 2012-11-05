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
public class Scheduler implements Serializable {
	
	@Id private Long id;
	private Long eventId;
	private List<Long> eventAvailabilities;
	private Date eventFinalized;
	
	
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
	public Scheduler(Long pEventId) {
		this.eventId = pEventId;
	}
	
	public void addAvailability(Availability pAvailability) {
		this.eventAvailabilities.add(pAvailability.getId());
	}
	
	@Entity
	public class Availability implements Serializable {
		
		@Id private Long id;
		private Date time;
		private Integer votes;
		
		public Availability() {

		}
		
		public Availability(Date pDate) {
			this.time = pDate;
			this.votes = 0;
		}
		
		public Long getId() {
			return this.id;
		}
		
		public void incrementVote() {
			this.votes++;
		}
		
	}	

}
