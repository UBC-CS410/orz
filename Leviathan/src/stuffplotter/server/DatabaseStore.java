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
	 * Stores an Account to the data store using Objectify.
	 * @pre pAct != null;
	 * @post true;
	 * @param pAcct - the Account to be stored.
	 */	
	public void store(Account pAct)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pAct);
	}
	
	/**
	 * Stores an AccountStatistic to the data store using Objectify.
	 * @pre pActStats != null;
	 * @post true;
	 * @param pAcct - the AccountStatistic to be stored.
	 */	
	public void store(AccountStatistic pActStats)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pActStats);
	}
	
	/**
	 * Stores an Event to the data store using Objectify.
	 * @pre pEvt != null;
	 * @post true;
	 * @param pEvt - the Event to be stored.
	 */	
	public void store(Event pEvt)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pEvt);
	}
	
	/**
	 * Stores a Scheduler to the data store using Objectify.
	 * @pre pSch != null;
	 * @post true;
	 * @param pSch - the Scheduler to be stored.
	 */	
	public void store(Scheduler pSch)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pSch);
	}
	
	/**
	 * Stores an Availability to the data store using Objectify.
	 * @pre pAvl != null;
	 * @post true;
	 * @param pAvl - the Availability to be stored.
	 */	
	public void store(Availability pAvl)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pAvl);
	}
	
	/**
	 * Stores a Comment to the data store using Objectify.
	 * @pre pNotif != null;
	 * @post true;
	 * @param pNotif - the Notification to be stored.
	 */	
	public void store(Notification pNotif)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pNotif);
	}
	
	
	
	/**
	 * Stores a Comment to the data store using Objectify.
	 * @pre pCmt != null;
	 * @post true;
	 * @param pCmt - the Comment to be stored.
	 */	
	public void store(Comment pCmt)
	{
		Objectify ofy = ObjectifyService.begin();
		ofy.put(pCmt);
	}
	
	/**
	 * Fetches an AccountStatistic from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the AccountStatistic.
	 * @return - the AccountStatistic that is associated with the specified id.
	 */
	public AccountStatistic fetchAccountStats(String pId)
	{
		Objectify ofy = ObjectifyService.begin();
		AccountStatistic acctStats = ofy.get(AccountStatistic.class, pId);
		return acctStats;
	}
	
	/**
	 * Fetches an Account from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Account.
	 * @return the Account that is associated with the specified id.
	 */
	public Account fetchAccount(String pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Account acct = ofy.get(Account.class, pId);
		return acct;
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
	 * Fetches an Event from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Event
	 * @return the Event that is associated with the specified id.
	 */
	public Event fetchEvent(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Event evnt = ofy.get(Event.class, pId);
		return evnt;
	}
	
	/**
	 * Fetches a Scheduler from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Scheduler.
	 * @return the Scheduler that is associated with the specified id.
	 */
	public Scheduler fetchScheduler(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Scheduler sch = ofy.get(Scheduler.class, pId);
		return sch;
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
	 * Fetches an Availability from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Availability
	 * @return the Availability that is associated with the specified id.
	 */
	public Availability fetchAvailability(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Availability avl = ofy.get(Availability.class, pId);
		return avl;
	}
	
	/**
	 * Fetches an Comment from the data store using Objectify.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of the Comment.
	 * @return the Comment that is associated with the specified id.
	 */
	public Comment fetchComment(Long pId)
	{
		Objectify ofy = ObjectifyService.begin();
		Comment cmt = ofy.get(Comment.class, pId);
		return cmt;
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
