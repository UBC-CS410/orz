package stuffplotter.views.friends;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.views.util.ScrollDisplayPanel;

/**
 * Class to display Friends.
 */
public class FriendsDisplayPanel extends ScrollDisplayPanel
{
	List<FriendPanelView> friendViews;
	List<PendingFriendPanel> pendingFriendViews;
	
	/**
	 * Constructor for the FrinedsDisplayPanel/
	 * @pre numOfColumns > 0;
	 * @post true;
	 * @param numOfColumns - the number of columns to display the friends as.
	 */
	public FriendsDisplayPanel(int numOfColumns)
	{
		super(numOfColumns);
		this.friendViews = new ArrayList<FriendPanelView>();
		this.pendingFriendViews = new ArrayList<PendingFriendPanel>();
	}
	
	/**
	 * Display the given AccountModels.
	 * @pre friends != null;
	 * @post true;
	 * @param friends - the friends to display.
	 */
	public void setFriendsData(List<AccountModel> friends)
	{
		this.friendViews.clear();
		this.clearDisplay();
		
		// for loop to populate the view
		for(AccountModel model : friends)
		{
			FriendPanel friendPanel = new FriendPanel(model);
			this.addElement(friendPanel);
			this.friendViews.add(friendPanel);
		}
	}

	/**
	 * Retrieve the FriendPanelViews in this display.
	 * @pre true;
	 * @post true;
	 * @return the FriendPanelViews in thi display.
	 */
	public List<FriendPanelView> getFriendPanels()
	{
		return this.friendViews;
	}

	public void setPendingFriendsData(List<AccountModel> friends)
	{
		this.friendViews.clear();
		this.clearDisplay();
		
		// for loop to populate the view
		for(AccountModel model : friends)
		{
			PendingFriendPanel friendPanel = new PendingFriendPanel(model);
			this.addElement(friendPanel);
			this.pendingFriendViews.add(friendPanel);
		}
		
	}

	public List<PendingFriendPanel> getPendingFriendPanels()
	{
		return this.pendingFriendViews;
	}
}
