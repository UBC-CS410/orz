package stuffplotter.ui;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Class to display the list of friends a user has.
 */
public class FriendListPanel extends ScrollPanel
{
	private FlexTable friendDisplayer;
	
	/**
	 * Constructor for the FriendListPanel.
	 * @pre true;
	 * @post true;
	 */
	public FriendListPanel()
	{
		this.friendDisplayer = new FlexTable();
	}
	
	/**
	 * Add the given panel to the friend display.
	 * @pre friendPanel != null;
	 * @post true;
	 * @param friendPanel - the panel to add to the friend display.
	 */
	public void addFriendPanel(Panel friendPanel)
	{
		// TO DO
	}
}
