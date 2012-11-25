package stuffplotter.presenters;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the user account information display.
 */
public class UserAccountPresenter implements Presenter
{
	public interface UserAccountView
	{
		/**
		 * Set the user account data to display in the UserAccountView.
		 * @pre model != null;
		 * @post true;
		 */
		public void setUserData(AccountModel model);

		/**
		 * Retrieve the UserAccountView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the UserAccountView as a widget.
		 */
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final UserAccountView userAccountView;
	private final Account appUser;
	
	/**
	 * Constructor for the UserAccountPresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && appUser != null;
	 * @post true;
	 * @param appServices - the mapped services.
	 * @param eventBus - the global event bus.
	 * @param display - the view to present.
	 * @param user - the current user.
	 */
	public UserAccountPresenter(ServiceRepository appServices,
								HandlerManager eventBus,
								UserAccountView display,
								Account appUser)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.userAccountView = display;
		this.appUser = appUser;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.userAccountView.asWidget());
	}
}
