/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * JUnit test class for Account.java
 *
 */
public class AccountTest extends TestCase
{

	/**
	 * @pre
	 * @post
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * Verify that the instance fields in the Account class are set correctly
	 */
	public void testAccountCtor() {
		String userEmail = "john@example.com";
		ArrayList<String> userFriends = new ArrayList<String>();
		ArrayList<Long> userCurrentEvents = new ArrayList<Long>();
		ArrayList<Long> userPastEvents = new ArrayList<Long>();
		userPastEvents.add(1000L);
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
	public void testSimple() //simple test.
	{
		assertTrue(true);
	}

}
