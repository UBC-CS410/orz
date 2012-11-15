package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter
{
	private final HandlerManager eventBus;
	private final ServiceRepository serviceRepo;
	private HasWidgets masterView;
	
	/**
	 * Constructor for the AppController.
	 * @pre serviceRepository != null && eventBus != null;
	 * @post true;
	 * @param serviceRepository - a repository containing all the asynchronous services.
	 * @param eventBus - the HandlerManager to help components signal other components.
	 */
	public AppController(ServiceRepository serviceRepository, HandlerManager eventBus)
	{
		this.serviceRepo = serviceRepository;
		this.eventBus = eventBus;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub

	}

}
