package stuffplotter.server;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import stuffplotter.client.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.DayContainer;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;
import stuffplotter.shared.Scheduler;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
	 * 			pMonthContainers	the list of month containers containing the proposed times for the event
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
	
	
	@Override
	public Event retrieveEvent(Long pEventId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Modifies an Event by changing one of its fields 
	 * @pre 	modifiedEvent is not null
	 * @post	changes the persistent members of an Event
	 * @param 	pEvent	the modified event with field changes
	 */
	@Override
	public void updateEvent(Event pEvent)
	{
		dbstore.store(pEvent);
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
