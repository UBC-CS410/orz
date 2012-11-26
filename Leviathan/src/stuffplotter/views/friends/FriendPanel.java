package stuffplotter.views.friends;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FriendPanel extends HorizontalPanel 
{
	private static final int IMAGESIZE = 85;
	Label userEmail;
	Label userName;
	Label userTitle;
	Image profile;
	private VerticalPanel infoPanel;
	private HorizontalPanel buttonHolder;
	Button removeFriend;
	Button viewProfile;
	
	
	
	public FriendPanel(String email, String name, String title, String profileUrl)
	{
		this.userEmail = new Label(email);
		this.userName = new Label(name);
		this.userTitle = new Label(title);
		
		this.removeFriend = new Button("Remove Friend");
		this.viewProfile = new Button("View Profile");
		this.buttonHolder = new HorizontalPanel();
		this.buttonHolder.add(viewProfile);
		this.buttonHolder.add(removeFriend);
		
		
		this.infoPanel = new VerticalPanel();
		this.infoPanel.add(userName);
		this.infoPanel.add(userEmail);
		this.infoPanel.add(userTitle);
		this.infoPanel.add(buttonHolder);
		
		if(profileUrl!=null)
			this.profile = new Image(profileUrl);
		else
			this.profile = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/archive/b/bf/20101105230325!Mario_emblem.svg/120px-Mario_emblem.svg.png");
		
		this.profile.setPixelSize(IMAGESIZE, IMAGESIZE);
		this.add(profile);
		this.add(infoPanel);
	}
	
	public HasClickHandlers getRemoveFriendButton(){
		return this.removeFriend;
	}
	
	public HasClickHandlers getViewProfileButton(){
		return this.viewProfile;
	}



}
