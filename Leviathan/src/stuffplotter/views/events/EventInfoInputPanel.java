package stuffplotter.views.events;

import stuffplotter.client.MapCoordinate;
import stuffplotter.shared.Event.Frame;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class for displaying the input view for creating a new event.
 */
public class EventInfoInputPanel extends VerticalPanel
{
	private Label creator;
	private TextBox location;
	private MapCoordinate mapCoordinates;
	private TextBox name;
	private TextBox cost;
	private RadioButton singleDay;
	private RadioButton multiDay;
	private ListBox duration;
	private Label timeSuffix;
	private TextArea description;
	private Label errorMessage;
	
	/**
	 * Constructor for the EventInfoInput class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventInfoInputPanel()
	{
		super();
		this.mapCoordinates = null;
		this.addFields();
	}
	
	/**
	 * Helper method to add all the UI components to the event info window of the
	 * creation window.
	 * @pre true;
	 * @post true;
	 */
	private void addFields()
	{
		this.add(new Label("Event Information"));
		
		this.add(new Label("Created by:"));
		this.creator = new Label();
		this.add(this.creator);
		
		this.add(new Label("Event Name*:"));
		this.name = new TextBox();
		this.add(this.name);
		
		this.add(new Label("Location:"));
		this.location = new TextBox();
		this.location.setText("use the map to the right to help set the location");
		this.add(location);
		
		this.add(new Label("Cost:"));
		this.cost = new TextBox();
		this.add(cost);
		
		this.add(new Label("Event Span:"));
		this.singleDay = new RadioButton("eventframe", "Single-Day");
		this.singleDay.setValue(true);
		this.multiDay = new RadioButton("eventframe", "Multi-Day");
		HorizontalPanel radioBtnHolder = new HorizontalPanel();
		this.setupSwitcher();
		radioBtnHolder.add(this.singleDay);
		radioBtnHolder.add(this.multiDay);
		this.add(radioBtnHolder);
		
		HorizontalPanel listBoxHolder = new HorizontalPanel();
		this.add(new Label("Duration:"));
		this.duration = new ListBox();
		this.timeSuffix = new Label();
		this.setHoursDropdown();
		listBoxHolder.add(duration);
		listBoxHolder.add(timeSuffix);
		this.add(listBoxHolder);
		
		this.add(new Label("Description"));
		this.description = new TextArea();
		this.add(this.description);
		
		this.errorMessage = new Label();
		this.add(errorMessage);
	}
	
	/**
	 * Clear the location information from the event info panel.
	 * @pre true;
	 * @post true;
	 */
	public void clearResults()
	{
		this.setLocationText("");
		this.setCoordinates(null);
	}
	
	/**
	 * Helper method to setup switching of the display when the frame is selected.
	 * @pre true;
	 * @post true;
	 */
	private void setupSwitcher()
	{
		this.singleDay.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				setHoursDropdown();
			}
		});
		
		this.multiDay.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				setDaysDropdown();
			}
		});
	}
	
	/**
	 * Helper method to set the drop down list to the hours values.
	 * @pre true;
	 * @post true;
	 */
	private void setHoursDropdown()
	{
		this.duration.clear();
		
		// for loop to generate values from 1 - 24
		for(int i = 0; i < 24; i++)
		{
			int value = i + 1;
			this.duration.addItem(String.valueOf(value));
		}
		
		this.timeSuffix.setText("Hour(s)");
	}

	/**
	 * Helper method to set the drop down list to the days values.
	 * @pre true;
	 * @post true;
	 */
	private void setDaysDropdown()
	{
		this.duration.clear();
		
		// for loop to generate values from 1 - 31
		for(int i = 0; i < 31; i++)
		{
			int value = i + 1;
			this.duration.addItem(String.valueOf(value));
		}
		
		this.timeSuffix.setText("Day(s)");
	}
	
	/**
	 * Display the error message on the EventInfoInputPanel.
	 * @pre true;
	 * @post true;
	 */
	public void displayErrorMessage()
	{
		this.errorMessage.setText("The name of the event is required.");
	}
	
	/**
	 * Remove the error message on the EventInfoInputPanel.
	 * @pre true;
	 * @post true;
	 */
	public void clearErrorMessage()
	{
		this.errorMessage.setText("");
	}
	
	/**
	 * Set the creator name for the event.
	 * @pre creatorName != null;
	 * @post true;
	 * @param creatorName - the name (full name) of the creator.
	 */
	public void setCreator(String creatorName)
	{
		this.creator.setText(creatorName);
	}
	
	/**
	 * Retrieve the creator name for the event.
	 * @pre true;
	 * @post true;
	 */
	public String getCreator()
	{
		return this.creator.getText().trim();
	}
	
	/**
	 * Method to set the text in the input box for the location value.
	 * @pre text != null;
	 * @post this.location.getText().equals(text);
	 * @param text
	 */
	public void setLocationText(String text)
	{
		this.location.setText(text);
	}
	
	/**
	 * Method to store the coordinates for the event.
	 * @pre true;
	 * @post true;
	 * @param coordinates - the LatLng of the event.
	 */
	public void setCoordinates(LatLng coordinates)
	{
		if(coordinates != null)
		{
			this.mapCoordinates = new MapCoordinate(coordinates);
		}
		else
		{
			this.mapCoordinates = null;
		}
	}
	
	/**
	 * Method to retrieve the address of the event with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the address of the event.
	 */
	public String getLocation()
	{
		return this.location.getText().trim();
	}

	/**
	 * Method to retrieve the coordinates of the event as a 2 element Double[] where the
	 * first entry is the latitude and the second entry is the longitude. 
	 * @pre true;
	 * @post true;
	 * @return the coordinates of the event in a 2 element Double[] or null if none set.
	 */
	public Double[] getMapCoordinatesAsArray()
	{
		if(this.mapCoordinates != null)
		{
			return this.mapCoordinates.getCoordinateAsArray();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Method to retrieve the name of the event with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the name of the event.
	 */
	public String getName()
	{
		return this.name.getText().trim();
	}

	/**
	 * Method to retrieve the cost of the event with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the cost of the event.
	 */
	public String getCost()
	{
		return this.cost.getText().trim();
	}

	/**
	 * Retrieve the time frame for the event.
	 * @pre true;
	 * @post true;
	 * @return the time from for the event.
	 */
	public Frame getFrame()
	{
		if(this.singleDay.getValue())
		{
			return Frame.HOURS;
		}
		else
		{
			return Frame.DAYS;
		}
	}
	
	/**
	 * Method to retrieve the duration of the event with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the duration of the event.
	 */
	public String getDuration()
	{
		return this.duration.getValue(this.duration.getSelectedIndex());
	}

	/**
	 * Method to retrieve the description of the event with white space trimmed.
	 * @pre true;
	 * @post true;
	 * @return the description of the event.
	 */
	public String getDescription()
	{
		return this.description.getText().trim();
	}
}
