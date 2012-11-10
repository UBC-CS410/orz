package stuffplotter.UI;

import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the general information for an event during its creation.
 */
public class EventInfoPanel extends SimplePanel implements EventSubmittable
{
	private EventInfoInputPanel eventInputPanel;
	
	/**
	 * Constructor for the EventInfoPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventInfoPanel()
	{
		super();
		this.initializeUI();
	}

	/**
	 * Method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		HorizontalPanel infoHolder = new HorizontalPanel();
		eventInputPanel = new EventInfoInputPanel();
		EventLocationMapPanel mapPanel = new EventLocationMapPanel(eventInputPanel);
		infoHolder.add(eventInputPanel);
		infoHolder.add(mapPanel);
		this.add(infoHolder);
	}
	
	/**
	 * Method to retrieve the EventInfoInputPanel containing the general information for the
	 * event.
	 * @pre true;
	 * @post true;
	 * @return the EventInputPanel for the EventInfoPanel.
	 */
	public EventInfoInputPanel getEventInfoInputPanel()
	{
		return this.eventInputPanel;
	}
	
	/**
	 * Method to accept an EventCreationPageVisitor and have it perform certain tasks.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	@Override
	public void accept(EventCreationPageVisitor eventVisitor)
	{
		eventVisitor.visit(this);
	}
}
