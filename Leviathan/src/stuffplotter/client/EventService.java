package stuffplotter.client;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService {
	Calendar createCalendar();
	void createEvent();
	void editEvent();
	void deleteEvent();
	void rateEvent();
}
