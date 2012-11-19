package stuffplotter.presenters;

import java.util.Iterator;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.views.FriendsPageView;

/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter implements Presenter
{
	public interface FriendsView
	{
		public HasClickHandlers getAddFriendBtn();
		public HasClickHandlers getSearchFriendsBtn();
		//public FriendList getFriendDisplay() create presenter for this
		/**
		 * Retrieve the FriendsView as a Widget.
		 * @pre true;
		 * @post true;
		 * @return the FriendsView as a Widget.
		 */
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final FriendsView friendsView;
	
	/**
	 * Constructor for the FriendsPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the FriendsView to associate with the FriendsPagePresenter.
	 */
	public FriendsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, FriendsView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.friendsView = display;
	}

	/**
	 * Bind friends view components to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Present the friends view
	 * @pre true;
	 * @post this.homeView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.friendsView.asWidget());
	}
}
