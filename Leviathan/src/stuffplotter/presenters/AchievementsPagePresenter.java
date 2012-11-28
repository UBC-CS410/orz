package stuffplotter.presenters;

import java.util.List;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Achievement;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.achievements.AchievementsDisplayPanel.AchievementPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		 * Retrieves the achievement buttons;
		 * @pre true;
		 * @post true;
		 * @return achievementButtons
		 */
		public List<Widget> getAchievementButtons();
		/**
		 * Retrieve the AchievementsView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the AchievementsView as a widget.
		 */
		public Widget asWidget();
	
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
		this.eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
		{

			@Override
			public void onRefreshPage(RefreshPageEvent event)
			{
				dataBindAccount();
				
			}
			
		});
		setAchievementButtons();
		
	}

	private void setAchievementButtons()
	{
		
		List<Widget> panels = this.achievementsView.getAchievementButtons();
		for(Widget widget : panels)
		{
			final AchievementPanel achievemeantPanel = (AchievementPanel) widget;
			achievemeantPanel.getAchIcon().addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					appUser.setBadge(achievemeantPanel.getAch().getImg());
					appServices.getAccountService().saveAccount(appUser, new AsyncCallback<Void>()
					{

						@Override
						public void onFailure(Throwable caught)
						{
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result)
						{
							eventBus.fireEvent(new RefreshPageEvent());
						}
					});
					
				}
			});
		}
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
