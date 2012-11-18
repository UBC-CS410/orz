package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync
{	
	/**
	 * Adds an Event to the data store and the host's eventList
	 * @pre 	true;
	 * @post	adds 1 to the length of host account's eventList;
	 * @param 	newEvent - the new event to be created
	 * @param 	callback - the callback object
	 * @param   timeSlots - the times proposed to have an event.
	 */
	void createEvent(Event newEvent, List<MonthContainer> timeSlots, AsyncCallback<Event> callback);
	
	/**
	 * Retrieves an Event from the data store
	 * @pre 	eventId comes from an account's event list
	 * @post	true
	 * @param 	eventId - the id of the event to retrieve
	 * 			callback - the callback object
	 */
	void retrieveEvent(Long eventId, AsyncCallback<Event> callback);
	
	/**
	 * Retrieves a List of Events from the data store
	 * @pre 	eventIds comes from an account's event list
	 * @post	true
	 * @param 	eventIds - the list of ids of events to retrieve
	 * 			callback - the callback object
	 */
	void retrieveEvents(List<Long> eventIds, AsyncCallback<List<Event>> callback);
	
	/**
	 * Modifies an Event by changing one of its fields
	 * @pre 	modifiedEvent is not null
	 * @post	changes the persistent members of an Event
	 * @param 	modifiedEvent - the modified event with field changes
	 * 			callback - the callback object
	 * 		
	 */
	void updateEvent(Event modifiedEvent, AsyncCallback<Void> callback);
	
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(AsyncCallback<Void> callback);
}
