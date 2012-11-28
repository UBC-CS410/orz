package stuffplotter.views.account;

import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.presenters.UserAccountPresenter.UserAccountView;
import stuffplotter.shared.Achievement;
import stuffplotter.views.util.InfoPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the information for a user's account.
 */
public class AccountPanel extends SimplePanel implements UserAccountView
{
	private static final int IMAGESIZE = 85;
	private VerticalPanel informationHolder;
	private HorizontalPanel buttonHolder;
	private Button editBtn;
	private Button saveBtn;
	private Button cancelBtn;
	private InfoPanel nameField;
	private InfoPanel emailField;
	private InfoPanel titleField;
	private InfoPanel phoneField;
	private InfoPanel ageField;
	private Image profilePic;
	private Image badgePic;
	private Label newAgelabel;
	private TextBox ageTextBox;
	private Label newTitlelabel;
	private ListBox titleListBox;
	private Label newPhonelabel;
	private TextBox phoneTextBox;
	
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
		this.buttonHolder = new HorizontalPanel();
		this.informationHolder = new VerticalPanel();
		saveBtn = new Button("Save");
		cancelBtn = new Button("Cancel");
		editBtn = new Button("Edit");
		nameField = new InfoPanel("Name","");
		emailField = new InfoPanel("Email","");
		titleField = new InfoPanel("Title","");
		phoneField = new InfoPanel("Phone","");
		ageField = new InfoPanel("Age","");
		
		HorizontalPanel imageHolder = new HorizontalPanel();
		profilePic = new Image("images/profile.jpg");
		profilePic.setPixelSize(IMAGESIZE, IMAGESIZE);
		badgePic = new Image("images/blank.jpg");
		imageHolder.add(profilePic);
		imageHolder.add(badgePic);
		
		newAgelabel = new Label("Enter New Age: ");
		ageTextBox = new TextBox();
		newTitlelabel = new Label("Select New Title: ");
		titleListBox = new ListBox(false);
		titleListBox.addItem("Newb");
		newPhonelabel = new Label("Enter New Phone Number: ");
		phoneTextBox = new TextBox();
		
		
		this.buttonHolder.add(saveBtn);
		this.buttonHolder.add(cancelBtn);
		
		this.informationHolder.add(new Label("Account Information"));
		this.informationHolder.add(imageHolder);
		this.informationHolder.add(nameField);
		this.informationHolder.add(emailField);
		this.informationHolder.add(ageField);
		this.informationHolder.add(titleField);
		this.informationHolder.add(phoneField);
		this.informationHolder.add(editBtn);
		
		this.informationHolder.add(newAgelabel);
		this.informationHolder.add(ageTextBox);
		this.informationHolder.add(newTitlelabel);
		this.informationHolder.add(titleListBox);
		this.informationHolder.add(newPhonelabel);
		this.informationHolder.add(phoneTextBox);
		this.informationHolder.add(buttonHolder);
		
		this.disableEditMode();
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
		this.titleField.setValue(model.getUserTitle());
		this.badgePic.setUrl(model.getBadgePic());
		if(model.getUserProfilePicture()!=null)
			this.profilePic.setUrl(model.getUserProfilePicture());
		if(model.getUserAge().equals("--"))
		{
			this.ageField.setValue(model.getUserAge());
		}
	}
	
	@Override
	public void setUserData(AccountStatisticModel model)
	{
		List<Achievement> achivements = model.getUserAchievements();
		for(Achievement ach: achivements)
		{
			this.titleListBox.addItem(ach.getName());
		}
		
	}

	@Override
	public HasClickHandlers getEditButton()
	{
		return this.editBtn;
	}

	@Override
	public void enableEditMode()
	{
		
		this.editBtn.setVisible(false);
		this.newAgelabel.setVisible(true);
		this.ageTextBox.setVisible(true);
		this.buttonHolder.setVisible(true);
		this.newTitlelabel.setVisible(true);
		this.titleListBox.setVisible(true);
		this.newPhonelabel.setVisible(true);
		this.phoneTextBox.setVisible(true);
	}

	@Override
	public HasClickHandlers getSaveButton()
	{
		return this.saveBtn;
	}

	@Override
	public void disableEditMode()
	{
		this.editBtn.setVisible(true);
		this.newAgelabel.setVisible(false);
		this.ageTextBox.setVisible(false);
		this.buttonHolder.setVisible(false);
		this.newTitlelabel.setVisible(false);
		this.titleListBox.setVisible(false);
		this.newPhonelabel.setVisible(false);
		this.phoneTextBox.setVisible(false);
	}

	@Override
	public HasClickHandlers getCancelButton()
	{
		return this.cancelBtn;
	}

	@Override
	public String getTitleListBox()
	{
		String result = this.titleListBox.getValue(this.titleListBox.getSelectedIndex());
		titleField.setValue(result);
		return result;	
	}

	@Override
	public String getPhoneBox()
	{
		String result = this.phoneTextBox.getText();
		phoneField.setValue(result);
		return result;
	}

	@Override
	public String getAgeBox()
	{
		String result = this.ageTextBox.getText();
		ageField.setValue(result);
		return result;
	}


}
