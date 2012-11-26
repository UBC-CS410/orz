package stuffplotter.views.friends;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PendingFriendPanel extends HorizontalPanel
{
	private static final int IMAGESIZE = 85;
	private Image profilePic;
	private VerticalPanel infoPanel;
	private Label userName;
	private Label userEmail;
	private HorizontalPanel buttonHolder;
	private Button confirmButton;
	private Button denyButton;
	
	public PendingFriendPanel(String email, String name, String profileUrl)
	{
		this.userName = new Label(name);
		this.userEmail = new Label(email);
		if(profileUrl!=null)
			this.profilePic = new Image(profileUrl);
		else
			this.profilePic = new Image("http://i983.photobucket.com/albums/ae312/robzile/Mario-Box-question-mark.gif");
		
		this.buttonHolder = new HorizontalPanel();
		this.confirmButton = new Button("Confirm");
		this.denyButton = new Button("Deny");
		this.buttonHolder.add(confirmButton);
		this.buttonHolder.add(denyButton);
		
		
		this.profilePic.setPixelSize(IMAGESIZE, IMAGESIZE);
		this.infoPanel = new VerticalPanel();
		this.infoPanel.add(userName);
		this.infoPanel.add(userEmail);
		this.infoPanel.add(buttonHolder);
		
		
		
		
		this.add(profilePic);
		this.add(infoPanel);

	};
	
	public HasClickHandlers getConfirmButton(){
		return this.confirmButton;
	}
	
	public HasClickHandlers getDenyButton(){
		return this.denyButton;
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
