package stuffplotter.views.friends;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import stuffplotter.bindingcontracts.AccountModel;

/**
 * Class to display Friends using data binding from the FriendModel.
 */
public class FriendPanel extends HorizontalPanel implements FriendPanelView
{
	private static final int IMAGESIZE = 85;
	private Label userEmail;
	private Label userName;
	private Label userTitle;
	private Image profile;
	private Button viewBtn;
	private Button removeBtn;
	
	/**
	 * Constructor for the AchievementPanel.
	 * @pre model != null;
	 * @post truel;
	 * @param model - the Achievement to data bind with and display.
	 */
	public FriendPanel(AccountModel model)
	{
		this.initializeUI();
		this.dataBind(model);
	}
	
	/**
	 * Bind the given model to the view.
	 * @pre model != null;
	 * @post true;
	 * @param model - the model to bind to the display panel.
	 */
	private void dataBind(AccountModel model)
	{
		this.profile.setUrl(model.getUserProfilePicture());
		this.userEmail.setText(model.getUserEmail());
		this.userName.setText(model.getUserFullName());
		this.userTitle.setText(model.getUserTitle());
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.userEmail = new Label();
		this.userName = new Label();
		this.userTitle = new Label();
		this.profile = new Image("http://i983.photobucket.com/albums/ae312/robzile/Mario-Box-question-mark.gif");
		this.profile.setPixelSize(IMAGESIZE, IMAGESIZE);
		this.removeBtn = new Button("Remove");
		this.viewBtn = new Button("View");
		
		HorizontalPanel buttonHolder = new HorizontalPanel();
		buttonHolder.add(viewBtn);
		buttonHolder.add(removeBtn);
		
		VerticalPanel infoPanel = new VerticalPanel();
		infoPanel.add(userName);
		infoPanel.add(userEmail);
		infoPanel.add(userTitle);
		infoPanel.add(buttonHolder);
		
		this.add(profile);
		this.add(infoPanel);
	}
	
	@Override
	public Button getViewBtn()
	{
		return this.viewBtn;
	}

	@Override
	public HasClickHandlers getRemoveBtn()
	{
		return this.removeBtn;
	}

	@Override
	public String getEmail()
	{
		return this.userEmail.getText();
	}
}