package stuffplotter.ui;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the information for a user's account.
 */
public class AccountPanel extends SimplePanel
{
	private Account userAccount;
	private VerticalPanel informationHolder;
	
	/**
	 * Constructor for AccountPanel.
	 * @pre userAccount != null;
	 * @post this.isVisible() == true;
	 */
	public AccountPanel(Account userAccount)
	{
		super();
		this.userAccount = userAccount;
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the AccountPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.informationHolder = new VerticalPanel();
		this.informationHolder.add(new Label("Account Information"));
		this.informationHolder.add(new Button("Edit"));
		this.informationHolder.add(new InfoPanel("Name", this.userAccount.getUserName()));
		this.informationHolder.add(new InfoPanel("Email", this.userAccount.getUserEmail()));
		this.informationHolder.add(new InfoPanel("Phone", this.userAccount.getUserPhone()));
		int userAge = this.userAccount.getUserAge();
		this.informationHolder.add(new InfoPanel("Age", String.valueOf(userAge)));
		this.add(informationHolder);
	}
	
	/**
	 * Inner class for displaying the information for each field of a user.
	 * Contains the fields name, fields value, and edit/submit button.
	 */
	public class InfoPanel extends HorizontalPanel
	{
		/**
		 * Constructor for the InfoPanel.
		 * @pre fieldName != null;
		 * @post this.isVisible() == true;
		 * @param fieldName - the name of the attribute.
		 * @param fieldValue - the value of the attribute.
		 */
		public InfoPanel(String fieldName, String fieldValue)
		{
			this.add(new Label(fieldName + ": "));
			if(fieldValue != null)
			{
				this.add(new Label(fieldValue));
			}
			else
			{
				this.add(new Label("--"));
			}
		}
	}
	
	/**
	 * Method to return the underlying account of the AccountPanel.
	 * @pre true;
	 * @post true;
	 * @return the Account of the AccountPanel.
	 */
	public Account getAccount()
	{
		return this.userAccount;
	}
}
