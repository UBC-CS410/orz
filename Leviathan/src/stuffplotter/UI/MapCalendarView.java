package stuffplotter.UI;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Class to swap between a map and calendar view.
 */
public class MapCalendarView extends SimplePanel
{
	/**
	 * Constructor for MapCalednarView.
	 */
	public MapCalendarView()
	{
		super();
		TabPanel mapCalHolder = new TabPanel();
		MapWidget map = new MapWidget(LatLng.newInstance(49, -123), 8);
		map.setSize("250px", "250px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl3D());
		mapCalHolder.add(map, new Label("Map"));
		DatePicker calendar = new DatePicker();
		mapCalHolder.add(calendar, new Label("Calendar"));
		this.add(mapCalHolder);
	}
}
