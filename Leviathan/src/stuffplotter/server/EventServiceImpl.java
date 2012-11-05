package stuffplotter.server;

import java.util.Date;
import java.util.List;

import stuffplotter.client.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.DayContainer;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;
import stuffplotter.shared.Scheduler;
import stuffplotter.shared.Scheduler.Availability;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements EventService
{
	private DatabaseStore dbstore = new DatabaseStore();
	
	/**
	 * Adds an Event to the data store and the host's eventList
	 * 
	 * @pre 	true
	 * 
	 * @post	adds an Event id to an Account's event list
	 * 
	 * @param 	pOwner		the new event to store
	 * 
	 */
	@Override
	public Event createEvent(Event pEvent)
	{
		Event event = pEvent;
		dbstore.store(event);
		
		Account account = dbstore.fetchAccount(pEvent.getOwner());
		account.addUserEvent(event.getId());
		dbstore.store(account);
		
		return event;
	}
	
	/**
	 * Adds a Scheduler to an Event
	 * 
	 * @pre 	true
	 * 
	 * @post	true
	 * 
	 * @param 	pEventId			the id of the event
	 * 			pMonthContainers	the list of month containers containing the proposed times for the event
	 */
	@Override
	public void createScheduler(Long pEventId, List<MonthContainer> pMonthContainers) {
		Scheduler scheduler = new Scheduler(pEventId);
		
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
					Date date = new Date(year, month, day, hour, 0);
					Availability availability = scheduler.new Availability(date);
					dbstore.store(availability);
					scheduler.addAvailability(availability);
				}
			}
		}
		
		dbstore.store(scheduler);
	}
	
	
	@Override
	public Event retrieveEvent(Long pEventId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Modifies an Event by changing one of its fields
	 * 
	 * @pre 	modifiedEvent is not null
	 * 
	 * @post	changes the persistent members of an Event
	 * 
	 * @param 	pEvent	the modified event with field changes
	 * 		
	 */
	@Override
	public void updateEvent(Event pEvent) {
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
