package stuffplotter.views.friends;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FriendPanel extends HorizontalPanel 
{
	Label userEmail;
	Label userName;
	Label userTitle;
	Image profile;
	private VerticalPanel infoPanel;
	
	
	public FriendPanel(String email, String name, String title, String profileUrl)
	{
		this.userEmail = new Label(email);
		this.userName = new Label(name);
		this.userTitle = new Label(title);
		
		this.infoPanel = new VerticalPanel();
		this.infoPanel.add(userName);
		this.infoPanel.add(userTitle);
		this.infoPanel.add(userEmail);
		
		//Replace the bottom comment with this one once testing is done.
		//this.profile = new Image(profileUrl);
		this.profile = new Image("http://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Mario_emblem.svg/150px-Mario_emblem.svg.png");
		
		this.add(profile);
		this.add(infoPanel);
	}



}
