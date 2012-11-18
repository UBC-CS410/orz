package stuffplotter.presenters;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
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
	 * @pre friendsView != null;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the FriendsView to associate with the FriendsPagePresenter.
	 */
	public FriendsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, FriendsView friendsView)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.friendsView = friendsView;
	}


	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.friendsView.asWidget());
	}
}
