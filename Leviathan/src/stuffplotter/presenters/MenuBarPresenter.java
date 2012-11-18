package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.TopBarPresenter.TopBarView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class MenuBarPresenter implements Presenter
{
	public interface MenuBarView
	{
		/**
		 * Retrieve the HasClickHandler that will be used to change view to home page.
		 * @pre true;
		 * @post true;
		 * @return the HasClickHanlder that will be used to change view to home page.
		 */
		public HasClickHandlers getHomeBtn();
		
		/**
		 * Retrieve the HasClickHandler that will be used to change view to account page.
		 * @pre true;
		 * @post true;
		 * @return the HasClickHandler that will be used to change view to account page.
		 */
		public HasClickHandlers getAccountBtn();
		
		/**
		 * Retrieve the HasClickHandler that will be used to change view to events page.
		 * @pre true;
		 * @post true;
		 * @return the HasClickHandler that will be used to change view to events page.
		 */
		public HasClickHandlers getEventsBtn();
		
		/**
		 * Retrieve the HasClickHandler that will be used to change view to friends page.
		 * @pre true;
		 * @post true;
		 * @return the HasClickHandler that will be used to change view to friends page.
		 */
		public HasClickHandlers getFriendsBtn();
		
		/**
		 * Retrieve the HasClickHandler that will be used to change view to achievements page.
		 * @pre true;
		 * @post true;
		 * @return the HasClickHandler that will be used to change view to achievements page.
		 */
		public HasClickHandlers getAchievementsBtn();
	}
	
	private final ServiceRepository appServies;
	private final HandlerManager eventBus;
	private final MenuBarView menuBarDisplay;
	
	/**
	 * Constructor for the MenuBarView.
	 * @pre true;
	 * @post this.isVisible() == true
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 * @param display - the TopBarView to associate with the TopBarPresenter.
	 */
	public MenuBarPresenter(ServiceRepository appServices, HandlerManager eventBus, MenuBarView display)
	{
		this.appServies = appServices;
		this.eventBus = eventBus;
		this.menuBarDisplay = display;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		// Don't this method use since this panel doesn't have different views to swap with it.
	}
}
