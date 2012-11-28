/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.AchievementNotification;
import stuffplotter.shared.EventNotification;
import stuffplotter.shared.EventNotification.EventNotificationType;
import stuffplotter.shared.Notification;
import stuffplotter.shared.Notification.NotificationType;

/**
 * 
 *
 */
public class NotificationTest extends TestCase
{

	@Test
	public void testNotificationCtorNoArgs()
	{	
			
		Notification n = new AchievementNotification();
		
		assertNotNull(n);
		assertEquals("",n.getNotificationDisplay());
		
	}
	
	
	@Test
	public void testGetters()
	{
		Long notificationId = null;
		NotificationType type = null;
		String notificationFor;
		String notificationFrom = null;
		boolean newNotification = true;
		Date notificationTime = new Date();
		
		Notification n = new AchievementNotification();
		
		assertNotNull(n);
		
		assertEquals(notificationId,n.getNotificationId());
		assertEquals(type,n.getType());
		assertEquals(notificationFrom,n.getFrom());
		assertEquals(newNotification,n.getNewNotification());
		assertEquals(notificationTime,n.getNotificationTime());
		
	}

	@Test
	public void testSetters()
	{
		Notification n = new AchievementNotification();
		boolean bool = false;
		n.setNewNotification(bool);
		assertEquals(false,n.getNewNotification());
	}
	
	@Test
	public void testEquals() 
	{
		Notification n = new AchievementNotification();
		
		boolean bool = n.equals(n);
		assertTrue(bool);
	}
	
	@Test
	public void testHashCode()
	{
		Notification n = new AchievementNotification();
		
		
		assertTrue(n.hashCode() != 0);
	}
	
}
