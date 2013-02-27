/**
 * 
 */
package tests.client;

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
		String eventOwnerID = "John Doe";
		
		EventCreationPageRetriever ecpr = new EventCreationPageRetriever(eventOwnerID);
		
		assertNotNull(ecpr);
		assertEquals(eventOwnerID, ecpr.getOwnerID());
	}
	
	/* ignore this method, was used to test a theory.
	@Test
	public void testGetOwner() {
		String eventOwnerID = "John Doe";
		String eventOwner = null;
		EventCreationPageRetriever ecpr = new EventCreationPageRetriever(eventOwnerID);
		
		assertEquals(eventOwner, ecpr.getOwner());
	} */
}
