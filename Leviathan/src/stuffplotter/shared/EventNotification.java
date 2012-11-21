package stuffplotter.shared;

import stuffplotter.shared.Notification.NotificationType;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Subclass of Notification that represents a Event Notification.
 */
public class EventNotification extends Notification
{

	public enum EventNotificationType
	{
		EVENTINVITE, EVENTUPDATE
	}
	
	private EventNotificationType eventType;
	/**
	 * Constructor for a EventNotification.
	 * @pre id >= 0 && type != null && fromUser != null && forUser != null;
	 * @post this.id == id && this.type.equals(type) &&
	 * this.notificationFrom.equals(fromUser) && this.notificationFor.equals(forUser);
	 * @param id - the ID of the event.
	 * @param type - the type of the notification, should be EVENTINVITATION.
	 * @param fromUser - the user that created the event.
	 * @param forUser - the guest invited to the event.
	 */
	public EventNotification()
	{
		
	}
	
	public EventNotification(EventNotificationType type, String foruser, String from)
	{
		
		super(NotificationType.EVENTINVITATION, foruser, from);
		this.eventType = type;
	}


}