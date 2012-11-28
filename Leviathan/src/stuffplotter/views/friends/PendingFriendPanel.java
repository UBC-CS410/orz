package stuffplotter.views.friends;


import stuffplotter.bindingcontracts.AccountModel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PendingFriendPanel extends HorizontalPanel implements PendingFriendView
{
	private static final int IMAGESIZE = 85;
	private Label userName;
	private Label userEmail;
	private Image profilePic;
	private Button confirmButton;
	private Button denyButton;
	
	public PendingFriendPanel(AccountModel model)
	{
		this.initializeUI();
		this.dataBind(model);
	}


	private void initializeUI()
	{
		this.userName = new Label();
		this.userEmail = new Label();
		this.profilePic = new Image("images/profile.jpg");
		this.profilePic.setPixelSize(IMAGESIZE, IMAGESIZE);
		this.confirmButton = new Button("Confirm");
		this.denyButton = new Button("Deny");
		
		HorizontalPanel buttonHolder = new HorizontalPanel();
		buttonHolder.add(confirmButton);
		buttonHolder.add(denyButton);
		
		VerticalPanel infoPanel = new VerticalPanel();
		infoPanel.add(userName);
		infoPanel.add(userEmail);
		infoPanel.add(buttonHolder);
		
		this.add(profilePic);
		this.add(infoPanel);
	}
	
	private void dataBind(AccountModel model)
	{
		if(model.getUserProfilePicture()!=null)
			this.profilePic.setUrl(model.getUserProfilePicture());
		this.userEmail.setText(model.getUserEmail());
		this.userName.setText(model.getUserFullName());

	}


	@Override
	public HasClickHandlers getConfirmBtn()
	{
		return this.confirmButton;
	}


	@Override
	public HasClickHandlers getDenyBtn()
	{
		return this.denyButton;
	}


	@Override
	public String getEmail()
	{
		return this.userEmail.getText();
	}


	@Override
	public String getName()
	{
		return this.userName.getText();
	}

}
