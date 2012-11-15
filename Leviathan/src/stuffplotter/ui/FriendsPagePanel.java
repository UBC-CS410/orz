package stuffplotter.ui;

import stuffplotter.ui.util.ScrollDisplayPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the friends page view.
 */
public class FriendsPagePanel extends HorizontalPanel
{
	private static final int NUMOFCOLUMNS = 3;
	private Button addFriend;
	private Button searchFriends;
	private ScrollDisplayPanel friendDisplay;
	
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
		this.friendDisplay = new ScrollDisplayPanel(NUMOFCOLUMNS);
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.friendDisplay.addElement(new Label("Display of Friends"));
		this.add(this.friendDisplay);
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
	public ScrollDisplayPanel getFriendListPanel()
	{
		return this.friendDisplay;
	}
}
