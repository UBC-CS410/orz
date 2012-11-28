/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.Achievement;
import stuffplotter.shared.AchievementNotification;



/**
 *
 *
 */
public class AchievementNotificationTest extends TestCase
{

	@Test
	public void testAchievementNotificaionCtor()
	{
		Achievement ach = Achievement.ADD_FIRST_FRIEND;
		String user = "user";
	
		AchievementNotification an = new AchievementNotification(ach,user);
		
		assertNotNull(an);
	}

	@Test 
	public void testSetNotificationDisplay()
	{
		
		Achievement ach = Achievement.ADD_FIRST_FRIEND;
		String user = "user";
		
		AchievementNotification an = new AchievementNotification(ach,user);
		an.setNotificationDisplay(ach);
		assertEquals("You unlocked the achievement, "+ach.getName(),an.getNotificationDisplay());
	}
}
