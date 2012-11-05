package stuffplotter.client;

import java.util.Date;
import java.util.List;

import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService {
	Event createEvent(String ownerId, String eventName, String eventLocation, Date eventTime, double eventCost);
	void createScheduler(Long eventId, List<MonthContainer> eventTimes);
	
	Event retrieveEvent(Long eventId);
	void updateEvent(Event modifiedEvent);
	
	
	void deleteEvent();
	void rateEvent();
}
