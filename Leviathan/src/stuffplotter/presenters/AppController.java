package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.views.MasterView;
import stuffplotter.views.global.PageRefresherSignaller;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter
{
	private static final int REFRESH_RATE = 60000; // in ms
	private final HandlerManager eventBus;
	private final ServiceRepository rpcServices;	
	private HasWidgets container;
	private Account account;
	
	/**
	 * Constructor for the AppController.
	 * @pre serviceRepository != null && eventBus != null;
	 * @post true;
	 * @param serviceRepository - a repository containing all the asynchronous services.
	 * @param eventBus - the HandlerManager to help components signal other components.
	 * @param account - the user account that is accessing the application.
	 */
	public AppController(ServiceRepository serviceRepository, HandlerManager eventBus, Account account)
	{
		this.rpcServices = serviceRepository;
		this.eventBus = eventBus;
		this.account = account;
	}
	
	/**
	 * Helper method to add the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{

	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		this.container = container;
		
		Presenter presenter = new MasterPresenter(this.rpcServices, this.eventBus, new MasterView(), this.account);
		presenter.go(this.container);
		
		PageRefresherSignaller refreshPageUnit = new PageRefresherSignaller(this.eventBus, REFRESH_RATE);
		refreshPageUnit.startPeriodicRefreshing();
	}
}
