package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountPagePresenter.AccountView;

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
		public Widget asWidget();
		//public AccountPanel getAccountPanel(); // create presenter for this
		//public StatsPanel getStatisticsPanel(); // create presenter for this
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final AccountView accountView;
	
	/**
	 * Constructor for the AccountPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the mapped services
	 * @param eventBus - the global event bus
	 * @param display - the view to present
	 * @param user - the current user
	 */
	public AccountPagePresenter(ServiceRepository appServices, HandlerManager eventBus, AccountView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.accountView = display;
	}
	
	/**
	 * Bind account view components to handlers
	 * @pre true
	 * @post true
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
		container.add(this.accountView.asWidget());
	}
}
