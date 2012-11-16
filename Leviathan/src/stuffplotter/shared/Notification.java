package stuffplotter.shared;

import java.util.Date;

import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to hold the information for a user notification.
 */
public abstract class Notification
{
	public enum NotificationType
	{
		FRIENDREQUEST, EVENTINVITATION, ACHIEVEMENTGET;
	}

	/**
	 * ID of what generated the notification.
	 */
	private NotificationType type;
	private String notificationFrom;
	private boolean newNotification = true;
	private Date notificationTime;

	/**
	 * Constructor for a Notification.
	 * @pre id >= 0 && type != null && fromUser != null && forUser != null;
	 * @post this.id == id && this.type.equals(type) &&
	 * this.notificationFrom.equals(fromUser) && this.notificationFor.equals(forUser);
	 * @param id - the ID of what generated the notification.
	 * @param type - the type of the notification.
	 * @param from - the user the generated the notification.
	 * @param forUser - the user the notification is for.
	 */
	public Notification(NotificationType type, String from)
	{
		this.type = type;
		this.notificationFrom = from;
		this.notificationTime = new Date();
	}

	/**
	 * Method to retrieve the type of the notification.
	 * @pre true;
	 * @post true;
	 * @return the type of the notification.
	 */
	public NotificationType getType()
	{
		return this.type;
	}

	/**
	 * Retrieve the name of the user who generated the notification.
	 * @pre true;
	 * @post true;
	 * @return the name of the user who generated the notification.
	 */
	public String getFrom()
	{
		return this.notificationFrom;
	}


	/**
	 * Method to generate the message for the notification.
	 * @pre true;
	 * @post true;
	 * @return the message for the notification.
	 */
	public abstract SimplePanel generateMessage();
}