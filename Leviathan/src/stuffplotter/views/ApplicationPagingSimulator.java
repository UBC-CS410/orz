package stuffplotter.views;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.ui.DeckPanel;

/**
 * Class to simulate paged views for the applications. 
 */
public class ApplicationPagingSimulator extends DeckPanel
{	
	private HomePageView homePage;
	private AccountPageView accountPage;
	private EventsPageView eventsPage;
	private FriendsPageView friendsPage;
	private AchievementsPageView achievementsPanel;
	
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
	 * Constructor for ApplicationPagingSimluator.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public ApplicationPagingSimulator()
	{
		super();
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
		homePage = new HomePageView();
		
		// initialize User Account page
		accountPage = new AccountPageView();
		
		// initialize User Events page
		eventsPage = new EventsPageView();
		
		// initialize User Friends page
		friendsPage = new FriendsPageView();
		
		// initialize User Achievements page
		achievementsPanel = new AchievementsPageView();
		
		this.add(homePage);
		this.add(accountPage);
		this.add(eventsPage);
		this.add(friendsPage);
		this.add(achievementsPanel);
	}

	/**
	 * Retrieve the view for the Home page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Home page.
	 */
	public HomePageView getHomeView()
	{
		return this.homePage;
	}

	/**
	 * Retrieve the view for the Account page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Account page.
	 */
	public AccountPageView getAccountView()
	{
		return this.accountPage;
	}

	/**
	 * Retrieve the view for the Events page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Events page.
	 */
	public EventsPageView getEventsView()
	{
		return this.eventsPage;
	}

	/**
	 * Retrieve the view for the Friends page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Friends page.
	 */
	public FriendsPageView getFriendsView()
	{
		return this.friendsPage;
	}

	/**
	 * Retrieve the view for the Achievements page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Achievements page.
	 */
	public AchievementsPageView getAchievementsView()
	{
		return this.achievementsPanel;
	}
}
