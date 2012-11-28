/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.EventNotification;
import stuffplotter.shared.EventNotification.EventNotificationType;
import stuffplotter.shared.FriendNotification;
import stuffplotter.shared.FriendNotification.FriendNotificationType;
import stuffplotter.shared.Notification.NotificationType;

/**
 * @author farez
 *
 */
public class FriendNotificationTest extends TestCase
{

	@Test
	public void testFriendNotificationCtor()
	{
		String from = "from";
		String foruser = "for";
		FriendNotificationType type = FriendNotificationType.FRIENDREQUEST;
		
		
		FriendNotification n = new FriendNotification(type,foruser,from);
		
		assertNotNull(n);
		assertEquals(type,n.getFriendType());
		assertEquals(from,n.getFrom());
	}

	@Test
	public void testSetters()
	{
		String from = "from";
		String foruser = "for";
		FriendNotificationType type = FriendNotificationType.FRIENDACCEPTED;
		
		
		FriendNotification n = new FriendNotification(type,foruser,from);
		n.setNotificationDisplay(type, from);
		assertEquals(type,n.getFriendType());
		n.setFriendType(type);
		assertEquals(type,n.getFriendType());
		
	}
	
}
