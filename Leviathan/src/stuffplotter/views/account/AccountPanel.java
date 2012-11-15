package stuffplotter.views.account;

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
}
