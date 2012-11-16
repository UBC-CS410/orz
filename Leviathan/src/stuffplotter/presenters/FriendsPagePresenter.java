package stuffplotter.presenters;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

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
	}
	
	private FriendsPageView friendsView;
	
	/**
	 * Constructor for the FriendsPagePresenter.
	 * @pre friendsView != null;
	 * @post true;
	 */
	public FriendsPagePresenter(FriendsPageView friendsView)
	{
		this.friendsView = friendsView;
	}


	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub
		
	}

}
