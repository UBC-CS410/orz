package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.FriendsPagePresenter.FriendsView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for the Home Page presenter.
 */
public class HomePagePresenter implements Presenter
{
	public interface HomeView
	{
		public Widget asWidget();
		//public CalendarWidget getCalendar(); // create presenter for this
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
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		// TODO Auto-generated method stub
		
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
