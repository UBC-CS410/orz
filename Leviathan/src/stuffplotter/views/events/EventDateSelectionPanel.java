package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
				timeSheet.addDay(event.getValue());
			}
		});
	}

	/**
	 * Retrieve the TimeSheetPanel for the EventDateSelectionPanel.
	 * @pre true;
	 * @post true;
	 */
	public TimeSheetPanel getTimeSheetPanel()
	{
		return this.timeSheet;
	}
	
	/**
	 * Method to accept an EventCreationPageVisitor and have it perform certain tasks.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	@Override
	public void accept(EventCreationPageVisitor eventVisitor)
	{
		eventVisitor.visit(this);
	}
}

