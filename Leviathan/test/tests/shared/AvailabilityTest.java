/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.Availability;

/**
 * 
 *
 */
public class AvailabilityTest extends TestCase
{

	
	@Test
	public void testAvailabilityCtor()
	{
		Date time = new Date();
		int votes = 0;
		
		Availability av = new Availability(time);
		
		assertNotNull(av);
		assertEquals(time, time);
		assertEquals(votes, 0);
	}

	@Test
	public void testIncrementVote() {
		int voteinc = 1;
		Date time = new Date();
		
		Availability av = new Availability(time);
		av.incrementVote();
		
		//assertEquals(voteinc, )
	}
}
