package stuffplotter.views.events;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display a map with search box to find the location for an event.
 */
public class EventLocationMapPanel extends SimplePanel
{
	private static final LatLng VANCOUVER = LatLng.newInstance(49.264448, -123.185844);
	private static final int ZOOM_LEVEL = 12;
	private static final String MAP_WIDTH = "500px";
	private static final String MAP_HEIGHT = "300px";
	private MapWidget map;
	
	/**
	 * Constructor for EventLocationMapPanel.
	 * @pre timeSheetPanel != null;
	 * @post this.isVisible() == true;
	 */
	public EventLocationMapPanel()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to add the panels to the EventLocationMapPanel.
	 * @pre timeSheetPanel != null;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.map = new MapWidget(VANCOUVER, ZOOM_LEVEL);
		this.map.setSize(MAP_WIDTH, MAP_HEIGHT);
		this.map.setScrollWheelZoomEnabled(true);
		this.map.addControl(new LargeMapControl3D());
		this.add(this.map);
	}
	
	/**
	 * Clear the map and set it to the default view.
	 * @pre true;
	 * @post true;
	 */
	public void clearResults()
	{
		this.map.clearOverlays();
		this.map.setZoomLevel(ZOOM_LEVEL);
		this.map.panTo(VANCOUVER);
	}
	
	/**
	 * View the location on the map for the given LatLng.
	 * @pre location != null;
	 * @post true;
	 * @param location - the location to view.
	 */
	public void viewLocation(LatLng location)
	{
		this.map.clearOverlays();
		this.map.setZoomLevel(ZOOM_LEVEL);
		this.map.panTo(location);
		this.map.addOverlay(new Marker(location));
	}
}
