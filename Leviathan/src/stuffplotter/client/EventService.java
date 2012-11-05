package stuffplotter.client;

import java.util.Date;

import stuffplotter.shared.Event;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService {
	Event createEvent(String ownerId, String eventName, String eventLocation, Date eventTime, double eventCost);
	void editEvent(Event modifiedEvent);
	void deleteEvent();
	void rateEvent();
}
