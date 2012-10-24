package stuffplotter.UI;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
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
		initializeCalendarChangeHandler(calendar);
		mapCalHolder.add(calendar, new Label("Calendar"));
		this.add(mapCalHolder);
	}
	
	/**
	 * Helper method to initialize the change handler for the calendar.
	 * @param calendar - the calendar to add the change handler to.
	 */
	private void initializeCalendarChangeHandler(DatePicker calendar)
	{
		calendar.addValueChangeHandler(new ValueChangeHandler<Date>()
		{
			@Override
			public void onValueChange(ValueChangeEvent<Date> event)
			{
				Date dayClicked = event.getValue();
				DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy");
				Window.alert(dayFormat.format(dayClicked).toString());
			}			
		});
	}
}
