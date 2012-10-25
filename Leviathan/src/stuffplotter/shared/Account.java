package stuffplotter.shared;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;


/**
 * The Account Class holds information for individual users.
 * Information includes profile, events, and statistics.
 */

@Entity
public class Account implements Serializable {
	@Id private String userId;
	
	/* Session information */
	private boolean userSession = false;
	private String userLogin;
	private String userLogout;
	
	/* Basic information */
	private String userName;
	private int userAge;
	private String userEmail;
	private String userPhone;
	
	/* Level information */
	private int userLevel;
	private long userExperience;
	
	/* Achievement information */
	private List<Enum> userAchievements; 

	/* Custom information */
	private String userTitle;
	
	/** 
	 * Empty Account Constructor 
	 */
	public Account() {
		// do nothing
	}
	
	/**
	 * Account Constructor
	 * @param pId - google account
	 * @param pName - google nickname
	 * @param pEmail - google email
	 */
	public Account(String pId, String pName, String pEmail) {
		userId = pId;
		userName = pName;
		userEmail = pEmail;
	}
	
	/**
	 * This method determines if user is in a session.
	 * @return true if user is in a session, false otherwise
	 */
	public boolean inSession() {
		return userSession;
	}
	
	/**
	 * This method sets the session for the account.
	 * @param pSession - true to start session, false to end session
	 */
	public void setSession(boolean pSession) {
		userSession = pSession;
	}
	
	/**
	 * This method returns the login uri for the account.
	 * @return the login uri for the account
	 */
	public String getLogin() {
		return userLogin;
	}
	
	/**
	 * This method sets the login uri for the account.
	 * @param pUri - the login uri for the account
	 */
	public void setLogin(String pUri) {
		userLogin = pUri;
	}
	
	/**
	 * This method returns the logout uri for the account.
	 * @return the logout uri for the account
	 */
	public String getLogout() {
		return userLogout;
	}
	
	/**
	 * This method sets the logout uri for the account.
	 * @param pUri - the logout uri for the account
	 */
	public void setLogout(String pUri) {
		userLogout = pUri;
	}
	
	

}
