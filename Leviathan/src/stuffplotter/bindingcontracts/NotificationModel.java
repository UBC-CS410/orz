package stuffplotter.bindingcontracts;

import java.util.Date;

import stuffplotter.shared.Notification.NotificationType;

/**
 * Interface used by the Notification object to enforce a contract between the model and views to
 * simulate data binding.
 */
public interface NotificationModel
{
	/**
	 * Retrieve the ID of the notification.
	 * @pre true;
	 * @post true;
	 * @return the notification ID.
	 */
	public Long getNotificationId();
	
	/**
	 * Method to retrieve the type of the notification.
	 * @pre true;
	 * @post true;
	 * @return the type of the notification.
	 */
	public NotificationType getType();
	
	/**
	 * Retrieve the name of the user who generated the notification.
	 * @pre true;
	 * @post true;
	 * @return the name of the user who generated the notification.
	 */
	public String getFrom();
	
	/**
	 * Retrieve the time at which the notification was generated.
	 * @pre true;
	 * @post true;
	 * @return the time at which the notification was generated.
	 */
	public Date getNotificationTime();
}