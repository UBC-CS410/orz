package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.presenters.AchievementsPagePresenter.AchievementsView;
import stuffplotter.presenters.EventsPagePresenter.EventsView;
import stuffplotter.presenters.FriendsPagePresenter.FriendsView;
import stuffplotter.presenters.HomePagePresenter.HomeView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ApplicationPagingPresenter implements Presenter
{
	public interface MainView
	{
		/**
		 * enum to help determine which page to display.
		 */
		public enum View
		{
			HOME(0), ACCOUNT(1), EVENTS(2), FRIENDS(3), ACHIEVEMENTS(4);
			
			private int index;
			
			/**
			 * Constructor to bind enum to a 0 based index.
			 * @pre index >= 0;
			 * @post this.index == index;
			 * @param index - the index of the page.
			 */
			private View(int index)
			{
				this.index = index;
			}
			
			/**
			 * Method to get the index for the given enum.
			 * @pre true;
			 * @post true;
			 * @return	the index of the View.
			 */
			public int getIndex()
			{
				return this.index;
			}
		};
		
		/**
		 * Retrieve the panel that displays the home page.
		 * @pre true;
		 * @post true;
		 * @return the panel that displays the home page.
		 */
		public HomeView getHomeView();
		
		/**
		 * Retrieve the panel that displays the accounts page.
		 * @pre true;
		 * @post true;
		 * @return the panel that displays the accounts page.
		 */
		public AccountView getAccountView();
		
		/**
		 * Retrieve the panel that displays the events page. 
		 * @pre true;
		 * @post true;
		 * @return the panel that displays the events page.
		 */
		public EventsView getEventsView();
		
		/**
		 * Retrieve the panel that displays the friends page.
		 * @pre true;
		 * @post true;
		 * @return the panel that displays the friends page.
		 */
		public FriendsView getFriendsView();
		
		/**
		 * Retrieve the panel that displays the achievements page.
		 * @pre true;
		 * @post true;
		 * @return the panel that displays the achievements page.
		 */
		public AchievementsView getAchievementsView();
		
		/**
		 * Display the view that is indicated by the param view. 
		 * @pre view != null;
		 * @post true;
		 * @param view - the View to switch the main view to.
		 */
		public void showView(View view);
		
		/**
		 * Retrieve the MainView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the MainView as a widget.
		 */
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final MainView mainViewDisplay;
	
	/**
	 * Constructor for the ApplicationPagingPresenter.
	 * @pre true;
	 * @post this.isVisible() == true;
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 * @param display - the MainView to associate with the ApplicationPagingPresenter.
	 */
	public ApplicationPagingPresenter(ServiceRepository appServices, HandlerManager eventBus, MainView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.mainViewDisplay = display;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.mainViewDisplay.asWidget());
	}
}
