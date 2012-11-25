/**
 * 
 */
package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.EventCreationPageRetriever;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Frame;
import stuffplotter.shared.Event.Status;

/**
 * 
 *
 */
public class EventTest extends TestCase
{

	@Test
	public void testEventCtorNoArgs()
	{
		ArrayList<String> eventInvitees = new ArrayList<String>();
		ArrayList<String> eventAttendees = new ArrayList<String>();
		ArrayList<Long> eventComments = new ArrayList<Long>();
		//ArrayList<Double> eventRatings = new ArrayList<Double>();
		//Double rating;
		
		Event eve = new Event();
		
		assertNotNull(eve);
		assertEquals(eventInvitees, eve.getInvitees());
		assertEquals(eventAttendees, eve.getAttendees());
		assertEquals(eventComments, eve.getComments());
		
	}

	
	@Test
	public void testEvent() {
		ArrayList<String> eventInvitees = new ArrayList<String>();
		ArrayList<String> eventAttendees = new ArrayList<String>();
		ArrayList<Long> eventComments = new ArrayList<Long>();
				
		String eventOwner = "John Doe";
		String eventOwnerID = "1000";
		String eventName = "Matt's Birthday";
		Date eventDate = null;
		String eventLocation = "Chucky Cheese";
		Double[] eventCoordinates = null;
		String eventDuration = "2 hours";
		String eventCost = "100";
		String eventDescription = "No clowns please";
		Status eventStatus = null;
		Frame eventFrame = null;		
		
		EventCreationPageRetriever eventRetriever = new EventCreationPageRetriever(eventOwnerID);
		Event eve = new Event(eventRetriever);
		
		assertNotNull(eve);
		assertEquals(eventInvitees, eve.getInvitees());
		assertEquals(eventAttendees, eve.getAttendees());
		assertEquals(eventComments, eve.getComments());
		
		//assertEquals(eventOwner, eventRetriever.getOwner());				//commented code include failed accesses to private fields
		assertEquals(eventOwnerID, eventRetriever.getOwnerID());
		//assertEquals(eventName, eventRetriever.getName());
		assertEquals(eventDate, null);
		//assertEquals(eventLocation, eventRetriever.getLocation());
		assertEquals(eventCoordinates, eventRetriever.getCoordinates());
		//assertEquals(eventDuration, eventRetriever.getDuration());
		//assertEquals(eventCost, eventRetriever.getCost());
		//assertEquals(eventDescription, eventRetriever.getDescription());
		
		//assertEquals(eventStatus, Status.PROPOSED);
		assertEquals(eventFrame, eventRetriever.getFrame());
		
		
	}
}
