package stuffplotter.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import stuffplotter.bindingcontracts.NotificationModel;

/**
 * Class to hold the information for a user notification.
 */
public abstract class Notification implements Serializable, NotificationModel
{
	public enum NotificationType
	{
		FRIENDREQUEST, EVENTINVITATION, ACHIEVEMENTGET;
	}

	/**
	 * ID of what generated the notification.
	 */
	@Id private Long notificationId;
	private NotificationType type;
	private String notificationFor;
	private String notificationFrom;
	private boolean newNotification = true;
	private Date notificationTime;
	
	protected String notificationDisplay;
	
	
	public Notification()
	{
		setNotificationTime(new Date());
		this.setNotificationDisplay("");
	}
	
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
	public Notification(NotificationType type, String foruser, String from)
	{
		this.type = type;
		this.notificationFrom = from;
		this.notificationFor = foruser;
		this.setNotificationTime(new Date());
		this.setNotificationDisplay("");
	}

	
	public Long getNotificationId()
	{
		return this.notificationId;
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
	
	@Override
	public String getNotificationDisplay()
	{
		return this.notificationDisplay;
	}
	public void setNotificationDisplay(String notificationDisplay)
	{
		this.notificationDisplay = notificationDisplay;
	}
	
	@Override
	public void setNewNotification(boolean bool)
	{
		this.newNotification = bool;
	}
	public Date getNotificationTime()
	{
		return notificationTime;
	}
	public void setNotificationTime(Date notificationTime)
	{
		this.notificationTime = notificationTime;
	}
	
	@Override
	public boolean getNewNotification(){
		return this.newNotification;
	}
	
	@Override
	public boolean equals(Object o1)
	{
		if(o1 instanceof Notification)
		{
			final Notification other = (Notification) o1;
			if(o1 == this)
				return true;
			return this.getNotificationId()==other.notificationId;
		}
		return false;
		
	}
	
	@Override
	public int hashCode()
	{
	    int result = 0;
	    result = (int)(this.getNotificationId()*7)*13 * 43;
	    return result;
	}
	

	/**
	 * Serial version for the Notification.
	 */
	private static final long serialVersionUID = 5012234724329134162L;
}