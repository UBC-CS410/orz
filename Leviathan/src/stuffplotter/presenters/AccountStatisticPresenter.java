package stuffplotter.presenters;

import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;

import com.google.gwt.event.shared.HandlerManager;
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
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.statisticsView.asWidget());
	}
}
