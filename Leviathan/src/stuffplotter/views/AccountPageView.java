package stuffplotter.views;

import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.presenters.AccountStatisticPresenter.AccountStatisticView;
import stuffplotter.presenters.UserAccountPresenter.UserAccountView;
import stuffplotter.views.account.AccountPanel;
import stuffplotter.views.account.StatisticPanel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the account page view.
 */
public class AccountPageView extends HorizontalPanel implements AccountView
{
	private UserAccountView accountView;
	private AccountStatisticView statisticView;
	
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
		this.accountView = new AccountPanel();
		this.statisticView = new StatisticPanel();
	}
	
	@Override
	public UserAccountView getAccountPanel()
	{
		return this.accountView;
	}
	
	@Override
	public AccountStatisticView getStatisticsPanel()
	{
		return this.statisticView;
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
