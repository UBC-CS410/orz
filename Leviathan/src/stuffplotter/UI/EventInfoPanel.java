package stuffplotter.UI;

import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Event;

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
		HorizontalPanel infoHolder = new HorizontalPanel();
		eventInputPanel = new EventInfoInputPanel();
		EventLocationMapPanel mapPanel = new EventLocationMapPanel(eventInputPanel);
		infoHolder.add(eventInputPanel);
		infoHolder.add(mapPanel);
		this.add(infoHolder);
	}

	/**
	 * Method to retrieve the submission information from the EventInfoPanel.
	 * @pre event != null;
	 * @post true;
	 * @param event - the Event to have its fields populated with before sending to
	 * 				  the backend.
	 */
	@Override
	public void retrieveSubmission(Event event)
	{
		event.setName(eventInputPanel.getName());
		event.setLocation(eventInputPanel.getLocation());
		event.setCoordinates(eventInputPanel.getMapCoordinatesAsArray());
		event.setCost(eventInputPanel.getCost());
		event.setDuration(eventInputPanel.getDuration());
		event.setDescription(eventInputPanel.getDescription());
	}
}
