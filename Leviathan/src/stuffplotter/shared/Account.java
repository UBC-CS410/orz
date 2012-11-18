package stuffplotter.shared;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import stuffplotter.server.RecordVisitor;

import com.googlecode.objectify.annotation.Entity;


/**
 * The Account Class holds information for individual users.
 * Information includes profile, events, and statistics.
 */

@Entity
public class Account implements Serializable
{
	/* Session information */
	@Transient private String userLoginUrl;
	@Transient private String userLogoutUrl;

	/* Basic information */
	@Id
	private String userEmail;
	private int userAge;
	private String userPhone;
	
	/* Google information */
	private String userRefreshToken;
	private String userFullName = null;
	private String userProfilePicture;

	/* Social information */
	private List<String> userFriends = new ArrayList<String>();
	private List<String> pendingFriends = new ArrayList<String>();
	private List<Long> userEvents = new ArrayList<Long>();
	private List<Notification> userNotifications = new ArrayList<Notification>();

	/* Custom information */
	private String userTitle;

	/** 
	 * Account Constructor 
	 */
	public Account() {
		// do nothing
	}

	/**
	 * Account Constructor that sets google account information
	 * @param pEmail	google account email
	 * @param pId 		google account id
	 */
	public Account(String pEmail)
	{
		this.userEmail = pEmail;

		this.userFriends = new ArrayList<String>();
		this.userEvents = new ArrayList<Long>();
		this.userNotifications = new ArrayList<Notification>();
		this.userTitle = "Newbie";

	}

	/**
	 * Retrieves the URI that redirects user to google accounts
	 * @return 	the URI string to display in an anchor
	 */
	public String getLoginUrl() {
		return userLoginUrl;
	}

	/**
	 * Stores the URI that redirects user to google accounts
	 * @param pUri	the URI string to store, links to google accounts
	 */
	public void setLoginUrl(String pUri) {
		userLoginUrl = pUri;
	}

	/**
	 * Retrieves the URI that logs out user from google accounts
	 * @return the stored URI to display in an anchor
	 */
	public String getLogoutUrl() {
		return userLogoutUrl;
	}

	/**
	 * Stores the URI that logs out user from google accounts
	 * @param pUri	the URI string to store, links to google accounts
	 */
	public void setLogoutUrl(String pUri) {
		userLogoutUrl = pUri;
	}


	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userAge
	 */
	public int getUserAge() {
		return userAge;
	}

	/**
	 * @param userAge the userAge to set
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	/**
	 * Get the user's refresh token for access to their Google calendar.
	 * @pre true;
	 * @post true;
	 * @return userRefreshToken
	 */
	public String getUserRefreshToken()
	{
		return this.userRefreshToken;
	}
	
	/**
	 * Set the user's refresh token for access to their Google calendar
	 * @pre true;
	 * @post this.userRefreshToken == userRefreshToken;
	 * @param userRefreshToken
	 */
	public void setUserRefreshToken(String userRefreshToken)
	{
		this.userRefreshToken = userRefreshToken;
	}
	
	/**
	 * Get the user's full name.
	 * @pre true;
	 * @post true;
	 * @return userFullName
	 */
	public String getUserFullName() 
	{
		return this.userFullName;
	}
	
	/**
	 * Set the user's full name
	 * @pre userFullName != null;
	 * @post this.userFullName == userFullName;
	 * @param String containing user's full name
	 */
	public void setUserFullName(String userFullName)
	{
		this.userFullName = userFullName;
	}
	
	/**
	 * Get the url to user's profile picture.
	 * @pre true;
	 * @post true;
	 * @return userProfilePicture
	 */
	public String getUserProfilePicture() 
	{
		return this.userProfilePicture;
	}
	
	/**
	 * Set the url to user's profile picture
	 * @pre userProfilePicture != null;
	 * @post this.userProfilePicture == userProfilePicture;
	 * @param String containing url to user's profile picture
	 */
	public void setUserProfilePicture(String userProfilePicture)
	{
		this.userProfilePicture = userProfilePicture;
	}

	/**
	 * @return the userFriends
	 */
	public List<String> getUserFriends() {
		return userFriends;
	}

	/**
	 * Adds a friend to the user's friends list
	 * @param pId	the id of another user Account
	 */
	public void addUserFriend(String pId) {
		this.userFriends.add(pId);
	}

	/**
	 * @return the userEvents
	 */
	public List<Long> getUserEvents() {
		return userEvents;
	}

	/**
	 * Adds an event to the user's event list
	 * @param pId	the id of an Event
	 */
	public void addUserEvent(Long pId) {
		this.userEvents.add(pId);
	}

	/**
	 * Gets the pending list of friends
	 * @return returns the pending list of friends
	 */
	public List<String> getPendingFriends(){
		return this.pendingFriends;
	}

	/**
	 * This adds the pending user to thier pending list.
	 * @param pendingUser
	 * @return
	 */
	public boolean addPendingRequest(String pendingUser){
		return this.pendingFriends.add(pendingUser);
	}

	/**
	 * This moves user from the pending list into the friend list
	 * 
	 * @param pendingUser The pending user that will be accepted as a friend
	 * @return returns true if transfer of friends was successful
	 */
	public boolean acceptFriendRequest(String pendingUser){
		if(this.pendingFriends.remove(pendingUser)){
			this.userFriends.add(pendingUser);
			return true;
		}else{
			return false;
		}	

	}

	/**
	 * This denies the friend request
	 * 
	 * @param pendingUser The pending user that will be denied as a friend
	 * @return returns true if successful
	 */
	public boolean denyFriendRequest(String pendingUser){
		return this.pendingFriends.remove(pendingUser);
	}


	/**
	 * This confirms a friend requests by removing the user from the pending
	 * list into the friend list
	 * 
	 * 
	 * @pre The confirmed user must be in the pending list
	 * @post The confirmed user is now in the friends list
	 * @param userId
	 * @return true if successful, false otherwise
	 */
	public boolean confirmFriendReq(String userId)
	{
		this.pendingFriends.remove(userId);
		return this.userFriends.add(userId);

	}
	
	
	public List<Notification>  getUserNotifications()
	{
		return this.userNotifications;
	}
	
	public String getUserTitle()
	{
		return this.userTitle;
	}

}
