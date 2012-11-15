package stuffplotter.views.events;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the search panel for the location of an event.
 */
public class EventLocationSearchPanel extends VerticalPanel
{
	private MapWidget googleMap;
	private EventInfoInputPanel eventInfo;
	private JsArray<Placemark> locationsFound;
	private Button previousLocation;
	private Button nextLocation;
	private Label numOfResults;
	private int currentLocationIndex;
	private int totalNumLocations;
	
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
		this.initializeUI();
		this.defaultPageResults();
	}
	
	/**
	 * Helper method to generate the UI for the EventLocationSearchPanel.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		// create top section
		HorizontalPanel topSearchElementsHolder = new HorizontalPanel();
		TextBox searchBox = new TextBox();
		Button searchButton = new Button("Search");
		Button clearButton = new Button("Clear");
		this.initializeSearchButtons(searchBox, searchButton, clearButton);
		topSearchElementsHolder.add(searchBox);
		topSearchElementsHolder.add(searchButton);
		topSearchElementsHolder.add(clearButton);
		
		// create bottom section
		HorizontalPanel botSearchElementsHolder = new HorizontalPanel();
		this.numOfResults = new Label("Type in the location of the event.");
		this.previousLocation = new Button("Previous");
		this.nextLocation = new Button("Next");
		botSearchElementsHolder.add(this.numOfResults);
		botSearchElementsHolder.add(this.previousLocation);
		botSearchElementsHolder.add(this.nextLocation);
		this.initializePagingButtons();
		
		// add all the elements to the panel
		this.add(topSearchElementsHolder);
		this.add(botSearchElementsHolder);
	}
		
	/**
	 * Helper method to add clickHandlers to the "Previous" and "Next" buttons
	 * and disable the buttons initially.
	 * @pre previousResult != null && nextResult != null;
	 * @post previous.isEnabled() == false && next.isEnabled() == false;
	 */
	private void initializePagingButtons()
	{
		this.previousLocation.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				previousLocation();
				nextLocation.setEnabled(true);
			}			
		});
		
		this.nextLocation.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				nextLocation();
				previousLocation.setEnabled(true);
			}
		});

		this.disablePagingButtons();
	}

	/**
	 * Helper method to add clickHandler to search button.
	 * @pre searchBox != null && searchButton != null;
	 * @post true;
	 * @param searchBox - the TextBox to get input from the user for a location.
	 * @param searchButton - the Button to press when submitting query.
	 * @param clearButton - the Button to clear search results.
	 */
	private void initializeSearchButtons(TextBox searchBox, Button searchButton, Button clearButton)
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
						numOfResults.setText("No results found.");
						defaultPageResults();
						disablePagingButtons();
					}

					@Override
					public void onSuccess(JsArray<Placemark> locations)
					{
						locationsFound = locations;
						currentLocationIndex = 0;
						totalNumLocations = locations.length();
						disablePagingButtons();
						if(totalNumLocations > 1)
						{
							nextLocation.setEnabled(true);
						}
						// update location in EventInfoInputPanel, display for
						// Google Map, and number of results found
						updateUI(0);
						numOfResults.setText("Results found: " + locations.length());
					}
				};
				
				Geocoder geocoder = new Geocoder();
				geocoder.getLocations(searchInput.getText(), callback);
			}
		});
		
		clearButton.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				searchInput.setText("");
				defaultPageResults();
			}	
		});
	}
	
	/**
	 * Helper method to update the EventLocationSearchPanel UI after a
	 * search returned a successful result.
	 * @pre locationIndex >= 0;
	 * @post true;
	 * @param locationIndex - the index of the location to retrieve from
	 * 						  this.locationsFound
	 */
	private void updateUI(int locationIndex)
	{
		Placemark location = this.locationsFound.get(locationIndex);
		this.eventInfo.setLocationText(location.getAddress());
		this.eventInfo.setCoordinates(location.getPoint());
		Marker marker = new Marker(location.getPoint());
		this.googleMap.addOverlay(marker);
		this.googleMap.panTo(marker.getLatLng());
	}
	
	/**
	 * Helper method to display the previous location result if it exists.
	 * @pre true;
	 * @post true;
	 */
	private void previousLocation()
	{
		if(hasPreviousLocation())
		{
			this.currentLocationIndex--;
			this.updateUI(this.currentLocationIndex);
			if(!hasPreviousLocation())
			{
				this.previousLocation.setEnabled(false);
			}
		}
	}
	
	/**
	 * Helper method to determine if a previous location exists.
	 * @pre true;
	 * @post true;
	 * @return true if a previous location exists, false otherwise.
	 */
	private boolean hasPreviousLocation()
	{
		return this.currentLocationIndex > 0;
	}
	
	/**
	 * Helper method to display the next location result if it exists.
	 * @pre true;
	 * @post true;
	 */
	private void nextLocation()
	{
		if(hasNextLocation())
		{
			this.currentLocationIndex++;
			this.updateUI(this.currentLocationIndex);
			if(!hasNextLocation())
			{
				this.nextLocation.setEnabled(false);
			}
		}
	}
	
	/**
	 * Helper method to determine if a next location exists.
	 * @pre true;
	 * @post true;
	 * @return true if a next location exists, false otherwise.
	 */
	private boolean hasNextLocation()
	{
		return this.currentLocationIndex < this.totalNumLocations - 1;
	}
	
	/**
	 * Helper method to reset the page results for a location search to an
	 * empty search.
	 * @pre true;
	 * @post locationsFound == null && currentLocationIndex == 0 &&
	 * 		 totalNumLocations == 0;
	 */
	private void defaultPageResults()
	{
		locationsFound = null;
		this.googleMap.clearOverlays();
		this.numOfResults.setText("Type in the location of the event.");
		this.eventInfo.setLocationText("");
		this.eventInfo.setCoordinates(null);
		currentLocationIndex = 0;
		totalNumLocations = 0;
		this.disablePagingButtons();
	}
	
	/**
	 * Helper method to disable the paging buttons in the panel.
	 * @pre true;
	 * @post this.previousLocation.isEnabled() == false &&
	 * 		 this.nextLocation.isEnabled() == false;
	 */
	private void disablePagingButtons()
	{
		this.previousLocation.setEnabled(false);
		this.nextLocation.setEnabled(false);
	}
}
