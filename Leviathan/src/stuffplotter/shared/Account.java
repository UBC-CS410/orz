package stuffplotter.shared;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import stuffplotter.bindingcontracts.AccountModel;

import com.googlecode.objectify.annotation.Entity;


/**
 * The Account Class holds information for individual users.
 * Information includes profile, events, and statistics.
 */
@SuppressWarnings("serial")
@Entity
public class Account implements Serializable, AccountModel
{

	/* Basic information */
	@Id
	private String userEmail;
	private String userLogoutUrl;
	
	/* Google userinfo */
	private String userAccessToken;
	private String userFullName;
	private String gender;
	private String birthDate;
	private String userProfilePicture;

	/* Application information */
	private List<String> userFriends;
	private List<String> pendingFriends;
	private List<Long> userCurrentEvents;
	private List<Long> userPastEvents;
	private List<Long> userNotifications;
	
	/* Custom information */
	private String userAge;
	private String userPhone;
	private String userTitle;

	/** 
	 * Account Constructor
	 * @pre true;
	 * @post true;
	 */
	public Account()
	{
		this.userFriends = new ArrayList<String>();
		this.pendingFriends = new ArrayList<String>();
		this.userCurrentEvents = new ArrayList<Long>();
		this.userPastEvents = new ArrayList<Long>();
		this.userNotifications = new ArrayList<Long>();
		this.userAge = "";
	}

	/**
	 * Account Constructor that sets google account information
	 * @pre pEmail != null;
	 * @post true;
	 * @param pEmail - google account email
	 */
	public Account(String pEmail)
	{
		this.userAccessToken = null;
		this.userEmail = pEmail;
		this.userFriends = new ArrayList<String>();
		this.pendingFriends = new ArrayList<String>();
		this.userCurrentEvents = new ArrayList<Long>();
		this.userPastEvents = new ArrayList<Long>();
		this.userNotifications = new ArrayList<Long>();
		this.userAge = "--";
		this.userTitle = "Newbie";
	}

	/**
	 * Retrieve the user's email address.
	 * @pre true;
	 * @post true;
	 * @return the user's email address.
	 */
	public String getUserEmail()
	{
		return this.userEmail;
	}

	/**
	 * Set the user's email address.
	 * @pre userEmail != null;
	 * @post this.userEmail.equals(userEmail);
	 * @param userEmail  - the email address of the user.
	 */
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}
	
	/**
	 * Gets the URI that logs out user from google accounts.
	 * @pre true;
	 * @post true;
	 * @return the stored URI to display in an anchor.
	 */
	public String getLogoutUrl()
	{
		return this.userLogoutUrl;
	}

	/**
	 * Stores the URI that logs out user from google accounts.
	 * @pre pUri != null;
	 * @post this.userLogoutUrl.equals(pUri);
	 * @param pUri - the URI string to store, links to google accounts.
	 */
	public void setLogoutUrl(String pUri)
	{
		this.userLogoutUrl = pUri;
	}
	
	/**
	 * Get the user's refresh token for access to their Google calendar.
	 * @pre true;
	 * @post true;
	 * @return the user's refresh token.
	 */
	public String getAccessToken()
	{
		return this.userAccessToken;
	}
	
	/**
	 * Set the user's refresh token for access to their Google calendar
	 * @pre userRefreshToken != null;
	 * @post this.userRefreshToken.equals(userRefreshToken);
	 * @param userRefreshToken - the user's refresh token.
	 */
	public void setAccessToken(String userRefreshToken)
	{
		this.userAccessToken = userRefreshToken;
	}
	
	/**
	 * Get the user's full name.
	 * @pre true;
	 * @post true;
	 * @return the full name of the user.
	 */
	public String getUserFullName() 
	{
		return this.userFullName;
	}
	
	/**
	 * Set the user's full name.
	 * @pre userFullName != null;
	 * @post this.userFullName.equals(userFullName);
	 * @param userFullName - the user's full name.
	 */
	public void setUserFullName(String userFullName)
	{
		this.userFullName = userFullName;
	}
	
	/**
	 * Gets the user's gender.
	 * @pre true;
	 * @post true;
	 * @return the gender of the user.
	 */
	public String getGender()
	{
		return gender;
	}

	/**
	 * Sets the user's gender.
	 * @pre true;
	 * @post true;
	 * @param gender - the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}

	/**
	 * Gets the user's birth date.
	 * @pre true;
	 * @post true;
	 * @return the birth date of the user.
	 */
	public String getBirthDate()
	{
		return birthDate;
	}

	/**
	 * Sets the user's birth date.
	 * @pre true;
	 * @post true;
	 * @param birthDate - the birth date to set
	 */
	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	/**
	 * Get the url to user's profile picture.
	 * @pre true;
	 * @post true;
	 * @return the url to the user's profile picture.
	 */
	public String getUserProfilePicture() 
	{
		return this.userProfilePicture;
	}
	
	/**
	 * Set the url to user's profile picture.
	 * @pre userProfilePicture != null;
	 * @post this.userProfilePicture == userProfilePicture;
	 * @param userProfilePicture - url to user's profile picture.
	 */
	public void setUserProfilePicture(String userProfilePicture)
	{
		this.userProfilePicture = userProfilePicture;
	}

	/**
	 * Retrieve the list of friends (IDs) the user has.
	 * @pre true;
	 * @post true;
	 * @return the list of friends the user has.
	 */
	public List<String> getUserFriends()
	{
		return this.userFriends;
	}

	/**
	 * Adds a friend to the user's friends list.
	 * @pre pId != null;
	 * @post this.userFriends.size() == @pre.this.userFriends.size() + 1;
	 * @param pId - the id of another user Account.
	 */
	public void addUserFriend(String pId)
	{
		this.userFriends.add(pId);
	}

	/**
	 * Retrieve the list of current events (IDs) the user has.
	 * @pre true;
	 * @post true;
	 * @return the list of current events the user has. 
	 */
	public List<Long> getCurrentEvents()
	{
		return this.userCurrentEvents;
	}

	/**
	 * Retrieve the list of past events (IDs) the user has.
	 * @pre true;
	 * @post true;
	 * @return the list of past events the user has. 
	 */
	public List<Long> getPastEvents()
	{
		return this.userPastEvents;
	}
	
	/**
	 * Adds an event to the user's event list.
	 * @pre pId != null;
	 * @post true;
	 * @param pId - the id of an Event.
	 */
	public void addUserEvent(Long pId)
	{
		this.userCurrentEvents.add(pId);
	}

	/**
	 * Gets the pending list of friends.
	 * @pre true;
	 * @post true;
	 * @return returns the pending list of friends.
	 */
	public List<String> getPendingFriends()
	{
		return this.pendingFriends;
	}

	/**
	 * Add user to the pending friend request list.
	 * @pre pendingUser != null;
	 * @post true;
	 * @param pendingUser - the user to add to the pending friend request list.
	 * @return true if the user was successfully added; false otherwise.
	 */
	public boolean addPendingRequest(String pendingUser)
	{
		return this.pendingFriends.add(pendingUser);
	}

	/**
	 * This moves user from the pending list into the friend list.
	 * @pre pendingUser != null;
	 * @post this.userFriends.size() == @pre.this.userFriends.size() + 1 &&
	 * 		 this.pendingFriends.size() == @pre.this.userFriends.size() - 1; 
	 * @param userID - the pending user that will be accepted as a friend.
	 * @return true if transfer of friends was successful; false otherwise.
	 */
	public boolean confirmFriendRequest(String userID)
	{
		this.pendingFriends.remove(userID);
		return this.userFriends.add(userID);
	}

	/**
	 * This denies the friend request.
	 * @pre pendingUser != null;
	 * @post true;
	 * @param pendingUser - the pending user that will be denied as a friend.
	 * @return true if operation successful; otherwise false.
	 */
	public boolean denyFriendRequest(String pendingUser)
	{
		return this.pendingFriends.remove(pendingUser);
	}
	
	/**
	 * Adds the new notification at the start of the list and shifts
	 * the rest of the elements to the right.
	 * @pre notification > 0;
	 * @post true;
	 * @param notification - the ID of the notification.
	 */
	public void addUserNotification(Long notification)
	{
		this.userNotifications.add(0, notification);
	}
	
	/**
	 * Retrieve the list of the user's notifications (IDs).
	 * @pre true;
	 * @post true;
	 * @return the list of the user's notifications.
	 */
	@Override
	public List<Long> getUserNotifications()
	{
		return this.userNotifications;
	}
	
	/**
	 * Retrieve the age of the user.
	 * @pre true;
	 * @post true;
	 * @return the age of the user.
	 */
	public String getUserAge() 
	{
		return this.userAge;
	}

	/**
	 * Set the age of the user.
	 * @pre userAge > 0;
	 * @post this.userAge == userAge;
	 * @param userAge - the age of the user to set.
	 */
	public void setUserAge(String userAge)
	{
		this.userAge = userAge;
	}

	/**
	 * Retrieve the uesr's phone number.
	 * @pre true;
	 * @post true;
	 * @return the phone number of the user.
	 */
	public String getUserPhone()
	{
		return this.userPhone;
	}

	/**
	 * Set the phone number for the user.
	 * @pre userPhone != null;
	 * @post this.userPhone.equals(userPhone);
	 * @param userPhone - the phone number for the user to set.
	 */
	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}
	
	/**
	 * Retrieve the title (Achievement title) of the user. 
	 * @pre true;
	 * @post true;
	 * @return the title of the user.
	 */
	public String getUserTitle()
	{
		return this.userTitle;
	}

	/**
	 * 
	 * @pre
	 * @post
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.userTitle = title;
		
	}
	
	/**
	 * 
	 */
	@Override
	public int compare(AccountModel o1, AccountModel o2)
	{
		return o1.getUserFullName().compareToIgnoreCase(o2.getUserFullName());
	}

	/**
	 * 
	 * @pre
	 * @post
	 * @param eventId
	 */
	public void removeUserEvent(Long eventId)
	{
		this.userCurrentEvents.remove(eventId);
	}
	
}
