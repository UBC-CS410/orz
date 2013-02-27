/**
 * 
 */
package tests.shared;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.Achievement;

/**
 *
 *
 */
public class AchievementTest extends TestCase
{

	@Test
	public void testAchievementCtor()
	{
		int achId = 1000;
        String name = "Achievement Name";
    	String desc = "You've achieved something";
    	String msg = "You're awesome";
    	
    	Achievement a = Achievement.FIRST_LOG_IN;
    	/* FIRST_LOG_IN(0, "Stuff Beginner", "Login for the first time", "Welcome to stuff plotter!", "images/beginner.png"),
		   Note: this line is just for reference.
    	*/
    	
    	assertNotNull(a);
    	assertEquals(a.getAchId(), 0);
    	assertEquals(a.getName(), "Stuff Beginner");
    	assertEquals(a.getDesc(), "Login for the first time");
    	assertEquals(a.getMsg(), "Welcome to stuff plotter!");
    	assertEquals(a.getImg(), "images/beginner.png");
    	
	}

}
