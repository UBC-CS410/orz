package stuffplotter.ui;

import java.util.List;

import stuffplotter.shared.Notification;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Class to display a user's notifications 
 */
public class UserNotificationsPopupPanel extends PopupPanel
{
	private FlexTable userList;
	private FocusPanel scrollHolder;
	private ScrollPanel notificationHolder;
	private List<Notification> userNotifications;
	
	/**
	 * Constructor for UserNotificationsPopupPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public UserNotificationsPopupPanel(List<Notification> notifications)
	{
		super(false);
		this.scrollHolder = new FocusPanel();
		this.notificationHolder = new ScrollPanel();
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
		if(userNotifications.isEmpty())
		{
			userList.setWidget(0, 0, new Label("No new notifications."));
		}
		else
		{
			for(int i = 0; i < userNotifications.size(); i++)
			{
				userList.setWidget(i, 0, userNotifications.get(i).generateMessage());
			}
		}

		this.scrollHolder.addBlurHandler(new BlurHandler()
		{
			@Override
			public void onBlur(BlurEvent event)
			{
				hide();
			}	
		});
		
		this.notificationHolder.add(userList);
		this.scrollHolder.add(notificationHolder);
		this.add(scrollHolder);
	}
	
	/**
	 * Change the visibility of the notification window.
	 * @pre true;
	 * @post true;
	 */
	public void toggleVisibility()
	{
		if(this.isShowing())
		{
			this.hide();
		}
		else
		{
			this.show();
			this.scrollHolder.setFocus(true);
		}
	}
}