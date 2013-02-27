package stuffplotter.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Achievement;
import stuffplotter.shared.Event;

/**
 * This visitor visits the account/event to see if the user has cleared any achievements
 * @author Matt
 *
 */
public class AchievementChecker implements RecordVisitor
{
	private AccountStatistic user;
	private Event event;
	private List<Achievement> userAchievements;
	private List<Achievement> unlockAchievements;
	private int numLogins;
	private int numFriends;
	private int numHostedEvents;
	private int numParticipatedEvents;
	private int numComments;

	final private int ACHIEVEMENTXP = 55;
	private ServiceRepository applicationServices = new ServiceRepository();
	
	
	public AchievementChecker()
	{
		this.unlockAchievements = new ArrayList<Achievement>();
	}
	
	@Override
	public void visit(AccountStatistic user)
	{
		this.user = user;
		this.userAchievements = user.getUserAchievements();
		this.unlockAchievements = new ArrayList<Achievement>();
		this.numLogins = user.getNumberOfLogins();
		this.numFriends = user.getNumberOfFriends();
		this.numHostedEvents = user.getNumberOfHostedEvents();
		this.numParticipatedEvents = user.getNumberOfParticipatedEvents();
		this.numComments = user.getNumberOfComments();
		
		checkAccountAchievements();
		
		LevelSystem leveler = new LevelSystem(user);
		for(int i = 0; i<unlockAchievements.size(); i++)
			leveler.addExperience(ACHIEVEMENTXP);
			
		displayAchievements();
		if(unlockAchievements.size()>0)
			applicationServices.getStatsService().save(user, new AsyncCallback<Void>(){

				@Override
				public void onFailure(Throwable caught)
				{
					
				}

				@Override
				public void onSuccess(Void result)
				{

				}
				
			});
	}
	
	@Override
	public void visit(AccountStatistic user, Event event)
	{
		this.user = user;
		this.event = event;
		this.userAchievements = user.getUserAchievements();
		this.unlockAchievements = new ArrayList<Achievement>();
		this.numLogins = user.getNumberOfLogins();
		this.numFriends = user.getNumberOfFriends();
		this.numHostedEvents = user.getNumberOfHostedEvents();
		this.numParticipatedEvents = user.getNumberOfParticipatedEvents();
		
		checkEventAchievements();
		
		LevelSystem leveler = new LevelSystem(user);
		for(int i = 0; i<unlockAchievements.size(); i++)
			leveler.addExperience(ACHIEVEMENTXP);
		
		displayAchievements();
		if(unlockAchievements.size()>0)
			applicationServices.getStatsService().save(user, new AsyncCallback<Void>(){

				@Override
				public void onFailure(Throwable caught)
				{
					
				}

				@Override
				public void onSuccess(Void result)
				{

				}
				
			});
	}
	
	

	/**
	 * Checks achievements involving accounts
	 * @pre
	 * @post
	 */
	private void checkAccountAchievements()
	{		
		if(!this.userAchievements.contains(Achievement.FIRST_LOG_IN))
			firstLoggedIn();
		
		if(!this.userAchievements.contains(Achievement.REACH_LVL_5))
			reachedLvl5();
		
		if(!this.userAchievements.contains(Achievement.REACH_LVL10))
			reachedLvl10();
		
		if(!this.userAchievements.contains(Achievement.ADD_FIRST_FRIEND))
			addFirstFriend();
		
		if(!this.userAchievements.contains(Achievement.ADD_10_FRIENDS))
			add10Friends();
		
		if(!this.userAchievements.contains(Achievement.CREATE_FIRST_EVENT))
			createFirstEvent();
		
		if(!this.userAchievements.contains(Achievement.CREATE_5_EVENTS))
			create5Events();
		
		if(!this.userAchievements.contains(Achievement.COMMENT_AN_EVENT))
			commentAnEvent();
		
		if(!this.userAchievements.contains(Achievement.WRITE_50_COMMENTS))
			write50Comments();
		
		this.user.addUserAchievements(unlockAchievements);
		
		Notifier notifier = new Notifier();
		for(Achievement ach : unlockAchievements)
		{
			notifier.addNotification(user.getAccountEmail(), ach);
		}
		
	}


	/**
	 * Checks achievements involving events
	 * @pre
	 * @post
	 */
	private void checkEventAchievements()
	{

		
		if(!this.userAchievements.contains(Achievement.FIRST_PERFECT_EVENT))
			perfectEvent();
		
		if(!this.userAchievements.contains(Achievement.COMPLETE_FIRST_EVENT))
			completeFirstEvent();
		
		if(!this.userAchievements.contains(Achievement.FULL_EVENT_ATTENDANCE))
			fullEventAttendance();
		
		if(!this.userAchievements.contains(Achievement.COMPLETE_MULTI_DAY_EVENT))
			completeMultiDay();
		
		if(!this.userAchievements.contains(Achievement.RATE_AN_EVENT))
			rateAnEvent();
		
		if(!this.userAchievements.contains(Achievement.COMPLETE_3_EVENTS_SAMEDAY))
			complete3EventsSameday();
		
		this.user.addUserAchievements(unlockAchievements);
	}
	

	/**
	 * This will display the achievements that just been unlocked
	 * @pre
	 * @post
	 */
	private void displayAchievements()
	{

		String text = "Achievement(s) Unlocked:\n";
		if(!this.unlockAchievements.isEmpty())
		{
			for(int i = 0; i<this.unlockAchievements.size(); i++)
			{
				text = text+this.unlockAchievements.get(i).getName() +"  -  "+this.unlockAchievements.get(i).getMsg()+"\n";
			}
			Window.alert(text);
		}
		
	}


	
	/**
	 * This checks for the achievement if the user has logged in the for first time
	 * @pre
	 * @post
	 */
	private void firstLoggedIn()
	{
		if(this.numLogins>=1)
			this.unlockAchievements.add(Achievement.FIRST_LOG_IN);
	}
	
	
	/**
	 * This checks if the user has created his first event
	 * @pre
	 * @post
	 */
	private void createFirstEvent()
	{
		if(this.numHostedEvents>=1)
			this.unlockAchievements.add(Achievement.CREATE_FIRST_EVENT);
	}
	
	private void reachedLvl5()
	{
		if(this.user.getUserLevel()>=5)
			this.unlockAchievements.add(Achievement.REACH_LVL_5);
	}
	
	private void reachedLvl10()
	{
		if(this.user.getUserLevel()>=10)
			this.unlockAchievements.add(Achievement.REACH_LVL10);
	}
	
	/**
	 * This checks if the user has added if first friend
	 * @pre
	 * @post
	 */
	private void addFirstFriend()
	{
		if(this.numFriends>=1)
			this.unlockAchievements.add(Achievement.ADD_FIRST_FRIEND);
	}
	
	/**
	 * This checks if the user has at least 10 friends
	 * @pre
	 * @post
	 */
	private void add10Friends()
	{
		if(this.numFriends>=10)
			this.unlockAchievements.add(Achievement.ADD_10_FRIENDS);
	}
	
	/**
	 * This checks if the user's first even has been completed
	 * @pre
	 * @post
	 */
	private void completeFirstEvent()
	{
		if(this.numParticipatedEvents>=1)
			this.unlockAchievements.add(Achievement.FIRST_LOG_IN);
	}
	
	
	
	/**
	 * This checks if the user's event had a full attendance
	 * @pre
	 * @post
	 */
	private void fullEventAttendance()
	{
//		//TODO:
//		if(this.event.getInvitees().size() >=0)
//			this.unlockAchievements.add(Achievement.FIRST_LOG_IN);
	}
	
	private void complete3EventsSameday()
	{
		// TODO Auto-generated method stub
		
	}

	private void write50Comments()
	{
		if(this.numComments>=50)
			this.unlockAchievements.add(Achievement.WRITE_50_COMMENTS);
		
	}

	private void commentAnEvent()
	{
		if(this.numComments>=1)
			this.unlockAchievements.add(Achievement.COMMENT_AN_EVENT);
	}

	private void rateAnEvent()
	{
		// TODO Auto-generated method stub
		
	}

	private void completeMultiDay()
	{
		// TODO Auto-generated method stub
		
	}

	private void perfectEvent()
	{
		// TODO Auto-generated method stub
		
	}

	private void create5Events()
	{
		if(this.numParticipatedEvents>=5)
			this.unlockAchievements.add(Achievement.CREATE_5_EVENTS);
		
	}




}
