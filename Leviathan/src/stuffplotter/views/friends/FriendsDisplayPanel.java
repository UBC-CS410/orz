package stuffplotter.views.friends;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import stuffplotter.bindingcontracts.FriendModel;
import stuffplotter.views.util.ScrollDisplayPanel;

/**
 * Class to display Friends.
 */
public class FriendsDisplayPanel extends ScrollDisplayPanel
{
	/**
	 * Constructor for the FrinedsDisplayPanel/
	 * @pre numOfColumns > 0;
	 * @post true;
	 * @param numOfColumns - the number of columns to display the friends as.
	 */
	public FriendsDisplayPanel(int numOfColumns)
	{
		super(numOfColumns);
	}
	
	/**
	 * Display the given FriendModel.
	 * @pre friend != null;
	 * @post true;
	 * @param friend - the friend to display.
	 * @return the view button for the friend.
	 */
	public HasClickHandlers setFriendsData(FriendModel friend)
	{
		FriendPanel friendPanel = new FriendPanel(friend);
		this.addElement(friendPanel);
		return friendPanel.getViewBtn();
	}

	/**
	 * Inner class to display Friends using data binding from the FriendModel.
	 */
	public class FriendPanel extends VerticalPanel
	{
		private Button viewBtn;
		
		/**
		 * Constructor for the AchievementPanel.
		 * @pre model != null;
		 * @post truel;
		 * @param model - the Achievement to data bind with and display.
		 */
		public FriendPanel(FriendModel model)
		{
			this.dataBind(model);
			this.initializeUI();
		}
		
		/**
		 * Bind the given model to the view.
		 * @pre model != null;
		 * @post true;
		 * @param model - the model to bind to the display panel.
		 */
		private void dataBind(FriendModel model)
		{
			// TO DO
		}
		
		/**
		 * Helper method to initialize the UI.
		 * @pre true;
		 * @post true;
		 */
		private void initializeUI()
		{
			this.viewBtn = new Button("View");
		}
		
		/**
		 * Retrieve the view button for the friend.
		 * @pre true;
		 * @post true;
		 * @return the view button for the friend.
		 */
		public Button getViewBtn()
		{
			return this.viewBtn;
		}
	}
}
