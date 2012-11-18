package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.MasterView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the MasterView class.
 */
public class MasterPresenter implements Presenter
{
	public interface MasterViewer
	{
		/**
		 * Retrieve the top view which has the banner and top right panel stuff.
		 * @pre true;
		 * @post true;
		 * @return the TopBarView which has the banner and top right panel stuff.
		 */
		public TopBarView getTopPanel();
		
		/**
		 * Retrieve the page options view which has the "links" to the different pages. 
		 * @pre true;
		 * @post true;
		 * @return the MenuBarView which has the "links" to the different pages.
		 */
		public MenuBarView getMenuPanel();
		
		/**
		 * Retrieve the main view for the application.
		 * @pre true;
		 * @post true;
		 * @return the MainView which has the pages for the application.
		 */
		public MainView getMainPanel();
		
		/**
		 * Retrieve the MasterViewer as a widget.
		 * @pre true;
		 * @post true;
		 * @return the MasterViewer as a widget.
		 */
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final MasterView masterView;
	
	/**
	 * Constructor for the MasterPresenter.
	 * @pre true;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the MasterView to associate with the MasterPresenter.
	 */
	public MasterPresenter(ServiceRepository appServices, HandlerManager eventBus, MasterView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.masterView = display;
	}

	@Override
	public void go(final HasWidgets container)
	{	
		
		
		Presenter topBarPresenter = new TopBarPresenter(this.appServices,
														this.eventBus,
														this.masterView.getTopPanel());
		topBarPresenter.go(this.masterView);
		
		Presenter menuBarPresenter = new MenuBarPresenter(this.appServices,
														  this.eventBus,
														  this.masterView.getMenuPanel());
		menuBarPresenter.go(this.masterView);
		
		Presenter mainViewPresenter = new ApplicationPagingPresenter(this.appServices,
																	 this.eventBus,
																	 this.masterView.getMainPanel());
		mainViewPresenter.go(this.masterView);
		
		container.add(this.masterView.asWidget());
	}
}
