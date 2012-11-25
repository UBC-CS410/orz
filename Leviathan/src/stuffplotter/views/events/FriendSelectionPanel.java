package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.shared.Account;
import stuffplotter.views.friends.FriendsDisplayPanel;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the friends to select to invite to an event.
 */
public class FriendSelectionPanel extends SimplePanel implements EventSubmittable
{
	private static final int NUMOFCOLUMNS = 3;
	private FriendsDisplayPanel friendDisplayer;
	private static final String PANELWIDTH = "500px";
	private static final String PANELHEIGHT = "300px";
	
	/**
	 * Constructor for FriendSelectionPanel.
	 * @pre userAccount != null;
	 * @post this.isVisible() == true;
	 */
	public FriendSelectionPanel()
	{
		super();
		this.initializeUI();
	}

	/**
	 * Helper method to add the friends to the FriendSelectionPanel.
	 * @pre userAccount != null; 
	 * @post true;
	 * @param userAccount
	 */
	private void initializeUI()
	{	
		this.friendDisplayer = new FriendsDisplayPanel(NUMOFCOLUMNS);
		this.friendDisplayer.setSize(PANELWIDTH, PANELHEIGHT);
		friendDisplayer.addElement(new Label("You have no friends to add yet, go invite some!"));				
		this.add(friendDisplayer);
		this.setSize(PANELWIDTH, PANELHEIGHT);
	}

	/**
	 * Helper method to retrieve the friends (emails) selected to invite to an event.
	 * @pre true;
	 * @post true;
	 * @return the list of friends (populated in the UI) to invite to the event.
	 */
	public List<String> getFriendList()
	{
		FlexTable friendDisplay = this.friendDisplayer.getDisplayer();
		Iterator<Widget> iterator = friendDisplay.iterator();
		List<String> friendList = new ArrayList<String>();
		
		// while loop to retrieve the selected friends
		while(iterator.hasNext())
		{
			Widget friend = iterator.next();
			if(friend instanceof FriendPanel)
			{
				if(((FriendPanel) friend).isSelected())
				{
					friendList.add(((FriendPanel) friend).getEmail());
				}
			}
		}
		
		return friendList;
	}
	
	/**
	 * Sets the error message to be displayed in the FriendSelectionPanel if an error occurs. 
	 * @pre true;
	 * @post true;
	 */
	public void setErrorMessage()
	{
		this.friendDisplayer.getDisplayer().clear();
		this.friendDisplayer.addElement(new Label("Failed to retrieve list of friends, please " +
												  "try again."));
	}
	
	/**
	 * Set the list of friends to display.
	 * @pre true;
	 * @post true;
	 * @param friends - the list of friends to display.
	 */
	public void setFriendData(List<AccountModel> friends)
	{
		if(!friends.isEmpty())
		{
			this.friendDisplayer.getDisplayer().clear();
			for(AccountModel friend : friends)
			{
				this.friendDisplayer.addElement(new FriendPanel(friend.getUserFullName(),
																friend.getUserEmail(),
																friend.getUserProfilePicture()));
			}
		}
	}
	
	/**
	 * Method to accept an EventCreationPageVisitor and have it perform certain tasks.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	@Override
	public void accept(EventCreationPageVisitor eventVisitor)
	{
		eventVisitor.visit(this);
	}
	
	/**
	 * Inner class for displaying friends in the friend selection window.
	 */
	public class FriendPanel extends HorizontalPanel
	{
		private static final int HEIGHT = 50;
		private static final int WIDTH = 50;
		private String emailAddress;
		private CheckBox selectedValue;
		
		/**
		 * Constructor for the FriendPanel to display a friend in the FriendSelectionPanel.
		 * @pre fullName != null && emailAddress != null && profilePic != null;
		 * @post true;
		 */
		public FriendPanel(String fullName, String emailAddress, String profilePic)
		{
			this.emailAddress = emailAddress;
			this.selectedValue = new CheckBox();
			
			Image profilePicture = new Image(profilePic, 0, 0, WIDTH, HEIGHT);
			
			this.add(this.selectedValue);
			this.add(profilePicture);
			this.add(new Label(fullName));
		}
		
		/**
		 * Retrieves the email of the selected friend.
		 * @pre true;
		 * @post true;
		 * @return the email of the selected friend.
		 */
		public String getEmail()
		{
			return this.emailAddress;
		}
		
		/**
		 * Determine if the friend has been selected.
		 * @pre true;
		 * @post true;
		 * @return true if the friend has been selected; false otherwise.
		 */
		public boolean isSelected()
		{
			return this.selectedValue.getValue();
		}
	}
}
