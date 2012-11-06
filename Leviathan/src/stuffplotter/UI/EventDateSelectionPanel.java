package stuffplotter.UI;

import java.util.Date;

import stuffplotter.UI.MonthPanel.Month;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Event;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Class to display a DatePicker with TimeSheetPanel to select dates for an event.
 */
public class EventDateSelectionPanel extends SimplePanel implements EventSubmittable
{
	private TimeSheetPanel timeSheet;
	
	/**
	 * Constructor for EventDateSelectionPanel.
	 * @pre timeSheetPanel != null;
	 * @post this.isVisible() == true;
	 * @param timeSheetPanel - the TimeSheetPanel to add selected dates to.
	 */
	public EventDateSelectionPanel(TimeSheetPanel timeSheetPanel)
	{
		super();
		this.initializeUI(timeSheetPanel);
	}
	
	/**
	 * Helper method to add the panels to the EventDateSelectionPanel.
	 * @pre timeSheetPanel != null;
	 * @post true;
	 * @param timeSheetPanel - the TimeSheetPanel to add selected dates to.
	 */
	private void initializeUI(TimeSheetPanel timeSheetPanel)
	{
		this.timeSheet = timeSheetPanel;
		HorizontalPanel horPanel = new HorizontalPanel();
		DatePicker calendar = new DatePicker();
		this.initCalChangeHandler(calendar);
		horPanel.add(calendar);
		horPanel.add(timeSheetPanel);
		this.add(horPanel);
	}
	
	/**
	 * Helper method to initialize the change handler for the calendar.
	 * @pre calendar != null;
	 * @post true;
	 * @param calendar - the calendar to add the change handler to.
	 * @param timeSheet - the time sheet to add selected dates to.
	 */
	private void initCalChangeHandler(DatePicker calendar)
	{
		calendar.addValueChangeHandler(new ValueChangeHandler<Date>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<Date> event)
			{
				Date dayClicked = event.getValue();
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy");
				String[] calendarValues = dayFormat.format(dayClicked).toString().split(",");
				Month month = Month.valueOf(calendarValues[0].toUpperCase());
				int[] day = { Integer.valueOf(calendarValues[1]) };
				String year = calendarValues[2];
				timeSheet.addDay(month, year, day);
			}
		});
	}

	/**
	 * Method to retrieve the submission information from the TimeSheetPanel.
	 * @pre event != null;
	 * @post true;
	 * @param event - the Event to have its fields populated with before sending to
	 * 				  the backend.
	 */
	@Override
	public void retrieveSubmission(Event event)
	{
		//event.setTimeSheet(this.timeSheet.retrieveSubmission());
	}
}

