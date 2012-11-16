package stuffplotter.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import stuffplotter.server.RecordVisitor;

public class AccountStatistic implements Serializable
{
	@Id
	private String accountEmail;
	
	private int userLevel;
	private int userExperience;
	private List<AchievementDescription> userAchievements; 
	
	private int numberOfLogins;
	private int numberOfHostedEvents;
	private int numberOfParticipatedEvents;
	private int numberOfFriends;	
	
	public AccountStatistic()
	{
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
		this.setNumberOfFriends(0);
		this.userAchievements = new ArrayList<AchievementDescription>();
	}
	
	public AccountStatistic(String user)
	{
		this.accountEmail = user;
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
		this.setNumberOfFriends(0);
		this.userAchievements = new ArrayList<AchievementDescription>();
	}
	
	public void accept (RecordVisitor visitor)
	{
		visitor.visit(this);
	}
	
	public void increamentLogin()
	{
		int login = this.numberOfLogins;
		login++;
		this.numberOfLogins = login;
	}
	
	public void increamentHostedEvents()
	{
		int host = this.numberOfHostedEvents;
		host++;
		this.numberOfHostedEvents = host;
	}
	
	public void increamentParticipatedEvents()
	{
		int participated = this.numberOfParticipatedEvents;
		participated++;
		this.numberOfParticipatedEvents = participated;
	}
	
	public String getAccountEmail()
	{
		return accountEmail;
	}
	
	public void setAccountEmail(String email)
	{
		this.accountEmail = email;
	}

	public int getNumberOfLogins()
	{
		return numberOfLogins;
	}

	public void setNumberOfLogins(int numberOfLogins)
	{
		this.numberOfLogins = numberOfLogins;
	}

	public int getNumberOfHostedEvents()
	{
		return numberOfHostedEvents;
	}

	public void setNumberOfHostedEvents(int numberOfHostedEvents)
	{
		this.numberOfHostedEvents = numberOfHostedEvents;
	}

	public int getNumberOfParticipatedEvents()
	{
		return numberOfParticipatedEvents;
	}

	public void setNumberOfParticipatedEvents(int numberOfParticipatedEvents)
	{
		this.numberOfParticipatedEvents = numberOfParticipatedEvents;
	}

	public int getUserLevel()
	{
		return userLevel;
	}

	public void setUserLevel(int userLevel)
	{
		this.userLevel = userLevel;
	}

	public int getUserExperience()
	{
		return userExperience;
	}

	public void setUserExperience(int userExperience)
	{
		this.userExperience = userExperience;
	}

	public List<AchievementDescription> getUserAchievements()
	{
		return userAchievements;
	}

	public void setUserAchievements(List<AchievementDescription> userAchievements)
	{
		this.userAchievements = userAchievements;
	}
	
	/**
	 * This method adds achievements as well as checks for duplicates
	 * @pre
	 * @post
	 * @param achievements to be added
	 * @return
	 */
	public boolean addUserAchievements(List<AchievementDescription> achievements)
	{
		for(int i = 0; i<achievements.size(); i++)
		{
			if(this.userAchievements.contains(achievements.get(i)))
				achievements.remove(i);
		}
		
		return this.userAchievements.addAll(achievements);
	}

	public int getNumberOfFriends()
	{
		return numberOfFriends;
	}

	public void setNumberOfFriends(int numberOfFriends)
	{
		this.numberOfFriends = numberOfFriends;
	}
	
}
