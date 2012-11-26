/**
 * 
 */
package tests.signals;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import stuffplotter.signals.SubmittedAvailabilitiesEvent;

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
		List<Long> availabilityIds = new ArrayList<Long>();
		
		SubmittedAvailabilitiesEvent sae = new SubmittedAvailabilitiesEvent(availabilityIds);
		
		assertNotNull(sae);
		assertEquals(availabilityIds, sae.getAvailabilityIds());
	}

}
