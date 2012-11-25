package stuffplotter.views.global;

import java.util.List;

import stuffplotter.bindingcontracts.NotificationModel;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
	
	/**
	 * Constructor for UserNotificationsPopupPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public UserNotificationsPopupPanel()
	{
		super(false);
		initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the UserNotificationsPopupPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.scrollHolder = new FocusPanel();
		this.notificationHolder = new ScrollPanel();
		userList = new FlexTable();
		userList.setWidget(0, 0, new Label("No new notifications."));

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
	
	/**
	 * Set the notifications to display in the notification window.
	 * @pre notification != null;
	 * @post true;
	 * @param notifications - the list of notifications to display.
	 */
	public void setNotificationData(List<NotificationModel> notifications)
	{
		for(int i = 0; i<notifications.size(); i++)
		{
			String display = "";
			if(notifications.get(i).getNewNotification())
				display = "**";
			display = display+notifications.get(i).getNotificationDisplay();
			userList.setWidget(i, 0, new Label(display));
		}

		
	}
}