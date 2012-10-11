package stuffplotter.client;

/**
 * Class to handle leveling for users.
 */
public class LevelSystem {
	
	private int currentLevel;
	private int currentExp;
	
	/**
	 * Default constructor for level system.
	 */
	public LevelSystem()
	{
		this.currentLevel = 1;
		this.currentExp = 0;
	}
	
	/**
	 * Method to add experience to the user.
	 * @param expAmount - the amount of experience to add.
	 */
	public void addExperience(int expAmount)
	{
		this.currentExp += expAmount;
		tryLevelUp(this.currentLevel, this.currentExp);
	}
	
	/**
	 * Method to increment the user's level, should only be called during addExperience().
	 */
	private void incrementLevel()
	{
		this.currentLevel++;
	}
	
	/**
	 * Method to determine if a user can level up, should only be called during addExperience()
	 * @param level - current level of the user.
	 * @param currentExperience - current experience of the user.
	 */
	private void tryLevelUp(int level, int currentExperience)
	{
		int experienceForNextLvl = (int) (level * 100 * Math.pow(2, level - 1));
		if(experienceForNextLvl <= currentExperience)
		{
			incrementLevel();
		}
		else
		{
			// do nothing
		}
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
