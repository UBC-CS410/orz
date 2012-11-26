package stuffplotter.presenters;

import java.util.Date;

import stuffplotter.client.services.ServiceRepository;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for the Home Page presenter.
 */
public class HomePagePresenter implements Presenter
{
	public interface HomeView
	{
		/**
		 * Retrieve the HomeView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the HomeView as a widget.
		 */
		public Widget asWidget();
		public Calendar getCalendar(); // create presenter for this
		//public NewsFeedPanel getNewsFeed(); // create presenter for this
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final HomeView homeView;
	
	/**
	 * Constructor for the HomePagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the mapped services
	 * @param eventBus - the global event bus
	 * @param display - the view to present
	 * @param user - the current user
	 */
	public HomePagePresenter(ServiceRepository appServices, HandlerManager eventBus, HomeView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.homeView = display;
	}

	/**
	 * Bind view components to handlers
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.homeView.getCalendar().addTimeBlockClickHandler(new TimeBlockClickHandler<Date>()
		{
			@Override
			public void onTimeBlockClick(TimeBlockClickEvent<Date> event)
			{
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy");
				String[] calendarValues = dayFormat.format(event.getTarget()).toString().split(",");
				Window.alert(calendarValues[1]);
			}
		});
	}
	
	/**
	 * Present the view
	 * @pre true;
	 * @post this.homeView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.homeView.asWidget());
	}

}
