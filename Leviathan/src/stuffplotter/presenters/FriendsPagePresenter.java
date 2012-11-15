package stuffplotter.presenters;

import stuffplotter.views.FriendsPageView;

/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter
{
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
}
