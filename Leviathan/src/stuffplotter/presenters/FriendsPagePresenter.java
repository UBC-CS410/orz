package stuffplotter.presenters;

import com.google.gwt.user.client.ui.HasWidgets;

import stuffplotter.views.FriendsPageView;

/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter implements Presenter
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

	@Override
	public void go(HasWidgets container)
	{
		// TODO Auto-generated method stub
		
	}
}
