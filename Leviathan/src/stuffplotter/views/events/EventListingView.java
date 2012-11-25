package stuffplotter.views.events;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel to display the name and time of an event as a row in a ScrollDisplayPanel.
 */
public class EventListingView extends VerticalPanel
{
	
	private final Anchor link;
	
	/**
	 * Constructor for EventShortView
	 * @pre	true
	 * @post this.isVisible() == true
	 * 
	 */
	public EventListingView(String name, String time, String location)
	{
		this.link = new Anchor(name);
		
		this.setStyleName("eventListing");
		this.link.setStyleName("eventListingAnchor");
		
		this.add(link);
		this.add(new Label(time));
		this.add(new Label(location));
	}
	
	public HasClickHandlers getLink()
	{
		return this.link;
	}
}