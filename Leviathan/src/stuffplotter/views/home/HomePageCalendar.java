package stuffplotter.views.home;

import java.util.Date;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;

/**
 * Class to display the calendar on the home page.
 */
public class HomePageCalendar extends VerticalPanel
{
	private static final String CAL_WIDTH = "500px";
	private static final String CAL_HEIGHT = "400px";
	private Calendar calendar;
	private Button previousMonth;
	private Button nextMonth;
	private Label currentMonth;
	
	/**
	 * Constructor for the home page calendar.
	 * @pre true;
	 * @post true;
	 */
	public HomePageCalendar()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initial the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{		
		calendar = new Calendar();
		calendar.setWidth(CAL_WIDTH);
		calendar.setHeight(CAL_HEIGHT);
		calendar.setView(CalendarViews.MONTH);
		this.addTopControl();
		this.add(calendar);
	}
	
	/**
	 * Helper method to add the top control panel.
	 * @pre true;
	 * @post true;
	 */
	private void addTopControl()
	{
		HorizontalPanel controlPanel = new HorizontalPanel();
		this.previousMonth = new Button("<<");
		this.nextMonth = new Button(">>");
		Date currentDate = this.calendar.getDate();
		DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,yyyy");
		String[] calendarValues = dayFormat.format(currentDate).toString().split(",");
		this.currentMonth = new Label(calendarValues[0] + " " + calendarValues[1]);
		controlPanel.add(this.previousMonth);
		controlPanel.add(this.currentMonth);
		controlPanel.add(this.nextMonth);
		this.add(controlPanel);
		
		this.initializeBasicBtnFunctions();
	}
	
	/**
	 * Helper method to initialize the basic functions for the previous and next month buttons.
	 * @pre true;
	 * @post true;
	 */
	private void initializeBasicBtnFunctions()
	{		
		this.previousMonth.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Date currentDate = calendar.getDate();
				CalendarUtil.addMonthsToDate(currentDate, -1);
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,yyyy");
				String[] calendarValues = dayFormat.format(currentDate).toString().split(",");
				currentMonth.setText(calendarValues[0] + " " + calendarValues[1]);
				calendar.setDate(currentDate);
			}
		});
		
		this.nextMonth.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Date currentDate = calendar.getDate();
				CalendarUtil.addMonthsToDate(currentDate, 1);
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,yyyy");
				String[] calendarValues = dayFormat.format(currentDate).toString().split(",");
				currentMonth.setText(calendarValues[0] + " " + calendarValues[1]);
				calendar.setDate(currentDate);
			}
		});
	}
	
	/**
	 * Retrieve the Calendar from the panel.
	 * @pre true;
	 * @post true;
	 * @return the Calendar from the panel.
	 */
	public Calendar getCalendar()
	{
		return this.calendar;
	}
}
