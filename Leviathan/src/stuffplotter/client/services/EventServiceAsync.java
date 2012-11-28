package stuffplotter.client.services;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.Scheduler;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync
{	
	void createEvent(Event newEvent, List<Date> timeSlots, AsyncCallback<Event> callback);
	void retrieveEvent(Account account, Long eventId, AsyncCallback<Event> callback);
	void retrieveEvents(Account account, List<Long> eventIds, AsyncCallback<List<Event>> callback);
	void updateEvent(Event modifiedEvent, AsyncCallback<Void> callback);
	
	void retrieveScheduler(Long schedulerId, AsyncCallback<Scheduler> callback);
	void updateScheduler(String userId, Long schedulerId, List<Long> availabilityIds, AsyncCallback<Void> callback);
	
	void retrieveAvailabilities(Long pSchedulerId, AsyncCallback<List<Availability>> callback);
	void addComment(Long eventId, String username, Date time, String comment, AsyncCallback<Void> callback);
	void getComments(Long eventId, AsyncCallback<List<Comment>> callback);
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(Long eventId, String userId, AsyncCallback<Void> callback);
	
	void inviteGuest(Long eventId, String userId, AsyncCallback<Void> callback);
	void confirmGuest(Long eventId, String userId, AsyncCallback<Void> callback);
	void removeGuest(Long eventId, String userId, AsyncCallback<Void> callback);
}
