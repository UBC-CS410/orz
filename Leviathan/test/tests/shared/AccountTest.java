/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;



import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;

/**
 * JUnit test class for Account.java
 *
 */
public class AccountTest extends TestCase
{
	/**
	 * Verify that the instance fields in the Account class are set correctly
	 */
	public void testAccountCtorNoArgs() 
	{
		List<String> userFriends = new ArrayList<String>();
		List<String> pendingFriends = new ArrayList<String>();
		List<Long> userCurrentEvents = new ArrayList<Long>();
		List<Long> userPastEvents = new ArrayList<Long>();
		List<Long> userNotifications = new ArrayList<Long>();
		
		Account ac = new Account();
		
		assertNotNull(ac);
		assertEquals(userFriends,ac.getUserFriends());
		assertEquals(pendingFriends,ac.getPendingFriends());
		assertEquals(userCurrentEvents,ac.getCurrentEvents());
		assertEquals(userPastEvents, ac.getPastEvents());
		assertEquals(userNotifications, ac.getUserNotifications());
	}
	
	public void testAccountCtor() {
		String userEmail = "john@example.com";
		ArrayList<String> userFriends = new ArrayList<String>();
		ArrayList<Long> userCurrentEvents = new ArrayList<Long>();
		ArrayList<Long> userPastEvents = new ArrayList<Long>();
		//userPastEvents.add(1000L);
		ArrayList<Notification> userNotifications = new ArrayList<Notification>();
		String userTitle = "Newbie";

		
		Account ac = new Account(userEmail);
		assertNotNull(ac);
		assertEquals(userEmail, ac.getUserEmail());
		assertEquals(userFriends, ac.getUserFriends());
		assertEquals(userCurrentEvents, ac.getCurrentEvents());
		assertEquals(userPastEvents, ac.getPastEvents());
		assertEquals(userNotifications, ac.getUserNotifications());
		assertEquals(userTitle, ac.getUserTitle());

	}
	
	@Test
	public void testGetters()
	{
		/* Session information */
		String userLoginUrl = null;
		String userLogoutUrl = null;

		/* Basic information */
		
		String userAge = "";
		String userPhone = null;
		
		/* Google information */
		String userAccessToken = null;
		String userFullName = null;
		String userProfilePicture = null;
		
		Account ac = new Account();
		assertNotNull(ac);
		
		assertEquals(userLogoutUrl,ac.getLogoutUrl());
		
		assertEquals(userAge,ac.getUserAge());
		assertEquals(userPhone,ac.getUserPhone());
		
		assertEquals(userAccessToken,ac.getAccessToken());
		assertEquals(userFullName,ac.getUserFullName());
		assertEquals(userProfilePicture,ac.getUserProfilePicture());
		
	}
	
	@Test
	public void testSetters()
	{	

		
		Account ac = new Account();

		ac.setLogoutUrl("logout");
		
		ac.setUserEmail("newemail");
		ac.setUserAge("20");
		ac.setUserPhone("1800NoName");
		
		ac.setAccessToken("newAccessToken");
		ac.setUserFullName("FullName");
		ac.setUserProfilePicture("ProfilePic");
		
		ac.setTitle("NewGuy");
		
		assertEquals("logout",ac.getLogoutUrl());
		
		assertEquals("newemail",ac.getUserEmail());
		assertEquals("20",ac.getUserAge());
		assertEquals("1800NoName",ac.getUserPhone());
		
		assertEquals("newAccessToken",ac.getAccessToken());
		assertEquals("FullName",ac.getUserFullName());
		assertEquals("ProfilePic",ac.getUserProfilePicture());
	}
	
	
	@Test
	public void testSimple() //simple test.
	{
		assertTrue(true);
	}

}
