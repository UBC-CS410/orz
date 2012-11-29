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
				
		String eventOwner = null;
		String eventOwnerID = "1000";
		String eventName = null;
		Date eventDate = null;
		String eventLocation = null;
		Double[] eventCoordinates = null;
		String eventDuration = null;
		String eventCost = null;
		String eventDescription = null;
		Status eventStatus = Status.PROPOSED;
		Frame eventFrame = null;
		Long eventId = null;
		
		EventCreationPageRetriever eventRetriever = new EventCreationPageRetriever(eventOwnerID);
		Event eve = new Event(eventRetriever);
		
		assertNotNull(eve);
		//assertEquals(eventInvitees, eve.getInvitees());
		assertEquals(eventAttendees, eve.getAttendees());
		assertEquals(eventComments, eve.getComments());
		
		assertEquals(eventOwner, eve.getOwner());				
		assertEquals(eventOwnerID, eve.getOwnerID());
		assertEquals(eventName, eve.getName());
		assertEquals(eventDate, eve.getDate());
		assertEquals(eventLocation, eve.getLocation());
		assertEquals(eventCoordinates, eve.getCoordinates());
		assertEquals(eventDuration, eve.getDuration());
		assertEquals(eventCost, eve.getCost());
		assertEquals(eventDescription, eve.getDescription());
		
		assertEquals(eventStatus, eve.getStatus());
		assertEquals(eventFrame, eve.getTimeFrame());
		assertEquals(eventId,eve.getId());
		
		
	}
	
	@Test
	public void testSetters()
	{
		String eventOwner = "1000";
		String eventOwnerID = "1000";
		String eventName = "birthday";
		Date eventDate = new Date();
		String eventLocation = "nuketown";
		Double[] eventCoordinates = null;
		String eventDuration = "2 hours";
		String eventCost = "3 dollars";
		String eventDescription = "matt's birthday";
		Status eventStatus = Status.FINISHED;
		Frame eventFrame = Frame.HOURS;
		
		ArrayList<String> eventInvitees = new ArrayList<String>();
		ArrayList<String> eventAttendees = new ArrayList<String>();
		ArrayList<Long> eventComments = new ArrayList<Long>();
		Long eventScheduler = 3000L;
		List<String> eventRaters = new ArrayList<String>();
		
		EventCreationPageRetriever eventRetriever = new EventCreationPageRetriever(eventOwnerID);
		Event eve = new Event(eventRetriever);
		
		eve.setOwner(eventOwnerID);
		eve.setOwnerID(eventOwnerID);
		eve.setName(eventName);
		eve.setDate(eventDate);
		eve.setLocation(eventLocation);
		eve.setCoordinates(eventCoordinates);
		eve.setDuration(eventDuration);
		eve.setCost(eventCost);
		eve.setDescription(eventDescription);
		eve.setStatus(eventStatus);
		eve.setTimeFrame(eventFrame);
		
		eve.setInvitees(eventInvitees);
		eve.setSchedulerId(eventScheduler);
				
		assertEquals(eventOwner, eve.getOwner());				
		assertEquals(eventOwnerID, eve.getOwnerID());
		assertEquals(eventName, eve.getName());
		assertEquals(eventDate, eve.getDate());
		assertEquals(eventLocation, eve.getLocation());
		assertEquals(eventCoordinates, eve.getCoordinates());
		assertEquals(eventDuration, eve.getDuration());
		assertEquals(eventCost, eve.getCost());
		assertEquals(eventDescription, eve.getDescription());
		
		assertEquals(eventStatus, eve.getStatus());
		assertEquals(eventFrame, eve.getTimeFrame());
		
		assertEquals(eventInvitees, eve.getInvitees());
		assertEquals(eventAttendees, eve.getAttendees());
		assertEquals(eventComments, eve.getComments());
		assertEquals(eventScheduler,eve.getSchedulerId());
		assertEquals(eventRaters,eve.getEventRaters());
		
	}
	
	@Test
	public void testAdders()
	{
		
		
		String eventOwnerID = "1000";
		//EventCreationPageRetriever eventRetriever = new EventCreationPageRetriever(eventOwnerID);
		Event eve = new Event();
		
		Long eventComment = 100L;
		Long eventComment2 = 200L;
		
		Double rating = 4.3;
		Double rating2 = 5.0;
		
		String eventRater = "Bob";
		String eventRater2 = "Jon";
		
		/*adding people */
			eve.addAttendee("John");
			eve.addAttendee("Bob");
			eve.addAttendee("Dan");
			eve.addAttendee("Scott");
			
			eve.addInvitee("John");
			eve.addInvitee("Bob");
			eve.addInvitee("Dan");
			eve.addInvitee("Scott");
			
			eve.addComment(eventComment);
			eve.addComment(eventComment2);
			
			eve.addRating(rating);
			eve.addRating(rating2);
			
			eve.addEventRater(eventRater);
			eve.addEventRater(eventRater2);
			
		assertEquals(4,eve.getAttendees().size());
		//assertEquals(4,eve.getInvitees().size());
		assertEquals(2,eve.getComments().size());
		assertEquals(2,eve.getEventRaters().size());
		
			
		
	}
	
	@Test
	public void testRemoveInvitee()
	{
		String eventOwnerID = "1000";
		//EventCreationPageRetriever eventRetriever = new EventCreationPageRetriever(eventOwnerID);
		Event eve = new Event();
		
		eve.addInvitee("Dan");
		eve.addInvitee("Scott");
		
		eve.removeInvitee(eve.getInvitees().get(0));
		assertEquals(1,eve.getInvitees().size());
	}
	
}
