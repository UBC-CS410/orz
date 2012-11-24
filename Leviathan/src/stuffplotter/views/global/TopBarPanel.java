package stuffplotter.views.global;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.shared.Notification;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display logged in user's identity and log out button at top of application.
 */
public class TopBarPanel extends HorizontalPanel implements TopBarView
{
	private Anchor logoutLink;
	private UserNotificationsPopupPanel popup;
	private Label userNameDisplay;
	
	/**
	 * Constructor for TopRightPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public TopBarPanel()
	{
		super();
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
		popup = new UserNotificationsPopupPanel();
		
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
		this.userNameDisplay = new Label("Welcome!");
		this.add(this.userNameDisplay);
		this.add(new Label(" | "));
		this.logoutLink = new Anchor("Logout");
		this.logoutLink.setEnabled(false);
		this.add(this.logoutLink);
	}

	/**
	 * Set the user data for the TopBarPanel.
	 * @pre userAccount != null;
	 * @post true;
	 * @param userAccount - the user account to data bind with the view. 	 
	 */
	@Override
	public void setUserData(AccountModel userAccount)
	{
		this.userNameDisplay.setText(userAccount.getUserFullName());
		this.logoutLink.setHref(userAccount.getLogoutUrl());
	}
	
	/**
	 * Refresh the notifications panel.
	 * @pre notification != null;
	 * @post true;
	 * @param notifications - the list of notifications to display.
	 */
	@Override
	public void setNotificationData(List<NotificationModel> notifications)
	{
		this.popup.setNotificationData(notifications);
	}
	
	/**
	 * Returns this as a widget so that other views can add this
	 * @pre true;
	 * @post true;
	 * @return this;
	 */
	public Widget asWidget()
	{
		return this;
	}
}
