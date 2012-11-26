package stuffplotter.views.account;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.presenters.UserAccountPresenter.UserAccountView;
import stuffplotter.views.util.InfoPanel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the information for a user's account.
 */
public class AccountPanel extends SimplePanel implements UserAccountView
{
	private VerticalPanel informationHolder;
	private Button editBtn;
	private InfoPanel nameField;
	private InfoPanel emailField;
	private InfoPanel phoneField;
	private InfoPanel ageField;
	
	/**
	 * Constructor for AccountPanel.
	 * @pre userAccount != null;
	 * @post this.isVisible() == true;
	 */
	public AccountPanel()
	{
		super();
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
		editBtn = new Button("Edit");
		nameField = new InfoPanel("Name","");
		emailField = new InfoPanel("Email","");
		phoneField = new InfoPanel("Phone","");
		ageField = new InfoPanel("Age","");
		this.informationHolder.add(new Label("Account Information"));
		this.informationHolder.add(editBtn);
		this.informationHolder.add(nameField);
		this.informationHolder.add(emailField);
		this.informationHolder.add(phoneField);
		this.informationHolder.add(ageField);
		this.add(informationHolder);
	}
	
	/**
	 * Set the data to display for the AccountPanel.
	 * @pre model != null;
	 * @post true;
	 * @param model - the user account to display.
	 */
	@Override
	public void setUserData(AccountModel model)
	{
		this.nameField.setValue(model.getUserFullName());
		this.emailField.setValue(model.getUserEmail());
		this.phoneField.setValue(model.getUserPhone());
		if(model.getUserAge() > 0)
		{
			this.ageField.setValue(String.valueOf(model.getUserAge()));
		}
	}
}
