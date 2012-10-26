package stuffplotter.shared;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.googlecode.objectify.annotation.Entity;


/**
 * The Account Class holds information for individual users.
 * Information includes profile, events, and statistics.
 */

@Entity
public class Account implements Serializable {
	@Id private String userId;
	
	/* Session information */
	@Transient private boolean userSession;
	@Transient private String userLogin;
	@Transient private String userLogout;
	
	/* Basic information */
	private String userName;
	private String userEmail;
	private int userAge;
	private String userPhone;
	
	/* Event information */
	private List<Long> userEvents;
	
	/* Record information */
	private int userLevel;
	private long userExperience;
	private List<Achievement> userAchievements; 

	/* Custom information */
	private String userTitle;
	
	/** 
	 * Account Constructor 
	 */
	public Account() {
		// do nothing
	}
	
	/**
	 * Account Constructor that clones a google account's information
	 * @param pId 		google account id
	 * @param pName 	google account nickname
	 * @param pEmail	google account email
	 */
	public Account(String pId, String pName, String pEmail) {
		this.userId = pId;
		this.userName = pName;
		this.userEmail = pEmail;
	}
	
	/**
	 * Determines if user is in a google account session.
	 * @return 	true if user is in a session
	 * 			false otherwise
	 */
	public boolean inSession() {
		return userSession;
	}
	
	/**
	 * Flags whether user is in a google account session.
	 * @param pBool		true if user is starting a session
	 * 					false if user is ending a session
	 */
	public void setSession(boolean pBool) {
		userSession = pBool;
	}
	
	/**
	 * Retrieves the URI that redirects user to google accounts
	 * @return 	the URI string to display in an anchor
	 */
	public String getLogin() {
		return userLogin;
	}
	
	/**
	 * Stores the URI that redirects user to google accounts
	 * @param pUri	the URI string to store, links to google accounts
	 */
	public void setLogin(String pUri) {
		userLogin = pUri;
	}
	
	/**
	 * Retrieves the URI that logs out user from google accounts
	 * @return the stored URI to display in an anchor
	 */
	public String getLogout() {
		return userLogout;
	}
	
	/**
	 * Stores the URI that logs out user from google accounts
	 * @param pUri	the URI string to store, links to google accounts
	 */
	public void setLogout(String pUri) {
		userLogout = pUri;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
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
	 * @return the userEvents
	 */
	public List<Long> getUserEvents() {
		return userEvents;
	}

	/**
	 * @param userEvents the userEvents to set
	 */
	public void setUserEvents(List<Long> userEvents) {
		this.userEvents = userEvents;
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
	public long getUserExperience() {
		return userExperience;
	}

	/**
	 * @param userExperience the userExperience to set
	 */
	public void setUserExperience(long userExperience) {
		this.userExperience = userExperience;
	}

	/**
	 * @return the userAchievements
	 */
	public List<Achievement> getUserAchievements() {
		return userAchievements;
	}

	/**
	 * @param userAchievements the userAchievements to set
	 */
	public void setUserAchievements(List<Achievement> userAchievements) {
		this.userAchievements = userAchievements;
	}
	
	

}
