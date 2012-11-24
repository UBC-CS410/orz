/**
 * 
 */
package tests.client;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.EventCreationPageRetriever;

/**
 * 
 *
 */
public class ECPRTest extends TestCase
{

	@Test
	public void testECPRCtor()
	{
		String eventOwner = "John Doe";
		
		EventCreationPageRetriever ecpr = new EventCreationPageRetriever(eventOwner);
		
		assertNotNull(ecpr);
		assertEquals(eventOwner, ecpr.getOwner());
	}

}
