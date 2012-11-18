package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView.View;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.shared.Account;
import stuffplotter.views.MasterView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		public TopBarView getTopView();
		
		/**
		 * Retrieve the page options view which has the "links" to the different pages. 
		 * @pre true;
		 * @post true;
		 * @return the MenuBarView which has the "links" to the different pages.
		 */
		public MenuBarView getMenuView();
		
		/**
		 * Retrieve the main view for the application.
		 * @pre true;
		 * @post true;
		 * @return the MainView which has the pages for the application.
		 */
		public MainView getMainView();
		
		/**
		 * Retrieve the MasterViewer as a widget.
		 * @pre true;
		 * @post true;
		 * @return the MasterViewer as a widget.
		 */
		public Widget asWidget();
	}
	
	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final MasterViewer masterView;
	
	/**
	 * Constructor for the MasterPresenter.
	 * @pre true;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the MasterView to associate with the MasterPresenter.
	 */
	public MasterPresenter(ServiceRepository appServices, HandlerManager eventBus, MasterViewer display, Account user)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.masterView = display;
		this.appUser = user;
	}

	/**
	 * Helper method to add the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.masterView.getMenuView().getHomeBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				masterView.getMainView().showView(View.HOME);
			}
		});
		
		this.masterView.getMenuView().getAccountBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				masterView.getMainView().showView(View.ACCOUNT);
			}
		});
		
		this.masterView.getMenuView().getEventsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Presenter eventsPagePresenter = new EventsPagePresenter(
						appServices.getEventService(), 
						eventBus, 
						masterView.getMainView(), 
						appUser);
				eventsPagePresenter.go((HasWidgets) masterView);
			}
		});
		
		this.masterView.getMenuView().getFriendsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				masterView.getMainView().showView(View.FRIENDS);
			}
		});
		
		this.masterView.getMenuView().getAchievementsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				masterView.getMainView().showView(View.ACHIEVEMENTS);
			}
		});
	}	
	
	@Override
	public void go(final HasWidgets container)
	{	
		this.bind();
		
		Presenter topBarPresenter = new TopBarPresenter(this.appServices,
														this.eventBus,
														this.masterView.getTopView());
		topBarPresenter.go((HasWidgets) this.masterView);
		
		Presenter menuBarPresenter = new MenuBarPresenter(this.appServices,
														  this.eventBus,
														  this.masterView.getMenuView());
		menuBarPresenter.go((HasWidgets) this.masterView);
		
		Presenter mainViewPresenter = new ApplicationPagingPresenter(this.appServices,
																	 this.eventBus,
																	 this.masterView.getMainView());
		mainViewPresenter.go((HasWidgets) this.masterView);
		
		container.add(this.masterView.asWidget());
	}
}