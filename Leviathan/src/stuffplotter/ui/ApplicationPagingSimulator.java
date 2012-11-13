package stuffplotter.ui;

import stuffplotter.presenters.AccountPagePresenter;
import stuffplotter.presenters.AchievementsPagePresenter;
import stuffplotter.presenters.EventsPagePresenter;
import stuffplotter.presenters.FriendsPagePresenter;
import stuffplotter.presenters.HomePagePresenter;
import stuffplotter.shared.Account;

import com.google.gwt.user.client.ui.DeckPanel;

/**
 * Class to simulate paged views for the applications. 
 */
public class ApplicationPagingSimulator extends DeckPanel
{
	private HomePagePresenter homePresenter;
	private AccountPagePresenter accountPresenter;
	private EventsPagePresenter eventsPresenter;
	private FriendsPagePresenter friendsPresenter;
	private AchievementsPagePresenter achievementsPresenter;
	
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
	
	private Account loggedInUser;
	
	/**
	 * Constructor for ApplicationPagingSimluator.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public ApplicationPagingSimulator(Account userAccount)
	{
		super();
		loggedInUser = userAccount;
		this.initializePages();
		this.showWidget(0);
	}
	
	/**
	 * Display the desired view.
	 * @pre viewToDisplay != null;
	 * @post this.getVisibleWidget() == viewToDisplay.getIndex(); 
	 * @param viewToDisplay - the View enum of the display to show.
	 */
	public void showView(View viewToDisplay)
	{
		this.showWidget(viewToDisplay.getIndex());
	}
	
	/**
	 * Helper method to initialize the simulated pages for the application.
	 * @pre true;
	 * @post true;
	 */
	private void initializePages()
	{
		// initialize Home page
		HomePagePanel homePage = new HomePagePanel();
		
		// initialize User Account page
		AccountPagePanel accountPage = new AccountPagePanel(this.loggedInUser);
		
		// initialize User Events page
		EventsPagePanel eventsPage = new EventsPagePanel(this.loggedInUser);
		
		// initialize User Friends page
		FriendsPagePanel friendsPage = new FriendsPagePanel();
		this.friendsPresenter = new FriendsPagePresenter(friendsPage);
		
		// initialize User Achievements page
		AchievementsPagePanel achievementsPanel = new AchievementsPagePanel();
		
		this.add(homePage);
		this.add(accountPage);
		this.add(eventsPage);
		this.add(friendsPage);
		this.add(achievementsPanel);
	}
}
