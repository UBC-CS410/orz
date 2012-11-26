package stuffplotter.views.events;

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
	public EventListingView(Event event)
	{
		this.eventLink = new Anchor(event.getName());
		this.eventLink.setStyleName("eventListingLabel");
		
		Label scheduleLabel = new Label();
		if(event.getDate() == null)
		{
			scheduleLabel.setText("Unscheduled");
			scheduleLabel.setStyleName("eventUnscheduledLabel");
		}
		else
		{
			scheduleLabel.setText(event.getDate().toString());
			scheduleLabel.setStyleName("eventScheduledLabel");
		}

		this.setStyleName("eventListing");
		
		this.add(this.eventLink);
		this.add(scheduleLabel);
	}
	
	public HasClickHandlers getLink()
	{
		return this.eventLink;
	}
}