package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.client.GoogleCalendar;

import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext.ListRequest;
import com.google.api.gwt.services.calendar.shared.model.CalendarList;
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class EventDateSelectionPresenter implements Presenter
{
	public interface EventDateSelectionView
	{
		/**
		 * Retrieve the date picker in the display.
		 * @pre true;
		 * @post true;
		 * @return the date picker in the display.
		 */
		public HasValueChangeHandlers<Date> getCalendar();
		
		/**
		 * Populate the times on the time sheet panel.
		 * @pre shownDate != null && conflictDates != null;
		 * @post true;
		 * @param shownDate - the day to display.
		 * @param conflictDates - the time slots that already have events going on.
		 */
		public void populateTimeSheet(Date shownDate, List<Date> conflictDates);
		
		/**
		 * Retrieve the EventDateSelectionView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventDateSelectionView as a widget.
		 */
		public Widget asWidget();
	}
	
	private final EventDateSelectionView display;
	
	/**
	 * Constructor for the EventDateSelectionPresenter.
	 * @pre display != null;
	 * @post true;
	 * @param display - the display for selecting dates for an event.
	 */
	public EventDateSelectionPresenter(EventDateSelectionView display)
	{
		this.display = display;
	}
	
	/**
	 * Helper method to attache the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.display.getCalendar().addValueChangeHandler(new ValueChangeHandler<Date>()
		{
			@Override
			public void onValueChange(final ValueChangeEvent<Date> event)
			{
				// add google calendar here to get list of conflicts
				GoogleCalendar  calendar = new GoogleCalendar();
				final Calendar googleCalendar = calendar.getCalendar();
				
				googleCalendar.calendarList().list().setMinAccessRole(MinAccessRole.OWNER).fire(new Receiver<CalendarList>()
				{
					@Override
					public void onSuccess(CalendarList response) 
					{
						String calendarID = response.getItems().get(0).getId();
						ListRequest calRequest = googleCalendar.events().list(calendarID);
						calRequest.fire(new Receiver<Events>()
						{
							@Override
							public void onSuccess(Events response)
							{
								String result = "Events Found: ";
								List<com.google.api.gwt.services.calendar.shared.model.Event> events = response.getItems();
								if(events != null)
								{
									for(com.google.api.gwt.services.calendar.shared.model.Event event : events)
									{
										result += " " + event.getCreated();
									}
								}
								
								// add google calendar here to get list of conflicts
								display.populateTimeSheet(event.getValue(), new ArrayList<Date>());
							
								Window.alert(result);
							}
						});
					}
				});
			}
		});
	}
	
	@Override
	public void go(HasWidgets container)
	{
		this.bind();
		container.add(this.display.asWidget());
	}
}
