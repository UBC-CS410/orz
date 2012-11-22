package stuffplotter.views;

import stuffplotter.presenters.HomePagePresenter.HomeView;
import stuffplotter.views.home.HomePageCalendar;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the home page view.
 */
public class HomePageView extends SimplePanel implements HomeView
{
	private HomePageCalendar calendar;
	
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
		calendar = new HomePageCalendar();
		HorizontalPanel calMapHolder = new HorizontalPanel();
		calMapHolder.add(calendar);
		this.add(calMapHolder);
	}
	
	/**
	 * Retrieve the Calendar from the HomePageView.
	 * @pre true;
	 * @post true;
	 * @return the Calendar on the HomePageView.
	 */
	public Calendar getCalendar()
	{
		return this.calendar.getCalendar();
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
