package stuffplotter.UI;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display a map with search box to find the location for an event.
 */
public class EventLocationMapPanel extends SimplePanel
{
	final private String mapWidth = "250px";
	final private String mapHeight = "250px";
	private EventInfoInputPanel eventInfoInput;
	
	/**
	 * Constructor for EventLocationMapPanel.
	 * @pre timeSheetPanel != null;
	 * @post this.isVisible() == true;
	 * @param eventInfoInputPanel - the EventInfoInputPanel to add location to.
	 */
	public EventLocationMapPanel(EventInfoInputPanel eventInfoInputPanel)
	{
		super();
		this.initializeUI(eventInfoInputPanel);
	}
	
	/**
	 * Helper method to add the panels to the EventLocationMapPanel.
	 * @pre timeSheetPanel != null;
	 * @post true;
	 * @param eventInfoInputPanel - the TimeSheetPanel to add selected dates to.
	 */
	private void initializeUI(EventInfoInputPanel eventInfoInputPanel)
	{
		this.eventInfoInput = eventInfoInputPanel;
		HorizontalPanel infoMapHolder = new HorizontalPanel();
		MapWidget map = new MapWidget(LatLng.newInstance(49, -123), 8);
		map.setSize(mapWidth, mapHeight);
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl3D());
		infoMapHolder.add(eventInfoInputPanel);
		infoMapHolder.add(map);
		this.add(infoMapHolder);
	}
}
