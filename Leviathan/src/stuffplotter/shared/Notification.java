package stuffplotter.shared;

/**
 * Class to hold the information for a user notification.
 */
public abstract class Notification
{
	public enum NotificationType
	{
		FRIENDREQUEST, EVENTINVITE;
	}
	
	/**
	 * ID of what generated the notification.
	 */
	private Long id;
	private NotificationType type;
	
	/**
	 * Constructor for a Notification.
	 * @pre true;
	 * @post true;
	 */
	public Notification(Long id, NotificationType type)
	{
		this.id = id;
		this.type = type;
	}
	
	/**
	 * Method to retrieve the ID that generated the notification.
	 * @pre true;
	 * @post true;
	 * @return the ID that generated the notification.
	 */
	public Long getID()
	{
		return this.id;
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
	 * Method to generate the message for the notification.
	 * @pre true;
	 * @post true;
	 * @return the message for the notification.
	 */
	public abstract String generateMessage();
}
