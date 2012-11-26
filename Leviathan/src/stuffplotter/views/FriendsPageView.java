package stuffplotter.views;

import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.presenters.FriendsPagePresenter.FriendsView;

import stuffplotter.views.friends.FriendPanel;
import stuffplotter.views.friends.FriendPanelView;
import stuffplotter.views.friends.FriendsDisplayPanel;
import stuffplotter.views.friends.PendingFriendPanel;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the friends page view.
 */
public class FriendsPageView extends HorizontalPanel implements FriendsView
{
	private static final int NUMOFCOLUMNS = 1;
	private Button addFriend;
	private Button searchFriends;
	private TextBox addFriendTextBox;
	private FriendsDisplayPanel friendDisplay;
	private ScrollDisplayPanel pendingFriendDisplay;
	
	/**
	 * Constructor for the FriendsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public FriendsPageView()
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
		HorizontalPanel addFriendPanel = new HorizontalPanel();
		
		this.addFriend = new Button("Add Friend");
		this.addFriendTextBox = new TextBox();
		this.addFriendTextBox.setWidth("250px");
		this.addFriendTextBox.setText("Example: stuffplotter001@gmail.com");
		
		
		this.searchFriends = new Button("Search Friends");
		this.pendingFriendDisplay = new ScrollDisplayPanel(NUMOFCOLUMNS);
		this.pendingFriendDisplay.addElement(new Label("Pending Friend"));

		
		addFriendPanel.add(this.addFriend);
		addFriendPanel.add(this.addFriendTextBox);
		buttonHolder.add(addFriendPanel);
		buttonHolder.add(this.searchFriends);
		buttonHolder.add(this.pendingFriendDisplay);
		
		
		this.add(buttonHolder);
		this.friendDisplay = new FriendsDisplayPanel(NUMOFCOLUMNS);
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
	

	@Override
	public Widget asWidget()
	{
		return this;
	}

	@Override
	public String getFriendBoxText()
	{
		return this.addFriendTextBox.getText();
	}


	@Override
	public void clearFriendBoxText()
	{
		this.addFriendTextBox.setText("");
		
	}

	@Override
	public void addPendingUsers(PendingFriendPanel pendPan)
	{
		this.pendingFriendDisplay.addElement(pendPan);
	}

	@Override
	public void addFriendPanel(FriendPanel friendPan)
	{	
		this.friendDisplay.addElement(friendPan);
	}

	@Override
	public HasAllFocusHandlers getFriendTextBox()
	{
		return this.addFriendTextBox;
	}

	@Override
	public List<FriendPanelView> getFriendPanels()
	{
		return this.friendDisplay.getFriendPanels();
	}

	@Override
	public void setFriendData(List<AccountModel> models)
	{
		this.friendDisplay.setFriendsData(models);
	}
}
