package stuffplotter.UI;

import java.util.List;

import stuffplotter.shared.Notification;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Class to display a user's notifications 
 */
public class UserNotificationsPopupPanel extends PopupPanel
{
	private FlexTable userList;
	private List<Notification> userNotifications;
	
	/**
	 * Constructor for UserNotificationsPopupPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public UserNotificationsPopupPanel(List<Notification> notifications)
	{
		super(false);
		this.userNotifications = notifications;
		initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the UserNotificationsPopupPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		userList = new FlexTable();
		
		for(int i = 0; i < userNotifications.size(); i++)
		{
			userList.setWidget(i, 0, userNotifications.get(i).generateMessage());
		}
	}
}