package stuffplotter.UI;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Class to display logged in user's identity and log out button at top of application.
 */
public class TopRightPanel extends HorizontalPanel
{
	private String userName;
	private Anchor logoutLink;
	private List<Notification> notifications;
	
	/**
	 * Constructor for TopRightPanel.
	 * @pre userAccount != null;
	 * @post this.isVisible() == true && 
	 * 		 this.userName.equals(userAccount.getUserName()) &&
	 * 		 this.logouLink.getHref().equals(userAccount.getLogout());
	 * @param userAccount - the Account of the user that is logged in.
	 */
	public TopRightPanel(Account userAccount)
	{
		super();
		this.userName = userAccount.getUserName();
		this.notifications = userAccount.getUserNotifications();
		this.logoutLink = new Anchor("Logout", userAccount.getLogout());
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the TopRightPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.add(new Label("Notifications"));
		this.add(new Label("Welcome " + this.userName + "!"));
		this.add(new Label("|"));
		this.add(this.logoutLink);
		this.add(new UserNotificationsPopupPanel(this.notifications));
	}
}
