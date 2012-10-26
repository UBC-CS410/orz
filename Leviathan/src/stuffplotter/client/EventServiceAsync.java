package stuffplotter.client;

import java.util.Date;

import stuffplotter.shared.Event;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {
	void createCalendar(AsyncCallback<Calendar> callback);
	void createEvent(String ownerId, String eventName, Date eventTime, double eventCost, AsyncCallback<Event> callback);
	void editEvent(AsyncCallback<Void> callback);
	void deleteEvent(AsyncCallback<Void> callback);
	void rateEvent(AsyncCallback<Void> callback);
}
