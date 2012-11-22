package stuffplotter.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import stuffplotter.client.services.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.DayContainer;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;
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
	public Event createEvent(Event pEvent, List<MonthContainer> timeSlots)
	{
		// store event
		Event event = pEvent;
		dbstore.store(event);
		
		// associate scheduler to event
		this.addScheduler(event.getId(), timeSlots);
		
		
		Account account = dbstore.fetchAccount(pEvent.getOwner());
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
	 * @param	pMonthContainers	the list of month containers containing the proposed times for the event
	 */
	private void addScheduler(Long pEventId, List<MonthContainer> pMonthContainers)
	{
		Scheduler scheduler = new Scheduler();
		
		for (MonthContainer mc : pMonthContainers)
		{
			for (DayContainer dc : mc.getDays())
			{
				for (int i = 0; i < dc.getTimeSlots().size(); i++)
				{
					int year = Integer.parseInt(mc.getYear());
					int month = mc.getMonth().getIndex();
					int day = Integer.parseInt(dc.getDay());
					int hour = dc.getTimeSlots().get(i);
					
					Calendar calendar = Calendar.getInstance();
					calendar.set(year, month, day, hour, 0);
					Date date = calendar.getTime();
					
					Availability availability = new Availability(date);
					availability.setTimeFields(year, mc.getMonth(), day, hour);
					dbstore.store(availability);
					scheduler.addAvailability(availability);
				}
			}
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
