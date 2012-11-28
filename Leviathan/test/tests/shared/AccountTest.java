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
		String gender = "male";
		String birthDay = "Jan1";
		
		Account ac = new Account();

		ac.setLogoutUrl("logout");
		
		ac.setUserEmail("newemail");
		ac.setUserAge("20");
		ac.setUserPhone("1800NoName");
		
		ac.setAccessToken("newAccessToken");
		ac.setUserFullName("FullName");
		ac.setUserProfilePicture("ProfilePic");
		
		ac.setTitle("NewGuy");
		
		ac.setGender(gender);
		ac.setBirthDate(birthDay);
		
		assertEquals("logout",ac.getLogoutUrl());
		
		assertEquals("newemail",ac.getUserEmail());
		assertEquals("20",ac.getUserAge());
		assertEquals("1800NoName",ac.getUserPhone());
		
		assertEquals("newAccessToken",ac.getAccessToken());
		assertEquals("FullName",ac.getUserFullName());
		assertEquals("ProfilePic",ac.getUserProfilePicture());
		
		assertEquals("male", ac.getGender());
		assertEquals("Jan1",ac.getBirthDate());
		
	}
	
	
	@Test
	public void testAdders()
	{
		String id1 = "1";
		String id2 = "2";
		String id3 = "3";
		
		Long l1 = 100L;
		Long l2 = 200L;
		Long l3 = 300L;
		
		String pu1 = "John";
		String pu2 = "Dan";
		
		Account ac = new Account();
		
		ac.addUserFriend(id1);
		ac.addUserFriend(id2);
		ac.addUserFriend(id3);
		
		ac.addUserEvent(l1);
		ac.addUserEvent(l2);
		ac.addUserEvent(l3);
		
		ac.addPendingRequest(pu1);
		ac.addPendingRequest(pu2);
		
		ac.addUserNotification(l1);
		ac.addUserNotification(l2);
		
		assertEquals(3,ac.getUserFriends().size());
		assertEquals(3,ac.getCurrentEvents().size());
		assertEquals(2,ac.getPendingFriends().size());
		assertEquals(2,ac.getUserNotifications().size());
		
		ac.removeUserEvent(l2);
		assertEquals(2,ac.getCurrentEvents().size());
	}

	@Test
	public void testDenyandConfirm()
	{
		String id1 = "1";
		String id2 = "2";
		String id3 = "3";
		
		String pu1 = "John";
		String pu2 = "Dan";
		
		Account ac = new Account();
		ac.confirmFriendRequest(id1);
		ac.confirmFriendRequest(id2);
		ac.confirmFriendRequest(id3);
		
		assertEquals(3,	ac.getUserFriends().size());
		
		ac.addPendingRequest(pu1);
		ac.addPendingRequest(pu2);
		
		assertEquals(2,ac.getPendingFriends().size());
		
		ac.denyFriendRequest(pu1);
		
		assertEquals(1,ac.getPendingFriends().size());
	}
	
}
