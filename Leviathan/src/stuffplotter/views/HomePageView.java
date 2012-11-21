package stuffplotter.views;

import java.util.Date;

import stuffplotter.presenters.HomePagePresenter.HomeView;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the home page view.
 */
public class HomePageView extends SimplePanel implements HomeView
{
	/**
	 * Constructor for the HomePagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public HomePageView()
	{
		super();
		this.initializeUI();
	}
	
	/**gwt load widget using ajax
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		// testing calendar stuff
		Calendar calendar = new Calendar();
		calendar.setWidth("500px");
		calendar.setHeight("400px");

		//Displays the month View
		calendar.setView(CalendarViews.MONTH);
		calendar.addTimeBlockClickHandler(new TimeBlockClickHandler<Date>()
		{
			@Override
			public void onTimeBlockClick(TimeBlockClickEvent<Date> event)
			{
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy");
				String[] calendarValues = dayFormat.format(event.getTarget()).toString().split(",");
				Window.alert(calendarValues[1]);
			}
		});
		
		HorizontalPanel calMapHolder = new HorizontalPanel();
		calMapHolder.add(calendar);
		this.add(calMapHolder);
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
