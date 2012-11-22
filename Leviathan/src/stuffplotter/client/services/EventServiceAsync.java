package stuffplotter.client.services;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync
{	
	void createEvent(Event newEvent, List<MonthContainer> timeSlots, AsyncCallback<Event> callback);
	void retrieveEvent(Long eventId, AsyncCallback<Event> callback);
	void retrieveEvents(List<Long> eventIds, AsyncCallback<List<Event>> callback);
	void updateEvent(Event modifiedEvent, AsyncCallback<Void> callback);
	void updateScheduler(List<Long> availabilityIds, AsyncCallback<Void> callback);
	void retrieveAvailabilities(Long pSchedulerId, AsyncCallback<List<Availability>> callback);
	void addComment(Long eventId, String username, Date time, String comment, AsyncCallback<Void> callback);
	void getComments(Long eventId, AsyncCallback<List<Comment>> callback);
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(AsyncCallback<Void> callback);
}
