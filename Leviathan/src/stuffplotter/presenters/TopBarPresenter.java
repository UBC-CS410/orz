package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.views.global.TopBarPanel;

import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopBarPresenter implements Presenter
{
	public interface TopBarView
	{
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final TopBarView topBarDisplay;
	
	/**
	 * Constructor for the TopBarPresenter.
	 * @pre true;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 */
	public TopBarPresenter(ServiceRepository appServices, HandlerManager eventBus)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.topBarDisplay = new TopBarPanel();
	}
		
	@Override
	public void go(final HasWidgets container)
	{
		container.add(topBarDisplay.asWidget());
	}
}
