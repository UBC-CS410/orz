/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		List<String> submitters = new ArrayList<String>();
		
		Scheduler sch = new Scheduler();
		Long id = null;
		assertNotNull(sch);
		assertEquals(availabilities, sch.getAvailabilities());
		assertEquals(id,sch.getId());
		assertEquals(submitters,sch.getSubmitters());
	}

	@Test
	public void testAddAvail()
	{

		
		Scheduler sch = new Scheduler();
		Date day = new Date();
		Date day2 = new Date();
		Availability pAvailability = new Availability(day);
		Availability pAvailability2 = new Availability(day2);
		
		sch.addAvailability(pAvailability);
		sch.addAvailability(pAvailability2);
		assertEquals(2, sch.getAvailabilities().size());
	}
	
	@Test
	public void testAddSubmitter()
	{
		String userId1 = "1000";
		String userId2 = "2000";
		Scheduler sch = new Scheduler();
		
		sch.addSubmitter(userId1);
		sch.addSubmitter(userId2);
		
		assertTrue(sch.getSubmitters().contains(userId1));
		assertEquals(2,sch.getSubmitters().size());
	}
}
