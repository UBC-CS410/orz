package stuffplotter.presenters;

import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AccountStatisticPresenter implements Presenter
{
	public interface AccountStatisticView
	{
		/**
		 * Set the statistic data to display.
		 * @pre true;
		 * @post true;
		 * @param model - the model to display on the StatisticPanel.
		 */
		public void setStatisticData(AccountStatisticModel model);
		
		/**
		 * Retrieve the AccountStatisticView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the AccountStatisticView as a widget.
		 */
		public Widget asWidget();

		/**
		 * Display the failed to retrieve information message.
		 * @pre true;
		 * @post true;
		 */
		public void displayFailMessage();
		
		/**
		 * Remove the failed to retrieve information message.
		 * @pre true;
		 * @post true;
		 */
		public void removeFailMessage();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final AccountStatisticView statisticsView;
	private final Account appUser;
	
	/**
	 * Constructor for the AccountStatisticPresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && appUser != null;
	 * @post true;
	 * @param appServices - the mapped services.
	 * @param eventBus - the global event bus.
	 * @param display - the view to present,
	 * @param user - the current user.
	 */
	public AccountStatisticPresenter(ServiceRepository appServices,
									 HandlerManager eventBus,
									 AccountStatisticView display,
									 Account appUser)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.statisticsView = display;
		this.appUser = appUser;
		this.dataBindAccount();
	}
	
	/**
	 * Helper method to data bind the account to the view.
	 * @pre true;
	 * @post true;
	 */
	private void dataBindAccount()
	{
		this.appServices.getStatsService().getStats(this.appUser.getUserEmail(),new AsyncCallback<AccountStatistic>()
		{
			@Override
			public void onSuccess(AccountStatistic result)
			{
				statisticsView.setStatisticData(result);
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				statisticsView.displayFailMessage();
			}
		});
	}
	
	/**
	 * Bind view components to handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.statisticsView.asWidget());
	}
}
