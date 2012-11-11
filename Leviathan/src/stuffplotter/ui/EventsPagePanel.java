package stuffplotter.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the events page view.
 */
public class EventsPagePanel extends SimplePanel
{
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPagePanel()
	{
		super();
		this.add(new Label("Events"));
	}
}
