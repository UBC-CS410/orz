package stuffplotter.views;


import stuffplotter.presenters.ApplicationPagingPresenter.MainView;

import com.google.gwt.user.client.ui.DeckPanel;

/**
 * Class to simulate paged views for the application; pages between the Home, Account,
 * Events, Friends, and Achievements views.
 */
public class ApplicationPagingView extends DeckPanel implements MainView
{	
	private HomePageView homePage;
	private AccountPageView accountPage;
	private EventsPageView eventsPage;
	private FriendsPageView friendsPage;
	private AchievementsPageView achievementsPanel;
	
	/**
	 * Constructor for ApplicationPagingSimluator.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public ApplicationPagingView()
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
		//this.add(achievementsPanel);
	}

	/**
	 * Retrieve the view for the Home page.
	 * @pre true;
	 * @post true;
	 * @return the view for the Home page.
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public AchievementsPageView getAchievementsView()
	{
		return this.achievementsPanel;
	}
}
