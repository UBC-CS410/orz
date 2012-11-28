package stuffplotter.views.events;

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
	private static final String NO_RESULTS = "No results found.";
	private static final String ONE_RESULT = " result was found.";
	private static final String MANY_RESULTS = " results were found.";
	private TextBox searchBox;
	private Button searchButton;
	private Button clearButton;
	private Button previousLocation;
	private Button nextLocation;
	private Label numOfResults;
	
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
		this.searchBox.setText("");
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
	public Button getPreviousBtn()
	{
		return this.previousLocation;
	}

	/**
	 * Retrieve the next location button.
	 * @pre true;
	 * @post true;
	 * @return the next location button.
	 */
	public Button getNextBtn()
	{
		return this.nextLocation;
	}

	/**
	 * Enable the back button.
	 * @pre true;
	 * @post this.previousLocation.isEnabled();
	 */
	public void enableBackBtn()
	{
		this.previousLocation.setEnabled(true);
	}
	
	/**
	 * Disable the back button.
	 * @pre true;
	 * @post !this.previousLocation.isEnabled();
	 */
	public void disableBackBtn()
	{
		this.previousLocation.setEnabled(false);
	}
	
	/**
	 * Enable the next button.
	 * @pre true;
	 * @post this.nextLocation.isEnabled();
	 */
	public void enableNextBtn()
	{
		this.nextLocation.setEnabled(true);
	}
	
	/**
	 * Disable the next button.
	 * @pre true;
	 * @post !this.nextLocation.isEnabled();
	 */
	public void disableNextBtn()
	{
		this.nextLocation.setEnabled(false);
	}
	
	/**
	 * Set the number of results found for the display.
	 * @pre numOfResults != null;
	 * @post true;
	 * @param numOfResults - the string to display with the number of results found.
	 */
	public void setNumOfResults(int numOfResults)
	{
		if(numOfResults == 1)
		{
			this.numOfResults.setText(String.valueOf(numOfResults) + ONE_RESULT);
		}
		else
		{
			this.numOfResults.setText(String.valueOf(numOfResults + MANY_RESULTS));
		}
	}
	
	/**
	 * Set the fail message to appear.
	 * @pre true;
	 * @post true;
	 */
	public void setFailResult()
	{
		this.numOfResults.setText(NO_RESULTS);
	}
}
