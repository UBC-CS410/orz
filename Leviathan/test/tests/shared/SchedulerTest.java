/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import stuffplotter.shared.Scheduler;

/**
 * 
 *
 */
public class SchedulerTest
{

	@Test
	public void testSchedulerCtor()
	{
		ArrayList<Long> availabilities = new ArrayList<Long>();
		
		Scheduler sch = new Scheduler();
		
		assertNotNull(sch);
		assertEquals(availabilities, sch.getAvailabilities());
	}

}
