package stuffplotter.views;

import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.views.account.AccountPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the account page view.
 */
public class AccountPageView extends SimplePanel implements AccountView
{
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
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
