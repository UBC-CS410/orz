package stuffplotter.server;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.client.services.AccountStatsService;
import stuffplotter.client.services.AccountStatsServiceAsync;
import stuffplotter.shared.AccountStatistic;

/**
 * Class to handle leveling for users.
 */
public class LevelSystem {
	
	private static final int MAXLEVEL = 10;
	private static final int MAXXP = 100;
	private AccountStatistic user;
	private int currentExp;
	private int currentLevel;
	
	/**
	 * Default constructor for level system.
	 */
	public LevelSystem(AccountStatistic user)
	{
		this.user = user;
		this.currentExp = user.getUserExperience();
		this.currentLevel = user.getUserLevel();
	}

	public void saveStats()
	{
		AccountStatsServiceAsync statsService = GWT.create(AccountStatsService.class);
		statsService.save(this.user, new AsyncCallback<Void>(){

			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result)
			{
				// TODO Auto-generated method stub
				
			}});
	}
	
	/**
	 * Method to add experience to the user.
	 * @param expAmount - the amount of experience to add.
	 */
	public void addExperience(int expAmount)
	{
		if(this.currentLevel<MAXLEVEL)
		{
			this.currentExp += expAmount;
			if(this.currentExp>=100)
			{
				incrementLevel();
				this.currentExp = this.currentExp%100;
			}
			user.setUserExperience(currentExp);
			user.setUserLevel(currentLevel);
		}

	}
	
	/**
	 * Method to increment the user's level, should only be called during addExperience().
	 */
	private void incrementLevel()
	{
			this.currentLevel++;
			if (this.currentLevel>=MAXLEVEL)
			{
				user.setUserLevel(MAXLEVEL);
				user.setUserExperience(MAXXP);
			}
			else
				user.setUserLevel(currentLevel);
			user.accept(new AchievementChecker());

	}
	
	
	/**
	 * Method to retrieve the user's current experience.
	 * @return the user's current experience.
	 */
	public int getCurrentExperience()
	{
		return this.currentExp;
	}
	
	/**
	 * Method to retrieve the user's current level.
	 * @return the user's current level.
	 */
	public int getCurrentLevel()
	{
		return this.currentLevel;
	}
}
