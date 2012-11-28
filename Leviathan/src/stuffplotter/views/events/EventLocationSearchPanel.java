package stuffplotter.views.events;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	private TextBox searchBox;
	private Button searchButton;
	private Button clearButton;
	private Button previousLocation;
	private Button nextLocation;
	private Label numOfResults;
	private int currentLocationIndex;
	private int totalNumLocations;
	
	/**
	 * Constructor for EventLocationSearchPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventLocationSearchPanel()
	{
		super();
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
		this.searchBox = new TextBox();
		this.searchButton = new Button("Search");
		this.clearButton = new Button("Clear");
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
	 * Helper method to reset the page results for a location search to an
	 * empty search.
	 * @pre true;
	 * @post locationsFound == null && currentLocationIndex == 0 &&
	 * 		 totalNumLocations == 0;
	 */
	public void defaultPageResults()
	{
		this.numOfResults.setText("Type in the location of the event.");
		currentLocationIndex = 0;
		totalNumLocations = 0;
		this.disablePagingButtons();
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

	/**
	 * Retrieve search box input with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the search box input with white space trimmed.
	 */
	public String getUserInput()
	{
		return this.searchBox.getText().trim();
	}

	/**
	 * Retrieve the search button.
	 * @pre true;
	 * @post true;
	 * @return the search button.
	 */
	public Button getSearchButton()
	{
		return this.searchButton;
	}

	/**
	 * Retrieve the clear button.
	 * @pre true;
	 * @post true;
	 * @return the clear button.
	 */
	public Button getClearButton()
	{
		return this.clearButton;
	}

	/**
	 * Retrieve the previous location button.
	 * @pre true;
	 * @post true;
	 * @return the previous location button.
	 */
	public Button getPreviousLocation()
	{
		return this.previousLocation;
	}

	/**
	 * Retrieve the next location button.
	 * @pre true;
	 * @post true;
	 * @return the next location button.
	 */
	public Button getNextLocation()
	{
		return this.nextLocation;
	}

	/**
	 * Set the number of results found for the display.
	 * @pre numOfResults != null;
	 * @post true;
	 * @param numOfResults - the string to display with the number of results found.
	 */
	public void setNumOfResults(String numOfResults)
	{
		this.numOfResults.setText(numOfResults);
	}

	/**
	 * Set the total number of locations found and iterate through.
	 * @pre totaNumLocations >= 0;
	 * @post true;
	 * @param totalNumLocations - the total number of locations found.
	 */
	public void setTotalNumLocations(int totalNumLocations)
	{
		this.totalNumLocations = totalNumLocations;
	}
}
