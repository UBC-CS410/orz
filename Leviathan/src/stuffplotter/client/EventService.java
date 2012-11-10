package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService
{
	Event createEvent(Event newEvent, List<MonthContainer> timeSlots);
	
	Event retrieveEvent(Long eventId);
	void updateEvent(Event modifiedEvent);
	
	void deleteEvent();
	void rateEvent();
}
