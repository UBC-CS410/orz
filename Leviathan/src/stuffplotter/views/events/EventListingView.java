package stuffplotter.views.events;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

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
		
		Label statusLabel = new Label();
		if (event.getInvitees().contains(user.getUserEmail()))
		{
			statusLabel.setText("Invited");
			statusLabel.setStyleName("eventInvitedLabel");
		}
		else if(event.getDate() == null)
		{
			statusLabel.setText("Unscheduled");
			statusLabel.setStyleName("eventUnscheduledLabel");
		} 
		else
		{
			statusLabel.setText(event.getDate().toString());
			statusLabel.setStyleName("eventScheduledLabel");
		}

		this.setStyleName("eventListing");
		
		this.add(this.eventLink);
		this.add(statusLabel);
	}
	
	public HasClickHandlers getLink()
	{
		return this.eventLink;
	}
}