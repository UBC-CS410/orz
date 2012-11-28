package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.server.RecordVisitor;

public class AccountStatistic implements Serializable, AccountStatisticModel
{
	/**
	 * The account ID.
	 */
	@Id
	private String accountEmail;
	
	private int userLevel;
	private int userExperience;
	private List<Achievement> userAchievements; 
	
	private int numberOfLogins;
	private int numberOfHostedEvents;
	private int numberOfParticipatedEvents;
	private int numberOfFriends;
	private int numberOfComments;
	private int numberOfRates;

	/**
	 * Constructor for the AccountStatistic.
	 * @pre true;
	 * @post true;
	 */
	public AccountStatistic()
	{
		this.userLevel = 1;
		this.userExperience = 0;
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
		this.numberOfRates = 0;
		this.setNumberOfComments(0);
		this.setNumberOfFriends(0);
		this.userAchievements = new ArrayList<Achievement>();
	}
	
	/**
	 * Constructor for the AccountStatistic with a given user email.
	 * @pre user != null;
	 * @post true;
	 * @param user - the email of the user.
	 */
	public AccountStatistic(String user)
	{
		this.accountEmail = user;
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
		this.numberOfRates = 0;
		this.setNumberOfFriends(0);
		this.setNumberOfComments(0);
		this.userAchievements = new ArrayList<Achievement>();
	}
	
	/**
	 * Method to accept a RecordVisitor.
	 * @pre visitor != null;
	 * @post true;
	 * @param visitor - the RecordVisitor to accept.
	 */
	public void accept (RecordVisitor visitor)
	{
		visitor.visit(this);
	}
	
	/**
	 * Method to accept a RecordVisitor and event.
	 * @pre visitor != null;
	 * @post true;
	 * @param visitor - the RecordVisitor to accept.
	 */
	public void accept (RecordVisitor visitor, Event event)
	{
		visitor.visit(this, event);
	}
	
	/**
	 * Increment the number of times the user has logged in.
	 * @pre true;
	 * @post this.numberOfLogins == @pre.this.numberOfLogins + 1;
	 */
	public void incrementLogin()
	{
		int login = this.numberOfLogins;
		login++;
		this.numberOfLogins = login;
	}
	
	/**
	 * Increment the number of events a user has hosted.
	 * @pre true;
	 * @post this.numberOfHostedEvents == @pre.this.numberOfHostedEvents + 1;
	 */
	public void incrementHostedEvents()
	{
		int host = this.numberOfHostedEvents;
		host++;
		this.numberOfHostedEvents = host;
	}
	
	/**
	 * Increment the number of events a user has participated in.
	 * @pre true;
	 * @post this.numberOfParticipatedEvents == @pre.this.numberOfParticipatedEvents.
	 */
	public void incrementParticipatedEvents()
	{
		int participated = this.numberOfParticipatedEvents;
		participated++;
		this.numberOfParticipatedEvents = participated;
	}
	
	/**
	 * Increment the number of Friends a user has.
	 * @pre true;
	 * @post this.numberOfParticipatedEvents == @pre.this.numberOfParticipatedEvents.
	 */
	public void incrementFriends()
	{
		int friends = this.numberOfFriends;
		friends++;
		this.numberOfFriends = friends;
	}
	
	public void decrementFriends()
	{
		int friends = this.numberOfFriends;
		friends--;
		this.numberOfFriends = friends;
		
	}
	
	/**
	 * Retrieve the email associated with the AccountStatistic.
	 * @pre true;
	 * @post true;
	 * @return the email associated with the AccountStatistic.
	 */
	@Override
	public String getAccountEmail()
	{
		return this.accountEmail;
	}
	
	/**
	 * Set the email associated with the AccountStatistic.
	 * @pre email != null;
	 * @post true;
	 * @param email - the email to associate with the AccountStatistic. 
	 */
	public void setAccountEmail(String email)
	{
		this.accountEmail = email;
	}

	/**
	 * Retrieve the number of times a user has logged in.
	 * @pre true;
	 * @post true;
	 * @return the number of times a user has logged in.
	 */
	@Override
	public int getNumberOfLogins()
	{
		return this.numberOfLogins;
	}

	/**
	 * Set the number of times a user has logged in.
	 * @pre numberOfLogins >= 0;
	 * @post true;
	 * @param numberOfLogins - the number of times the user has logged in.
	 */
	public void setNumberOfLogins(int numberOfLogins)
	{
		this.numberOfLogins = numberOfLogins;
	}

	/**
	 * Retrieve the number of events a user has hosted. 
	 * @pre true;
	 * @post true;
	 * @return the number of events a user has hosted.
	 */
	@Override
	public int getNumberOfHostedEvents()
	{
		return this.numberOfHostedEvents;
	}

	/**
	 * Set the number of events a user has hosted.
	 * @pre numberOfHostedEvents >= 0;
	 * @post true;
	 * @param numberOfHostedEvents - the number of events a user has hosted.
	 */
	public void setNumberOfHostedEvents(int numberOfHostedEvents)
	{
		this.numberOfHostedEvents = numberOfHostedEvents;
	}

	/**
	 * Retrieve the number of events a user has participated in.
	 * @pre true;
	 * @post true;
	 * @return the number of events a user has participated in.
	 */
	@Override
	public int getNumberOfParticipatedEvents()
	{
		return this.numberOfParticipatedEvents;
	}

	/**
	 * Set the number of participated events.
	 * @pre numberOfParticipatedEvents >= 0;
	 * @post true;
	 * @param numberOfParticipatedEvents - the number of events participated in to set.
	 */
	public void setNumberOfParticipatedEvents(int numberOfParticipatedEvents)
	{
		this.numberOfParticipatedEvents = numberOfParticipatedEvents;
	}

	/**
	 * Retrieve the level of the user.
	 * @pre true;
	 * @post true;
	 * @return the level of the user.
	 */
	@Override
	public int getUserLevel()
	{
		return this.userLevel;
	}

	/**
	 * Set the level of the user.
	 * @pre userLevel >= 0;
	 * @post true;
	 * @param userLevel - the level to set for the user.
	 */
	public void setUserLevel(int userLevel)
	{
		this.userLevel = userLevel;
	}

	/**
	 * Retrieve the amount of experience the user has gained.
	 * @pre true;
	 * @post true;
	 * @return the amount of experience the user has gained.
	 */
	@Override
	public int getUserExperience()
	{
		return this.userExperience;
	}

	/**
	 * Set the amount of experience the user has.
	 * @pre userExperience >= 0;
	 * @post true;
	 * @param userExperience - the amount of experience to set for the user.
	 */
	public void setUserExperience(int userExperience)
	{
		this.userExperience = userExperience;
	}

	/**
	 * Retrieve the list of Achievements that the user has.
	 * @pre true;
	 * @post true;
	 * @return the list of Achievements that the user has.
	 */
	@Override
	public List<Achievement> getUserAchievements()
	{
		return this.userAchievements;
	}

	/**
	 * Set the list of Achievements that the user has.
	 * @pre userAchievements != null;
	 * @post true;
	 * @param userAchievements - the list of Achievements that the user has.
	 */
	public void setUserAchievements(List<Achievement> userAchievements)
	{
		this.userAchievements = userAchievements;
	}
	
	/**
	 * This method adds achievements as well as checks for duplicates.
	 * @pre achievements != null;
	 * @post true;
	 * @param achievements - the achievements to be added.
	 * @return true is adding the achievements was successful; false otherwise.
	 */
	public boolean addUserAchievements(List<Achievement> achievements)
	{
		// for loop to remove duplicates
		for(int i = 0; i<achievements.size(); i++)
		{
			if(this.userAchievements.contains(achievements.get(i)))
				achievements.remove(i);
		}
		
		return this.userAchievements.addAll(achievements);
	}

	/**
	 * Retrieve the number of friends a user has.
	 * @pre true;
	 * @post true;
	 * @return the number of friends a user has.
	 */
	@Override
	public int getNumberOfFriends()
	{
		return this.numberOfFriends;
	}

	/**
	 * Set the number of friends a user has.
	 * @pre numberOfFriends >= 0;
	 * @post true;
	 * @param numberOfFriends - the number of friends to set for the user.
	 */
	public void setNumberOfFriends(int numberOfFriends)
	{
		this.numberOfFriends = numberOfFriends;
	}

	@Override
	public int getNumberOfComments()
	{
		return numberOfComments;
	}

	public void setNumberOfComments(int numberOfComments)
	{
		this.numberOfComments = numberOfComments;
	}

	/**
	 * Serial version for the AccountStatistic.
	 */
	private static final long serialVersionUID = 8871578746223259137L;

	@Override
	public int getNumberOfRates()
	{
		return this.numberOfRates;
	}
	
	public void setNumberOfRates(int rates)
	{
		this.numberOfRates = rates;
	}


}
