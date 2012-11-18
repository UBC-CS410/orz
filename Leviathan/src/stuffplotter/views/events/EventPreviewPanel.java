package stuffplotter.views.events;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventPreviewPanel extends VerticalPanel
{
	
	
	/**
	 * Constructor for EventsPreviewPanel
	 * @pre	true
	 * @post this.isVisible() == true
	 * 
	 */
	public EventPreviewPanel(String name, String time, String desc)
	{
		this.add(new Label("Event: " + name));
		this.add(new Label("Time: " + time));
		this.add(new Label(desc));
	}
		
}