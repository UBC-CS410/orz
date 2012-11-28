package stuffplotter.client.services;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.Scheduler;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService
{
	Event createEvent(Event newEvent, List<Date> timeSlots);
	Event retrieveEvent(Account account, Long eventId);
	List<Event> retrieveEvents(Account account, List<Long> eventIds);
	void updateEvent(Event modifiedEvent);
	
	Scheduler retrieveScheduler(Long schedulerId);
	void updateScheduler(String userId, Long schedulerId, List<Long> availabilityIds);
	List<Availability> retrieveAvailabilities(Long pSchedulerId);
	
	void addComment(Long eventId, String username, Date time, String comment);
	List<Comment> getComments(Long eventId);
	
	void deleteEvent();
	void rateEvent(Long eventId, String userId);
	
	void inviteGuest(Long eventId, String userId);
	void confirmGuest(Long eventId, String userId);
	void removeGuest(Long eventId, String userId);
}
