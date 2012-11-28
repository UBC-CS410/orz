package stuffplotter.bindingcontracts;

import java.util.Comparator;
import java.util.List;

/**
 * Interface used by the Account object to enforce a contract between the model and views to
 * simulate data binding.
 */
public interface AccountModel extends Comparator<AccountModel>
{
	/**
	 * Logout URL for the user.
	 * @pre true;
	 * @post true;
	 * @return the logout URL for the user.
	 */
	public String getLogoutUrl();
	
	/**
	 * Retrieve the user's email.
	 * @pre true;
	 * @post true;
	 * @return the user's email.
	 */
	public String getUserEmail();
	
	/**
	 * Retrieve the user's age.
	 * @pre true;
	 * @post true;
	 * @return the user's age.
	 */
	public String getUserAge();
	
	/**
	 * Retrieve the user's phone number.
	 * @pre true;
	 * @post true;
	 * @return the user's phone number.
	 */
	public String getUserPhone();
	
	/**
	 * Retrieve the full name of the user.
	 * @pre true;
	 * @post true;
	 * @return the full name of the user.
	 */
	public String getUserFullName();
	
	/**
	 * Retrieve the URL to the user's profile picture.
	 * @pre true;
	 * @post true;
	 * @return the URL to the user's profile picture.
	 */
	public String getUserProfilePicture();
	
	/**
	 * Retrieve the user's title.
	 * @pre true;
	 * @post true;
	 * @return the user's title.
	 */
	public String getUserTitle();
	
	/**
	 * Retrieve the list of the user's friends (e-mail addresses).
	 * @pre true;
	 * @post true;
	 * @return the list of the user's friends (e-mail addresses).
	 */
	public List<String> getUserFriends();
	
	/**
	 * Retrieve the list of the user's current event ids
	 * @pre true;
	 * @post true;
	 * @return the list of the user's current event ids
	 */
	public List<Long> getCurrentEvents();
	
	/**
	 * Retrieve the list of the user's finished event ids
	 * @pre true;
	 * @post true;
	 * @return the list of the user's finished event ids
	 */
	public List<Long> getPastEvents();
}
