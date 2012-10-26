package stuffplotter.server;

import java.security.Key;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

/**
 * Class to manipulate the database.
 */
public class DatabaseStore {
	
	/* Register all the entity classes Objectify will be working with */
	static
	{
		ObjectifyService.register(Account.class);
		ObjectifyService.register(Event.class);
	}
	
	/**
	 * Empty constructor for the DatabaseStore class.
	 */
	public DatabaseStore()
	{
		// empty constructor
	}
	
	/**
	 * Stores an object to the data store using Objectify
	 * @param pAcct	the Account to be stored
	 */	
	public void store(Object pObj) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pObj);
	}
	
	/**
	 * Fetches an Account from the data store using Objectify
	 * @param pId 	the id of the Account
	 * @return		the Account that is associated with the specified id
	 */
	public Account fetchAccount(String pId) {
		Objectify ofy = ObjectifyService.begin();
		Account acct = ofy.get(Account.class, pId);
		return acct;
	}
	
	/**
	 * Fetches an Event from the data store using Objectify
	 * @param pId 	the id of the Account
	 * @return		the Event that is associated with the specified id
	 */
	public Event fetchEvent(Long pId) {
		Objectify ofy = ObjectifyService.begin();
		Event evnt = ofy.get(Event.class, pId);
		return evnt;
	}
	
	/**
	 * Temporary method for adding events. i.e. this is a test method.
	 * @pre input != null;
	 * @post true;
	 * @param input - string to append to the event name.
	 * @return the ID of the event.
	 */
	
	public Long addEvent(String input)
	{	
		Objectify obj = ObjectifyService.begin();
		Event testEvent = new Event();
		obj.put(testEvent);
		return testEvent.getID();
	}
	
	
	/**
	 * Method to retrieve an event with the given ID.
	 * @param eventID - the ID of the event to search for. 
	 * @return the event or null if it could not be found.
	 */
	public Event retrieveEvent(String eventID)
	{
		Objectify obj = ObjectifyService.begin();
		Long eventIDAsLong;
		try
		{
			eventIDAsLong = Long.parseLong(eventID);
		}
		catch(NumberFormatException e)
		{
			return null;
		}
		
		List<Event> listOfEvents = obj.query(Event.class).filter("eventID", eventIDAsLong).list();
		if(listOfEvents.size() == 0)
		{
			return null;
		}
		
		return listOfEvents.get(0);
	}
}
