package stuffplotter.views;

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
	public AccountPageView()
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
		this.add(new AccountPanel());
	}
}
