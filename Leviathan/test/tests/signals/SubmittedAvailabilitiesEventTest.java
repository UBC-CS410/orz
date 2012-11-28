/**
 * 
 */
package tests.signals;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import stuffplotter.signals.EventSchedulerEvent;

/**
 * Junit tests for SubmittedAvailabilitiesEvent.java
 *
 */
public class SubmittedAvailabilitiesEventTest
{
	
	/**
	 * Verify that the instance fields in the class are set correctly
	 */
	@Test
	public void testSAECtor()
	{
		Long schedulerId = null;
		List<Long> availabilityIds = new ArrayList<Long>();
		
		EventSchedulerEvent sae = new EventSchedulerEvent(schedulerId, availabilityIds);
		
		assertNotNull(sae);
		assertEquals(availabilityIds, sae.getAvailabilityIds());
	}

}
