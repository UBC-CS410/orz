/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.DayContainer;

/**
 * @author farez
 *
 */
public class DayContainerTest extends TestCase
{

	@Test
	public void testDayContainerCtor()
	{
		String day = "Saturday";
		List<Integer> timeSlots = Arrays.asList(1,2,3);
		
		DayContainer dc = new DayContainer(day,timeSlots);
		
		assertNotNull(dc);
		assertEquals(day, dc.getDay());
		assertEquals(timeSlots, dc.getTimeSlots());
		
	}

}
