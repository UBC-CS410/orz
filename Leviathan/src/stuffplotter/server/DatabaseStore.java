package stuffplotter.server;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.AchievementNotification;
import stuffplotter.shared.Event;
import stuffplotter.shared.EventNotification;
import stuffplotter.shared.FriendNotification;
import stuffplotter.shared.Notification;
import stuffplotter.shared.Scheduler;
import stuffplotter.shared.Availability;

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
		ObjectifyService.register(AccountStatistic.class);
		ObjectifyService.register(Notification.class);
		ObjectifyService.register(AchievementNotification.class);
		ObjectifyService.register(FriendNotification.class);
		ObjectifyService.register(EventNotification.class);
		ObjectifyService.register(Event.class);
		ObjectifyService.register(Scheduler.class);
		ObjectifyService.register(Availability.class);
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
	public void store(Account pAct) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pAct);
	}
	
	/**
	 * Stores an AccountStatistic to the data store using Objectify
	 * @param pAcct	the Account to be stored
	 */	
	public void store(AccountStatistic pActStats) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pActStats);
	}
	
	/**
	 * Stores an Event to the data store using Objectify
	 * @param pEvt	the Event to be stored
	 */	
	public void store(Event pEvt) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pEvt);
	}
	
	/**
	 * Stores a Scheduler to the data store using Objectify
	 * @param pSch	the Scheduler to be stored
	 */	
	public void store(Scheduler pSch) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pSch);
	}
	
	/**
	 * Stores an Availability to the data store using Objectify
	 * @param pSch	the Scheduler to be stored
	 */	
	public void store(Availability pAvl) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pAvl);
	}
	
	/**
	 * Fetches an Account from the data store using Objectify
	 * @param pId 	the id of the Account
	 * @return		the Account that is associated with the specified id
	 */
	public AccountStatistic fetchAccountStats(String pId) {
		Objectify ofy = ObjectifyService.begin();
		AccountStatistic acctStats = ofy.get(AccountStatistic.class, pId);
		return acctStats;
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
	 * @param pId 	the id of the Event
	 * @return		the Event that is associated with the specified id
	 */
	public Event fetchEvent(Long pId) {
		Objectify ofy = ObjectifyService.begin();
		Event evnt = ofy.get(Event.class, pId);
		return evnt;
	}
	
	/**
	 * Searches for Events by Account
	 * @param pId	the id of the Account
	 * @return		the list of Events owned by the specified Account, list could be empty
	 */
	public List<Event> searchEvents(String pId) {
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventOwner", pId).list();
		return evntList;
	}
	
	/**
	 * Searches for Events by Date
	 * @param pDate	the date of interest
	 * @return		the list of Events on the specified Date, list could be empty
	 */
	public List<Event> searchEvents(Date pDate) {
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventDate", pDate).list();
		return evntList;
	}
	
	/**
	 * Searches for Events by Cost
	 * @param pDbl	the cost of interest
	 * @return		the list of Events of the specified cost, list could be empty
	 */
	public List<Event> searchEvents(double pDbl) {
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventCost", pDbl).list();
		return evntList;
	}
	
}
