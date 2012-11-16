package stuffplotter.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;

import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.AchievementDescription;
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
	private List<AchievementDescription> userAchievements;
	private List<AchievementDescription> unlockAchievements;
	private int numLogins;
	private int numFriends;
	private int numHostedEvents;
	private int numParticipatedEvents;
	final private int ACHIEVEMENTXP = 100;
	
	
	public AchievementChecker()
	{
		this.unlockAchievements = new ArrayList<AchievementDescription>();
	}
	
	@Override
	public void visit(AccountStatistic user)
	{
		this.user = user;
		this.userAchievements = user.getUserAchievements();
		this.unlockAchievements = new ArrayList<AchievementDescription>();
		this.numLogins = user.getNumberOfLogins();
		this.numFriends = user.getNumberOfFriends();
		this.numHostedEvents = user.getNumberOfHostedEvents();
		this.numParticipatedEvents = user.getNumberOfParticipatedEvents();
		
		checkAccountAchievements();
		
		LevelSystem leveler = new LevelSystem(user);
		for(int i = 0; i<unlockAchievements.size(); i++)
			leveler.addExperience(ACHIEVEMENTXP);
			
		displayAchievements();
	}
	
	@Override
	public void visit(AccountStatistic user, Event event)
	{
		this.user = user;
		this.event = event;
		this.userAchievements = user.getUserAchievements();
		this.unlockAchievements = new ArrayList<AchievementDescription>();
		this.numLogins = user.getNumberOfLogins();
		this.numFriends = user.getNumberOfFriends();
		this.numHostedEvents = user.getNumberOfHostedEvents();
		this.numParticipatedEvents = user.getNumberOfParticipatedEvents();
		
		checkEventAchievements();
		displayAchievements();
	}
	
	

	/**
	 * Checks achievements involving accounts
	 * @pre
	 * @post
	 */
	private void checkAccountAchievements()
	{		
		if(!this.userAchievements.contains(AchievementDescription.FIRST_LOG_IN))
			firstLoggedIn();
		
		if(!this.userAchievements.contains(AchievementDescription.ADD_FIRST_FRIEND))
			addFirstFriend();
		
		this.user.addUserAchievements(unlockAchievements);
	}


	/**
	 * Checks achievements involving events
	 * @pre
	 * @post
	 */
	private void checkEventAchievements()
	{
		if(!this.userAchievements.contains(AchievementDescription.CREATE_FIRST_EVENT))
			createFirstEvent();
		
		if(!this.userAchievements.contains(AchievementDescription.COMPLETE_FIRST_EVENT))
			completeFirstEvent();
		
		if(!this.userAchievements.contains(AchievementDescription.FULL_EVENT_ATTENDANCE))
			fullEventAttendance();
		
		
		this.user.addUserAchievements(unlockAchievements);
	}
	
	/**
	 * This will display the achievements that just been unlocked
	 * @pre
	 * @post
	 */
	private void displayAchievements()
	{

		String text = "Unlocked Achievement(s):\n";
		if(!unlockAchievements.isEmpty())
		{
			for(int i = 0; i<unlockAchievements.size(); i++)
			{
				text = text + unlockAchievements.get(i).getDisplay() + "\n";
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
			this.unlockAchievements.add(AchievementDescription.FIRST_LOG_IN);
	}
	
	
	/**
	 * This checks if the user has created his first event
	 * @pre
	 * @post
	 */
	private void createFirstEvent()
	{
		if(this.numHostedEvents>=1)
			this.unlockAchievements.add(AchievementDescription.CREATE_FIRST_EVENT);
	}
	
	
	/**
	 * This checks if the user has added if first friend
	 * @pre
	 * @post
	 */
	private void addFirstFriend()
	{
		if(this.numFriends>=1)
			this.unlockAchievements.add(AchievementDescription.ADD_FIRST_FRIEND);
	}
	
	/**
	 * This checks if the user's first even has been completed
	 * @pre
	 * @post
	 */
	private void completeFirstEvent()
	{
		if(this.numParticipatedEvents>=1)
			this.unlockAchievements.add(AchievementDescription.FIRST_LOG_IN);
	}
	
	/**
	 * This checks if the user's event had a full attendance
	 * @pre
	 * @post
	 */
	private void fullEventAttendance()
	{
		if(this.event.getInvitees().size() >=0)
			this.unlockAchievements.add(AchievementDescription.FIRST_LOG_IN);
	}




}
