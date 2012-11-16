package stuffplotter.presenters;

import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.presenters.AchievementsPagePresenter.AchievementsView;
import stuffplotter.presenters.EventsPagePresenter.EventsView;
import stuffplotter.presenters.FriendsPagePresenter.FriendsView;
import stuffplotter.presenters.HomePagePresenter.HomeView;

import com.google.gwt.user.client.ui.HasWidgets;

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
		
		public HomeView getHomeView();
		public AccountView getAccountView();
		public EventsView getEventsView();
		public FriendsView getFriendsView();
		public AchievementsView getAchievementsView();
		public void showView(View view); 
	}
	
	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub

	}

}
