package stuffplotter.presenters;

import com.google.gwt.event.dom.client.HasClickHandlers;

import stuffplotter.client.services.ServiceRepository;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuBarPresenter implements Presenter
{
	public interface MenuBarView
	{
		public HasClickHandlers getHomeBtn();
		public HasClickHandlers getAccountBtn();
		public HasClickHandlers getEventsBtn();
		public HasClickHandlers getFriendsBtn();
		public HasClickHandlers getAchievementsBtn();
		public Widget asWidget();
	}
	
	private final ServiceRepository appServies;
	private final HandlerManager eventBus;
	
	private final MenuBarView menuBarView;
		
	/**
	 * Constructor for the MenuBarPresenter.
	 * @pre true;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 */
	public MenuBarPresenter(ServiceRepository appServices, HandlerManager eventBus, MenuBarView display)
	{
		this.appServies = appServices;
		this.eventBus = eventBus;
		this.menuBarView = display;
	}
	
	private void bind()
	{

	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.menuBarView.asWidget());
	}
}
