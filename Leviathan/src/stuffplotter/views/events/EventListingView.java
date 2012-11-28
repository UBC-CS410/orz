package stuffplotter.views.events;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Status;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel to display the name and time of an event as a row in a ScrollDisplayPanel.
 */
public class EventListingView extends VerticalPanel
{
	
	private final Anchor eventLink;
	
	/**
	 * Constructor for EventShortView
	 * @pre	true
	 * @post this.isVisible() == true
	 * 
	 */
	public EventListingView(Account user, Event event)
	{
		this.eventLink = new Anchor(event.getName());
		this.eventLink.setStyleName("eventListingLabel");
		
		Label stateLabel = this.getStateLabel(user.getUserEmail(), event.getInvitees(), event.getStatus());
		Label timeLabel = new Label("");

		this.setStyleName("eventListing");
		
		this.add(this.eventLink);
		this.add(stateLabel);
	}
	
	public HasClickHandlers getLink()
	{
		return this.eventLink;
	}
	
	private Label getStateLabel(String username, List<String> invitees, Status status)
	{
		Label label = new Label();
		if (invitees.contains(username))
		{
			label.setText("Invited");
			label.setStyleName("eventInvitedLabel");
		}
		else
		{
			switch (status)
			{
				case PROPOSED:
					label.setText("Unscheduled");
					label.setStyleName("eventUnscheduledLabel");
					break;
				case SCHEDULED:
					label.setText("Scheduled");
					label.setStyleName("eventScheduledLabel");
					break;
				case FINISHED:
					label.setText("Finished");
					label.setStyleName("eventScheduledLabel");
					break;
			}
		}
		
		return label;
	}
}