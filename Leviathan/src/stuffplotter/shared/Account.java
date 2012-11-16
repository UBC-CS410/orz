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
	private String userName;
	private String userEmail;
	private int userAge;
	private String userPhone;
	
	/* Google information */
	private boolean userAccessPermission = false;
	private String userFullName;
	private String userProfilePicture;

	/* Social information */
	private List<String> userFriends = new ArrayList<String>();
	private List<String> pendingFriends = new ArrayList<String>();
	private List<Long> userEvents = new ArrayList<Long>();
	private List<Notification> userNotifications = new ArrayList<Notification>();

	/* Record information */
	private int userLevel;
	private int userExperience;
	private List<Achievement> userAchievements; 

	/* Custom information */
	private String userTitle;
	
	/* Achievement information */
	private int numberOfLogins;
	private int numberOfHostedEvents;
	private int numberOfParticipatedEvents;	

	/** 
	 * Account Constructor 
	 */
	public Account() {
		// do nothing
		this.userAchievements = new ArrayList<Achievement>();
	}

	/**
	 * Account Constructor that sets google account information
	 * @param pId 		google account id
	 * @param pName 	google account nickname
	 * @param pEmail	google account email
	 */
	public Account(String pName, String pEmail)
	{
		this.userName = pName;
		this.userEmail = pEmail;

		this.userFriends = new ArrayList<String>();
		this.userEvents = new ArrayList<Long>();
		this.userNotifications = new ArrayList<Notification>();

		this.userLevel = 0;
		this.userExperience = 0;
		this.userAchievements = new ArrayList<Achievement>();
		
		this.userTitle = "Newbie";
		
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
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
	 * Determine whether user gave permission to access their Google profile information.
	 * @pre true;
	 * @post true;
	 * @return true if yes or false if no
	 */
	public boolean getUserAccessPermission()
	{
		return this.userAccessPermission;
	}
	
	/**
	 * Marks whether user gave permission to access their Google profile information
	 * @pre true;
	 * @post this.userAccessPermission == userAccessPermission;
	 * @param true if yes or false if no
	 */
	public void setUserAccessPermission(boolean userAccessPermission)
	{
		this.userAccessPermission = userAccessPermission;
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
	 * @return the userLevel
	 */
	public int getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel the userLevel to set
	 */
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * @return the userExperience
	 */
	public int getUserExperience() {
		return userExperience;
	}

	/**
	 * @param userExperience the userExperience to set
	 */
	public void setUserExperience(int userExperience) {
		this.userExperience = userExperience;
	}

	/**
	 * @return the userAchievements
	 */
	public List<Achievement> getUserAchievements() {
		return this.userAchievements;
	}

	/**
	 * @param userAchievements the userAchievements to set
	 */
	public void setUserAchievements(List<Achievement> userAchievements) {
		this.userAchievements = userAchievements;							//add user achievement here?
	}
	
	/**
	 * This method adds achievements as well as checks for duplicates
	 * @pre
	 * @post
	 * @param achievements to be added
	 * @return
	 */
	public boolean addUserAchievements(List<Achievement> achievements)
	{
		for(int i = 0; i<achievements.size(); i++)
		{
			if(this.userAchievements.contains(achievements.get(i)))
				achievements.remove(i);
		}
		
		return this.userAchievements.addAll(achievements);
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
	
	public void accept(RecordVisitor visitor)
	{
		visitor.visit(this);
	}
	
	
	public List<Notification>  getUserNotifications()
	{
		return this.userNotifications;
	}
	
	public String getUserTitle()
	{
		return this.userTitle;
	}

	public int getNumberOfLogins()
	{
		return numberOfLogins;
	}

	public void setNumberOfLogins(int numberOfLogins)
	{
		this.numberOfLogins = numberOfLogins;
	}


	public int getNumberOfParticipatedEvents()
	{
		return numberOfParticipatedEvents;
	}

	public void setNumberOfParticipatedEvents(int numberOfParticipatedEvents)
	{
		this.numberOfParticipatedEvents = numberOfParticipatedEvents;
	}

	public int getNumberOfHostedEvents()
	{
		return numberOfHostedEvents;
	}

	public void setNumberOfHostedEvents(int numberOfHostedEvents)
	{
		this.numberOfHostedEvents = numberOfHostedEvents;
	}



}
