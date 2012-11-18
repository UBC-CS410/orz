package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView.View;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.MasterView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class AppController implements Presenter
{
	public interface MasterViewer
	{
		/**
		 * Retrieve the top panel which has the banner and top right panel stuff.
		 * @pre true;
		 * @post true;
		 * @return the TopBarView which has the banner and top right panel stuff.
		 */
		public TopBarView getTopPanel();
		
		/**
		 * Retrieve the page options panel which has the "links" to the different pages. 
		 * @pre true;
		 * @post true;
		 * @return the MenuBarView which has the "links" to the different pages.
		 */
		public MenuBarView getMenuPanel();
		
		public Widget asWidget();
	}
	
	private final HandlerManager eventBus;
	private final ServiceRepository rpcServices;
	
	private HasWidgets container;

	
	/**
	 * Constructor for the AppController.
	 * @pre serviceRepository != null && eventBus != null;
	 * @post true;
	 * @param serviceRepository - a repository containing all the asynchronous services.
	 * @param eventBus - the HandlerManager to help components signal other components.
	 */
	public AppController(ServiceRepository serviceRepository, HandlerManager eventBus)
	{
		this.rpcServices = serviceRepository;
		this.eventBus = eventBus;
	}
	
	/**
	 * Add handlers to objects.
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
	
		Presenter presenter = new MasterPresenter();
		presenter.go(this.container);

	}
}
