/**
 * 
 */
package stuffplotter.server;

import stuffplotter.client.EventService;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements EventService {

	@Override
	public Calendar createCalendar() {
		Calendar calendar = new Calendar();
		calendar.setWidth("500px");
		calendar.setHeight("400px");
		return calendar;
	}

	@Override
	public void createEvent() {
		// TODO Auto-generated method stub
		
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
