package stuffplotter.presenters;

import stuffplotter.ui.FriendsPagePanel;

/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter
{
	private FriendsPagePanel friendsView;
	
	/**
	 * Constructor for the FriendsPagePresenter.
	 * @pre friendsView != null;
	 * @post true;
	 */
	public FriendsPagePresenter(FriendsPagePanel friendsView)
	{
		this.friendsView = friendsView;
	}
}
