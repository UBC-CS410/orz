package stuffplotter.ui;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the account page view.
 */
public class AccountPagePanel extends SimplePanel
{
	// Note temporary keep account in this view, need to model MVP better
	
	/**
	 * Constructor for the AccountPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AccountPagePanel(Account account)
	{
		super();
		this.initializeUI(account);
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI(Account account)
	{
		this.add(new AccountPanel(account));
	}
}
