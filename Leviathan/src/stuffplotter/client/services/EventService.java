package stuffplotter.client.services;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService
{
	Event createEvent(Event newEvent, List<Date> timeSlots);
	Event retrieveEvent(Long eventId);
	List<Event> retrieveEvents(List<Long> eventIds);
	void updateEvent(Event modifiedEvent);
	
	void updateScheduler(List<Long> availabilityIds);
	List<Availability> retrieveAvailabilities(Long pSchedulerId);
	
	void addComment(Long eventId, String username, Date time, String comment);
	List<Comment> getComments(Long eventId);
	
	void deleteEvent();
	void rateEvent();
}
