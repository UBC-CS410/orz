/**
 * 
 */
package stuffplotter.server;

import java.util.Date;
import java.util.List;

import stuffplotter.client.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements EventService {
	
	private DatabaseStore dbstore = new DatabaseStore();
	
	/**
	 * Adds an Event to the data store and the host's eventList
	 * 
	 * @pre 	true
	 * 
	 * @post	adds an Event id to an Account's event list
	 * 
	 * @param 	pOwner		the id of the host account
	 * 			pName		the name of the event
	 * 			pLocation	the address of the event
	 * 			pDate		the date of the event
	 * 			pCost		the cost of the event
	 */
	@Override
	public Event createEvent(String pOwner, String pName, String pLocation, Date pDate, double pCost) {
		Event event = new Event(pOwner, pName, pLocation, pDate, pCost);
		dbstore.store(event);
		
		Account account = dbstore.fetchAccount(pOwner);
		account.addUserEvent(event.getEventId());
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
	 * @param 	eventId			the id of the event
	 * 			eventTimes		the list of month containers that contains proposed times for the event
	 */
	@Override
	public void createScheduler(Long pEventId, List<MonthContainer> pEventTimes) {
		// TODO Auto-generated method stub
		
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
	public void deleteEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateEvent() {
		// TODO Auto-generated method stub
	}

}
