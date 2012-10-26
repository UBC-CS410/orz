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
	 * Stores an Account to the data store using Objectify
	 * @param pAcct	the Account to be stored
	 */	
	public String storeAccount(Account pAcct) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pAcct);
		return pAcct.getUserId();
	}
	
	/**
	 * Fetches an Account from the data store using Objectify
	 * @param pUid 	the id that the Account is associated with
	 * @return		the Account that is associated with the specified id
	 */
	public Account fetchAccount(String pUid) {
		Objectify ofy = ObjectifyService.begin();
		Account acct = ofy.get(Account.class, pUid);
		return acct;
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
		List<String> testList = new ArrayList<String>();
		testList.add("Person 1");
		testList.add("Person 2");
		testList.add("Person 3");
		Event testEvent = new Event("Test Event 1" + input, testList);
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
