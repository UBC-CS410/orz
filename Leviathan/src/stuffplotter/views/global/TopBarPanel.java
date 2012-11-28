package stuffplotter.views.global;

import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.presenters.TopBarPresenter.TopBarView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display logged in user's identity and log out button at top of application.
 */
public class TopBarPanel extends HorizontalPanel implements TopBarView
{
	private Anchor logoutLink;
	private UserNotificationsPopupPanel popup;
	private Label userNameDisplay;
	private Anchor notificationsLabel;
	
	/**
	 * Constructor for TopRightPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public TopBarPanel()
	{
		super();
		this.setStyleName("topBar");
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the TopRightPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{		
		notificationsLabel = new Anchor("Notifications (0)");
		notificationsLabel.setStyleName("topBarNotifications");
		
		popup = new UserNotificationsPopupPanel();
		popup.setPopupPosition(this.notificationsLabel.getAbsoluteLeft(), this.notificationsLabel.getAbsoluteTop()+60);
		
		this.add(notificationsLabel);
		//this.setCellWidth(notificationsLabel, String.valueOf(Window.getClientWidth() - 200) + "px");
		this.setCellWidth(notificationsLabel, "200px");
		
		SimplePanel banner = new SimplePanel();
		banner.addStyleName("banner");
		this.add(banner);
		this.setCellWidth(banner, String.valueOf(Window.getClientWidth() - 400) + "px");	
		
		this.userNameDisplay = new Label();
		Label separator = new Label("|");
		this.logoutLink = new Anchor("Log out");
		
		this.userNameDisplay.setStyleName("topBarUserName");
		separator.setStyleName("topBarSeparator");
		this.logoutLink.setStyleName("topBarLogOut");
		
		this.add(this.userNameDisplay);
		this.add(separator);
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

	@Override
	public void setNotificationLabelText(String text)
	{
		this.notificationsLabel.setText(text);
	}

	@Override
	public HasClickHandlers getNotificationsLabel()
	{
		return this.notificationsLabel;
	}
	
	@Override
	public UserNotificationsPopupPanel getPopUp(){
		return this.popup;
	}


}
