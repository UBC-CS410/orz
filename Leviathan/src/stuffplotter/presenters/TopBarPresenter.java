package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class TopBarPresenter implements Presenter
{
	public interface TopBarView
	{
		// TO DO
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final TopBarView topBarDisplay;
	
	/**
	 * Constructor for the TopBarPresenter.
	 * @pre true;
	 * @post this.isVisible() == true;
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 * @param display - the TopBarView to associate with the TopBarPresenter.
	 */
	public TopBarPresenter(ServiceRepository appServices, HandlerManager eventBus, TopBarView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.topBarDisplay = display;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		// Don't this method use since this panel doesn't have different views to swap with it.
	}
}
