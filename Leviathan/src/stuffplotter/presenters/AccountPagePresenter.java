package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountStatisticPresenter.AccountStatisticView;
import stuffplotter.presenters.UserAccountPresenter.UserAccountView;
import stuffplotter.shared.Account;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for the Account Page presenter.
 */
public class AccountPagePresenter implements Presenter
{
	public interface AccountView
	{
		/**
		 * Retrieve the AccountView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the AccountView as a widget.
		 */
		public Widget asWidget();
		public UserAccountView getAccountPanel();
		public AccountStatisticView getStatisticsPanel();
	}

	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final AccountView accountView;
	
	/**
	 * Constructor for the AccountPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the mapped services.
	 * @param eventBus - the global event bus.
	 * @param display - the view to present.
	 * @param user - the current user.
	 */
	public AccountPagePresenter(ServiceRepository appServices,
								HandlerManager eventBus,
								AccountView display,
								Account appUser)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.accountView = display;
		this.appUser = appUser;
	}
	
	/**
	 * Bind account view components to handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Present the account view
	 * @pre true;
	 * @post this.accountView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		
		Presenter accountPresenter = new UserAccountPresenter(this.appServices,
															  this.eventBus,
															  this.accountView.getAccountPanel(),
															  this.appUser);
		accountPresenter.go((HasWidgets) this.accountView);
		
		Presenter statsPresenter = new AccountStatisticPresenter(this.appServices,
															this.eventBus,
															this.accountView.getStatisticsPanel(),
															this.appUser);
		statsPresenter.go((HasWidgets) this.accountView);
		
		container.add(this.accountView.asWidget());
	}
}
