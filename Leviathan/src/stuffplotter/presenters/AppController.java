package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.MasterView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

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
		
		/**
		 * Retrieve the main view panel which has the simulated pages for the different pages.
		 * @pre true;
		 * @post true;
		 * @return the MainView which has the simulated pages for the different pages.
		 */
		public MainView getMainView();
	}
	
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
		MasterView masterView = new MasterView();
		container.add(masterView);
		this.masterView = masterView;
		this.bind();
	}
}
