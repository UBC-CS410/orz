/**
 * 
 */
package stuffplotter.server;

import java.util.Date;

import stuffplotter.client.EventService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements EventService {
	
	private DatabaseStore dbstore = new DatabaseStore();

	@Override
	public Calendar createCalendar() {
		Calendar calendar = new Calendar();
		calendar.setWidth("500px");
		calendar.setHeight("400px");
		return calendar;
	}

	@Override
	public Event createEvent(String pOwner, String pName, String pLocation, Date pDate, double pCost) {
		Event event = new Event(pOwner, pName, pLocation, pDate, pCost);
		dbstore.store(event);
		
		Account account = dbstore.fetchAccount(pOwner);
		account.addUserEvent(event.getEventId());
		dbstore.store(account);
		
		return event;
	}

	@Override
	public void editEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateEvent() {
		// TODO Auto-generated method stub
		
	}

}
