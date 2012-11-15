package stuffplotter.views;

import stuffplotter.shared.Account;
import stuffplotter.views.account.AccountPanel;

import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the account page view.
 */
public class AccountPageView extends SimplePanel
{
	// Note temporary keep account in this view, need to model MVP better
	
	/**
	 * Constructor for the AccountPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AccountPageView(Account account)
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
