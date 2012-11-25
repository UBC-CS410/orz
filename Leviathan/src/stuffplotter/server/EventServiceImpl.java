package stuffplotter.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.client.services.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.Scheduler;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EventServiceImpl extends RemoteServiceServlet implements EventService
{
	private DatabaseStore dbstore = new DatabaseStore();
	private EmailService email = new EmailService();
	
	/**
	 * Adds an Event to the data store and the host's eventList.  Also associates the
	 * event to a event scheduler if event is added to data store successfully.
	 * @pre 	true;
	 * @post	adds an Event id to an Account's event list;
	 * @param 	pEvent - the new event to store
	 * @param   timeSlots - the time slots proposed for an event.
	 */
	@Override
	public Event createEvent(Event pEvent, List<Date> timeSlots)
	{
		// store event
		Event event = pEvent;
		dbstore.store(event);
		
		// associate scheduler to event
		this.addScheduler(event.getId(), timeSlots);
		
		
		Account account = dbstore.fetchAccount(pEvent.getOwnerID());
		account.addUserEvent(event.getId());
		dbstore.store(account);
		email.sendEvent(event);
		return event;
	}
	
	/**
	 * Helper method to add a Scheduler to an Event.
	 * @pre 	pMonthContainers != null
	 * @post	sets eventScheduler and eventStatus for the persistent event whose key is pEventId
	 * @param 	pEventId			the id of the event that the scheduler is bound to
	 * @param	pDates				the list of Dates containing the proposed times for the event
	 */
	private void addScheduler(Long pEventId, List<Date> pDates)
	{
		Scheduler scheduler = new Scheduler();
		
		for (Date date : pDates)
		{					
			Availability availability = new Availability(date);
			dbstore.store(availability);
			scheduler.addAvailability(availability);
		}
		
		dbstore.store(scheduler);
		Event event = dbstore.fetchEvent(pEventId);
		event.setEventScheduler(scheduler.getId());
		event.setStatus(Event.Status.PROPOSED);
		dbstore.store(event);
	}
	
	/**
	 * Retrieves an Event from the data store by id
	 * @pre 	eventId comes from an account's event list
	 * @post	true
	 * @param 	eventId - the id of the event to retrieve
	 */
	@Override
	public Event retrieveEvent(Long pEventId)
	{
		return dbstore.fetchEvent(pEventId);
	}
	
	/**
	 * Retrieves a List of Events from the data store by id
	 * @pre 	eventIds comes from an account's event list
	 * @post	true
	 * @param 	eventIds - the list of ids of events to retrieve
	 */
	@Override
	public List<Event> retrieveEvents(List<Long> pEventIds)
	{
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < pEventIds.size(); i++)
		{
			events.add(dbstore.fetchEvent(pEventIds.get(i)));
		}
		return events;
	}
	
	/**
	 * Retrieves a list of Availabilities from a Scheduler
	 * @pre 	pSchedulerId is valid
	 * @post	true
	 * @param 	pSchedulerId - the id of the Scheduler to retrieve from
	 */
	@Override
	public List<Availability> retrieveAvailabilities(Long pSchedulerId)
	{
		Scheduler scheduler = dbstore.fetchScheduler(pSchedulerId);
		List<Long> fetchIds = scheduler.getAvailabilities();
		List<Availability> fetched = new ArrayList<Availability>();
		for (int i = 0; i < fetchIds.size(); i++)
		{
			fetched.add(dbstore.fetchAvailability(fetchIds.get(i)));
		}
		return fetched;
	}
	

	/**
	 * Updates an Event by changing one of its fields 
	 * @pre 	modifiedEvent is not null
	 * @post	changes the persistent members of an Event
	 * @param 	pEvent	the modified event with field changes
	 */
	@Override
	public void updateEvent(Event pEvent)
	{
		dbstore.store(pEvent);
	}
	
	/**
	 * Updates a Scheduler by incrementing the vote on the specified availabilities
	 * @pre availabilityIds != null;
	 * @post for each Availability avl, avl.getVote += 1;
	 */
	@Override
	public void updateScheduler(List<Long> availabilityIds)
	{
		for (int i = 0; i < availabilityIds.size(); i++)
		{
			Availability toUpdate = dbstore.fetchAvailability(availabilityIds.get(i));
			toUpdate.incrementVote();
			dbstore.store(toUpdate);
		}
	}
	
	/**
	 * Adds a comment to an event
	 * @pre true;
	 * @post event.getComments.size()++;
	 * @param eventId - id of the event
	 * @param comment - string content
	 */
	@Override
	public void addComment(Long eventId, String username, Date time, String comment)
	{
		Comment newComment = new Comment(username, time, comment);
		dbstore.store(newComment);
		Event event = dbstore.fetchEvent(eventId);
		event.addComment(newComment.getId());
		dbstore.store(event);
	}

	/**
	 * Retrieves all comments posted for an event
	 * @pre true;
	 * @post true;
	 * @return list of strings
	 */
	@Override
	public List<Comment> getComments(Long eventId)
	{
		Event event = dbstore.fetchEvent(eventId);
		List<Comment> comments = new ArrayList<Comment>();
		List<Long> commentIds = event.getComments();
		for (int i = 0; i < commentIds.size(); i++)
		{
			Comment comment = dbstore.fetchComment(commentIds.get(i));
			comments.add(comment);
		}

		return comments;
	}

	
	@Override
	public void deleteEvent()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateEvent()
	{
		// TODO Auto-generated method stub
	}

}
