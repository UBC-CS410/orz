/**
 * 
 */
package tests.shared;

import junit.framework.TestCase;
import org.junit.Test;
import stuffplotter.shared.EventNotification;
import stuffplotter.shared.EventNotification.EventNotificationType;
import stuffplotter.shared.Notification.NotificationType;

/**
 * @author farez
 *
 */
public class EventNotificationTest extends TestCase
{

	@Test
	public void testEventNotificationCtor()
	{
		String from = "from";
		String foruser = "for";
		EventNotificationType type = EventNotificationType.EVENTINVITE;
		NotificationType ntype = NotificationType.EVENTINVITATION;
		
		EventNotification n = new EventNotification(type,foruser,from);
		
		assertNotNull(n);
		assertEquals(ntype,n.getType());
		assertEquals(from,n.getFrom());
	}
}
