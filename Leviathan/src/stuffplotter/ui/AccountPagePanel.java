package stuffplotter.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the account page view.
 */
public class AccountPagePanel extends SimplePanel
{
	/**
	 * Constructor for the AccountPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AccountPagePanel()
	{
		super();
		this.add(new Label("Account"));
	}
}
