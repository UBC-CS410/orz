package stuffplotter.client;

import java.util.Date;

import stuffplotter.shared.Event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {	
	/**
	 * Adds an Event to the data store and the host's eventList
	 * 
	 * @pre 	true
	 * 
	 * @post	adds 1 to the length of host account's eventList
	 * 
	 * @param 	ownerId			the id of the host account
	 * 			eventName		the name of the event
	 * 			eventLocation	the address of the event
	 * 			eventTime		the date of the event
	 * 			eventCost		the cost of the event
	 * 			callback		the callback object	
	 */
	void createEvent(String ownerId, String eventName, String eventLocation, Date eventTime, double eventCost, AsyncCallback<Event> callback);
	
	/**
	 * Modifies an Event by changing one of its fields
	 * 
	 * @pre 	fieldValue is not null and Object type is the same as required 
	 * 			by the variable that Field maps to
	 * 
	 * @post	changes the value of an Event's member variable
	 * 
	 * @param 	eventId			the id of the event
	 * 			eventField		the event field to change
	 * 			eventFieldValue	the new value to change event field to
	 * 			callback		the callback object
	 * 		
	 */
	void editEvent(Event modifiedEvent, AsyncCallback<Void> callback);
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(AsyncCallback<Void> callback);
}
