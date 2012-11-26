/**
 * 
 */
package tests.signals;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.signals.EventCreatedEvent;

/**
 * JUnit test class for EventCreatedEvent.java
 *
 */
public class EventCreatedEventTest extends TestCase
{
	/**
	 * Verify that the instance fields in the class are set correctly
	 */
	@Test
	public void testECECtor()
	{
		long eventID = 1000;
		EventCreatedEvent ece = new EventCreatedEvent(eventID);
		
		assertNotNull(ece);
		assertEquals(eventID, (long)ece.getEventID());
	}

}
