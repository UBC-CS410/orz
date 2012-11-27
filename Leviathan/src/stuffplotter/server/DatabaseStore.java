package stuffplotter.server;

import java.util.Date;
import java.util.List;
import java.util.Map;

import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.AchievementNotification;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.EventNotification;
import stuffplotter.shared.FriendNotification;
import stuffplotter.shared.Notification;
import stuffplotter.shared.Scheduler;
import stuffplotter.shared.Availability;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

/**
 * Class to manipulate the database.
 */
public class DatabaseStore
{	
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
		ObjectifyService.register(Comment.class);
	}
	
	/**
	 * Empty constructor for the DatabaseStore class.
	 * @pre true;
	 * @post true;
	 */
	public DatabaseStore()
	{
		// empty constructor
	}
	
	/**
	 * Creates or updates an object in the data store
	 * @pre object != null;
	 * @post ofy.get(object.getKey()) == object
	 * @param object - the object to store
	 */
	public void simpleStore(Object object)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(object);
	}
	
	/**
	 * Fetches an object from the data store
	 * @pre key != null;
	 * @post true;
	 * @param key - an objectify key
	 */
	public Object simpleFetch(Key<?> key)
	{
		Objectify ofy = ObjectifyService.begin();
		return ofy.get(key);
	}
	
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public AccountStatistic fetchAccountStats(String pId)
	{
		Objectify ofy = ObjectifyService.begin();
		AccountStatistic acctStats = ofy.get(AccountStatistic.class, pId);
		return acctStats;
	}
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public Account fetchAccount(String pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Account acct = ofy.get(Account.class, pId);
		return acct;
	}
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public Event fetchEvent(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Event evnt = ofy.get(Event.class, pId);
		return evnt;
	}
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public Scheduler fetchScheduler(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Scheduler sch = ofy.get(Scheduler.class, pId);
		return sch;
	}
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public Availability fetchAvailability(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Availability avl = ofy.get(Availability.class, pId);
		return avl;
	}
	
	//TODO: REFACTOR SERVICE IMPLS TO USE SIMPLE FETCH AND REMOVE THIS FUNCTION
	public Comment fetchComment(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Comment cmt = ofy.get(Comment.class, pId);
		return cmt;
	}
	
	/**
	 * Fetches the list of accounts from the data store using Objectify.
	 * @pre userIds != null;
	 * @post true;
	 * @param userIds - the list of user ids (e-mails) of the user accounts to get.
	 * @return the user accounts associated with the given list of ids (e-mails).
	 */
	public Map<String, Account> fetchAccounts(List<String> userIds)
	{
		Objectify ofy = ObjectifyService.begin();
		Map<String, Account> accounts = ofy.get(Account.class, userIds);
		return accounts;
	}
	
	/**
	 * Fetches a Notification from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Notification.
	 * @return the Notification that is associated with the specified id.
	 */
	public Notification fetchNotification(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Notification not;
		try
		{
			not = ofy.get(Notification.class, pId);
		}
		catch (NotFoundException nfe)
		{
			try
			{
				not = ofy.get(AchievementNotification.class, pId);
			}
			catch (NotFoundException nfe1)
			{
				try
				{
					not = ofy.get(FriendNotification.class, pId);
				}
				catch (NotFoundException nfe2)
				{
					not = ofy.get(EventNotification.class, pId);
				}
			}
		}

		return not;
	}
	
	/**
	 * Searches for Events by Account.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Account.
	 * @return the list of Events owned by the specified Account, list could be empty
	 */
	public List<Event> searchEvents(String pId)
	{
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventOwner", pId).list();
		return evntList;
	}
	
	/**
	 * Searches for Events by Date.
	 * @pre pDate != null;
	 * @post true;
	 * @param pDate - the date of interest.
	 * @return the list of Events on the specified Date, list could be empty.
	 */
	public List<Event> searchEvents(Date pDate)
	{
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventDate", pDate).list();
		return evntList;
	}
	
	/**
	 * Searches for Events by Cost.
	 * @pre true;
	 * @post true;
	 * @param pDbl - the cost of interest.
	 * @return the list of Events of the specified cost, list could be empty.
	 */
	public List<Event> searchEvents(double pDbl)
	{
		Objectify ofy = ObjectifyService.begin();
		List<Event> evntList = ofy.query(Event.class).filter("eventCost", pDbl).list();
		return evntList;
	}

}
