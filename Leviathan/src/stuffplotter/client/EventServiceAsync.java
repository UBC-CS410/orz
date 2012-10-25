package stuffplotter.client;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {
	void createCalendar(AsyncCallback<Calendar> callback);
	void createEvent(AsyncCallback<Void> callback);
	void editEvent(AsyncCallback<Void> callback);
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(AsyncCallback<Void> callback);
}
