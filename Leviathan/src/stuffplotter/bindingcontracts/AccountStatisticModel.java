package stuffplotter.bindingcontracts;

import java.util.List;

import stuffplotter.shared.Achievement;

/**
 * Interface used by the Account object to enforce a contract between the model and views to
 * simulate data binding.
 */
public interface AccountStatisticModel
{
	/**
	 * Retrieve the account associated with the statistics.
	 * @pre true;
	 * @post true;
	 * @return the account associated with the statistics.
	 */
	public String getAccountEmail();
	
	/**
	 * Retrieve the level of the user.
	 * @pre true;
	 * @post true;
	 * @return the level of the user.
	 */
	public int getUserLevel();
	
	/**
	 * Retrieve the experience of the user.
	 * @pre true;
	 * @post true;
	 * @return the experience of the user.
	 */
	public int getUserExperience();
	
	/**
	 * Retrieve the list of Achievements the user has received.
	 * @pre true;
	 * @post true;
	 * @return the list of Achievements the user has received.
	 */
	public List<Achievement> getUserAchievements();
	
	/**
	 * Retrieve the total number of times the user has logged in.
	 * @pre true;
	 * @post true;
	 * @return the total number of times the user has logged in.
	 */
	public int getNumberOfLogins();
	
	/**
	 * Retrieve the total number of events the user has hosted.
	 * @pre true;
	 * @post true;
	 * @return the total number of events the user has hosted.
	 */
	public int getNumberOfHostedEvents();
	
	/**
	 * Retrieve the total number of events the user has participated in.
	 * @pre true;
	 * @post true;
	 * @return the total number of events the user has participated in.
	 */
	public int getNumberOfParticipatedEvents();
	
	/**
	 * Retrieve the total number of friends the user has.
	 * @pre true;
	 * @post true;
	 * @return the total number of friends the user has.
	 */
	public int getNumberOfFriends();	
	
	/**
	 * Retrieve the total number of comments.
	 * @pre true;
	 * @post true;
	 * @return the total number of comments
	 */
	public int getNumberOfComments();
	
	/**
	 * Retrieve the total number of rates.
	 * @pre true;
	 * @post true;
	 * @return the total number of rates
	 */
	public int getNumberOfRates();
}
