package stuffplotter.views.global;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	private UserNotificationsPopupPanel popup;
	
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
		this.popup = new UserNotificationsPopupPanel(notifications);
		this.logoutLink = new Anchor("Logout", userAccount.getLogoutUrl());
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the TopRightPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		final Label notificationsLabel = new Label("Notifications");
				
		notificationsLabel.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				popup.toggleVisibility();
				popup.setPopupPosition(notificationsLabel.getAbsoluteLeft(), notificationsLabel.getAbsoluteTop() + 20);
			}
		});
		
		this.add(notificationsLabel);
		this.add(new Label(" | "));
		this.add(new Label("Welcome " + this.userName + "!"));
		this.add(new Label(" | "));
		this.add(this.logoutLink);
	}
}