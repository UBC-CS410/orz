package stuffplotter.ui.events;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display a map with search box to find the location for an event.
 */
public class EventLocationMapPanel extends SimplePanel
{
	final private LatLng vancouver = LatLng.newInstance(49.264448, -123.185844);
	final private int zoomLevel = 12;
	final private String mapWidth = "500px";
	final private String mapHeight = "300px";
	
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
		HorizontalPanel infoMapHolder = new HorizontalPanel();
		VerticalPanel mapSearchHolder = new VerticalPanel();
		MapWidget map = new MapWidget(vancouver, zoomLevel);
		map.setSize(mapWidth, mapHeight);
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl3D());
		mapSearchHolder.add(map);
		mapSearchHolder.add(new EventLocationSearchPanel(map, eventInfoInputPanel));
		infoMapHolder.add(eventInfoInputPanel);
		infoMapHolder.add(mapSearchHolder);
		this.add(infoMapHolder);
	}
}
