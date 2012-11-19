package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Availability;
import stuffplotter.shared.Event;
import stuffplotter.shared.MonthContainer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("events")
public interface EventService extends RemoteService
{
	Event createEvent(Event newEvent, List<MonthContainer> timeSlots);
	Event retrieveEvent(Long eventId);
	List<Event> retrieveEvents(List<Long> eventIds);
	void updateEvent(Event modifiedEvent);
	
	void updateScheduler(List<Long> availabilityIds);
	List<Availability> retrieveAvailabilities(Long pSchedulerId);
	
	void deleteEvent();
	void rateEvent();
}
