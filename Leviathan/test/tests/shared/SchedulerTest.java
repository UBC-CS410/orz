/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.Availability;
import stuffplotter.shared.Scheduler;

/**
 * 
 *
 */
public class SchedulerTest extends TestCase
{

	@Test
	public void testSchedulerCtor()
	{
		ArrayList<Long> availabilities = new ArrayList<Long>();
		
		Scheduler sch = new Scheduler();
		Long id = null;
		assertNotNull(sch);
		assertEquals(availabilities, sch.getAvailabilities());
		assertEquals(id,sch.getId());
	}

	@Test
	public void testAddAvail()
	{
		ArrayList<Long> availabilities = new ArrayList<Long>();
		
		Scheduler sch = new Scheduler();
		Date day = new Date();
		Date day2 = new Date();
		Availability pAvailability = new Availability(day);
		Availability pAvailability2 = new Availability(day2);
		
		sch.addAvailability(pAvailability);
		assertEquals(2, sch.getAvailabilities().size());
	}
}
