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
		this.profilePic = new Image("http://i983.photobucket.com/albums/ae312/robzile/Mario-Box-question-mark.gif");
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





	
//	private Label pendingUser;
//	private Button confirmButton;
//	public PendingFriendPanel(final String pendUser, final Account appUser)
//	{
//		final AccountServiceAsync accountService = GWT.create(AccountService.class);
//		this.pendingUser = new Label(pendUser);
//		this.confirmButton = new Button("Confirm");
//		confirmButton.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event)
//			{
//				accountService.confirmFriendReq(appUser, pendUser, new AsyncCallback<Void>(){
//
//					@Override
//					public void onFailure(Throwable caught)
//					{
//						Window.alert("Friend was not successfully added...");
//					}
//
//					@Override
//					public void onSuccess(Void result)
//					{
//						Window.alert("Friend successfully added!!");
//						confirmButton.setVisible(false);
//					}
//					
//				});
//				
//			}
//			
//		});
//		this.add(pendingUser);
//		this.add(confirmButton);
//	}

}
