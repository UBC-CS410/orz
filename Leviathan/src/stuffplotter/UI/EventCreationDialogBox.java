package stuffplotter.UI;

import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the window for creating events.
 */
public class EventCreationDialogBox extends DialogBox
{
	/**
	 * Constructor for the EventCreationDialogBox class.
	 */
	public EventCreationDialogBox()
	{
		HorizontalPanel horPanel = new HorizontalPanel();
		// temporary event info panel
		SimplePanel tempPanel = new SimplePanel();
		tempPanel.add(new Label("Event Info Goes Here"));
		horPanel.add(tempPanel);
		
		VerticalPanel vertPanel = new VerticalPanel();
		vertPanel.add(new MapCalendarView());
		vertPanel.add(new AvailabilitySubmitter());
		horPanel.add(vertPanel);
		
		this.add(horPanel);
	}
}
