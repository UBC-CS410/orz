package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.client.EventCreationPageVisitor;
import stuffplotter.presenters.EventDateSelectionPresenter.EventDateSelectionView;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Class to display a DatePicker with TimeSheetPanel to select dates for an event.
 */
public class EventDateSelectionPanel extends SimplePanel implements EventSubmittable, EventDateSelectionView
{
	private TimeSheetPanel timeSheet;
	private Label errorMessage;
	private DatePicker calendar;
	
	/**
	 * Constructor for EventDateSelectionPanel.
	 * @pre timeSheetPanel != null;
	 * @post this.isVisible() == true;
	 */
	public EventDateSelectionPanel()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to add the panels to the EventDateSelectionPanel.
	 * @pre timeSheetPanel != null;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.timeSheet = new TimeSheetPanel();
		HorizontalPanel horPanel = new HorizontalPanel();
		VerticalPanel vertPanel = new VerticalPanel();
		calendar = new DatePicker();
		this.errorMessage = new Label();
		vertPanel.add(new Label("Click on the calendar to select dates for the event."));
		vertPanel.add(calendar);
		vertPanel.add(errorMessage);
		horPanel.add(vertPanel);
		horPanel.add(this.timeSheet);
		this.add(horPanel);
	}
	
	/**
	 * Remove the error message on the EventDateSelectionPanel.
	 * @pre true;
	 * @post true;
	 */
	public void clearErrorMessage()
	{
		this.errorMessage.setText("");
	}
	
	/**
	 * Display the error message on the EventDateSelectionPanel.
	 * @pre true;
	 * @post true;
	 */
	public void displayErrorMessage()
	{
		this.errorMessage.setText("You must select at least one time slot.");
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

	/**
	 * Retrieve the calendar from this view.
	 * @pre true;
	 * @post true;
	 */
	public DatePicker getCalendar()
	{
		return this.calendar;
	}

	/**
	 * Populate the time sheet in this panel with the given date and mark any conflicting
	 * time slots.
	 * @pre shownDate != null && conflictDates != null;
	 * @post true;
	 * @param shownDate - the day to display on the UI.
	 * @param conflictDates - the list of Date that already have activities going on.
	 */
	public void populateTimeSheet(Date shownDate, List<Date> conflictDates)
	{
		this.timeSheet.addDay(shownDate, conflictDates);
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
	
}

