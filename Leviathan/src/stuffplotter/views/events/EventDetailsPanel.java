package stuffplotter.views.events;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventDetailsPanel extends VerticalPanel
{
	
	private final Button voteButton;
	
	/**
	 * Constructor for EventPreviewPanel.
	 * @pre true;
	 * @post true;
	 */
	public EventDetailsPanel() 
	{
		this.voteButton = new Button("Choose availabilities");
		
	}
	
}
