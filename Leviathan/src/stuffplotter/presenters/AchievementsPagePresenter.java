package stuffplotter.presenters;

import java.util.List;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Achievement;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for the Achievements Page presenter.
 */
public class AchievementsPagePresenter implements Presenter
{
	public interface AchievementsView
	{
		/**
		 * Sets the AchievementData
		 * @pre true;
		 * @post true;
		 * @param achievements
		 */
		public void setAchievementData(List<Achievement> achievements);
		/**
		 * Retrieve the AchievementsView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the AchievementsView as a widget.
		 */
		public Widget asWidget();
		//public AchievementsPanel getAchievementsPanel(); // create presenter for this 
	}
	private Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final AchievementsView achievementsView;
	
	/**
	 * Constructor for the AchievementsPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the mapped services
	 * @param eventBus - the global event bus
	 * @param display - the view to present
	 * @param user - the current user
	 */
	public AchievementsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, AchievementsView display, Account user)
	{
		this.appUser = user;
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.achievementsView = display;
		this.dataBindAccount();
	}

	private void dataBindAccount()
	{
		appServices.getStatsService().getStats(appUser.getUserEmail(), new AsyncCallback<AccountStatistic>()
		{
			
			@Override
			public void onSuccess(AccountStatistic result)
			{
				achievementsView.setAchievementData(result.getUserAchievements());
				
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				
			}
		});
		
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
	 * @post this.achievementsView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.achievementsView.asWidget());
	}
}
