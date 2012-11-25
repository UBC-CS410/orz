/**
 * 
 */
package tests.client;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.EventCreationPageValidator;

/**
 * JUnit test class for EventCreationPageValidtor.java
 *
 */
public class ECPVTest extends TestCase
{
	
	/**
	 * Verify that the instance fields in the class are set correctly
	 */
	@Test
	public void testECPVCtor()
	{
		EventCreationPageValidator ecpv = new EventCreationPageValidator();
		
		assertNotNull(ecpv);
		assertEquals(true, ecpv.isPageValid());
	}

}
