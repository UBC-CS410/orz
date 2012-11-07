package stuffplotter.shared;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class FriendNotification extends Notification
{
	/**
	 * Constructor for a FriendNotification.
	 * @pre id >= 0 && type != null && fromUser != null && forUser != null;
	 * @post this.id == id && this.type.equals(type) &&
	 * 		 this.notificationFrom.equals(fromUser) && this.notificationFor.equals(forUser);
	 * @param id - the ID of the user that sent the friend request.
	 * @param type - the type of the notification, should be FRIENDREQUEST.
	 * @param fromUser - the user that created the event.
	 * @param forUser - the guest invited to the event.
	 */
	public FriendNotification(Long id, NotificationType type, String fromUser, String forUser)
	{
		super(id, type, fromUser, forUser);
	}

	@Override
	public SimplePanel generateMessage()
	{
		SimplePanel notificationPanel = new SimplePanel();
		notificationPanel.add(new Label("Friend request by " + this.getFrom()));
		return notificationPanel;
	}

}
