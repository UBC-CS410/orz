package stuffplotter.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the friends page view.
 */
public class FriendsPagePanel extends HorizontalPanel
{
	private Button addFriend;
	private Button searchFriends;
	private FriendListPanel friendDisplay;
	
	/**
	 * Constructor for the FriendsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public FriendsPagePanel()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		VerticalPanel buttonHolder = new VerticalPanel();
		this.addFriend = new Button("Add Friend");
		this.searchFriends = new Button("Search Friends");
		buttonHolder.add(this.addFriend);
		buttonHolder.add(this.searchFriends);
		this.add(buttonHolder);
		this.add(new Label("Display of Friends"));
	}
	
	/**
	 * Retrieve the Add Friend button.
	 * @pre true;
	 * @post true;
	 * @return the Add Friend button.
	 */
	public Button getAddFriendBtn()
	{
		return this.addFriend;
	}
	
	/**
	 * Retrieve the Search Friends button.
	 * @pre true;
	 * @post true;
	 * @return the Search Friends button.
	 */
	public Button getSearchFriendsBtn()
	{
		return this.searchFriends;
	}
	
	/**
	 * Retrieve the Friend List panel.
	 * @pre true;
	 * @post true;
	 * @return the Friend List panel.
	 */
	public FriendListPanel getFriendListPanel()
	{
		return this.friendDisplay;
	}
}
