package stuffplotter.UI;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Class to display the search panel for the location of an event.
 */
public class EventLocationSearchPanel extends SimplePanel
{
	private MapWidget googleMap;
	private EventInfoInputPanel eventInfo;
	private JsArray<Placemark> locationsFound;
	
	/**
	 * Constructor for EventLocationSearchPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 * @param map - the GoogleMap to display the found locations on.
	 */
	public EventLocationSearchPanel(MapWidget map, EventInfoInputPanel eventInfo)
	{
		super();
		this.googleMap = map;
		this.eventInfo = eventInfo;
		HorizontalPanel searchElementsHolder = new HorizontalPanel();
		TextBox searchBox = new TextBox();
		Button searchButton = new Button("Search");
		this.initializeSearchButton(searchBox, searchButton);
		searchElementsHolder.add(searchBox);
		searchElementsHolder.add(searchButton);
		this.add(searchElementsHolder);
	}
	
	/**
	 * Helper method to add clickHandler to search button.
	 * @pre searchBox != null && searchButton != null;
	 * @post true;
	 * @param searchBox - the TextBox to get input from the user for a location.
	 * @param searchButton - the Button to press when submitting query.
	 */
	private void initializeSearchButton(TextBox searchBox, Button searchButton)
	{
		final TextBox searchInput = searchBox;
		searchButton.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				LocationCallback callback = new LocationCallback()
				{

					@Override
					public void onFailure(int statusCode)
					{
						// TODO Auto-generated method stub
						Window.alert("FAILED!!!");
					}

					@Override
					public void onSuccess(JsArray<Placemark> locations)
					{
						locationsFound = locations;
						eventInfo.setLocationText(locations.get(0).getStreet());
						Marker location = new Marker(locations.get(0).getPoint());
						location.addMarkerClickHandler(new MarkerClickHandler()
						{
							@Override
							public void onClick(MarkerClickEvent event)
							{
								Window.alert(this.toString());
							}	
						});
						googleMap.addOverlay(location);
					}

				};
				
				Geocoder geocoder = new Geocoder();
				geocoder.getLocations(searchInput.getText(), callback);
			}
		});
	}
}
