package stuffplotter.client;

import stuffplotter.shared.Event;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService {
	Calendar createCalendar();
	Event createEvent(Event event);
	void editEvent();
	void deleteEvent();
	void rateEvent();
}
