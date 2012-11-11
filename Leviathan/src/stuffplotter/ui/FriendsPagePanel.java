package stuffplotter.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the friends page view.
 */
public class FriendsPagePanel extends SimplePanel
{
	/**
	 * Constructor for the FriendsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public FriendsPagePanel()
	{
		super();
		this.add(new Label("Friends"));
	}
}
