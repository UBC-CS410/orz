package stuffplotter.UI;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class for displaying the input view for creating a new event.
 */
public class EventInfoInputPanel extends VerticalPanel
{
	private TextBox location;
	private LatLng mapCoordinates;
	private TextBox name;
	private TextBox cost;
	private TextBox duration;
	private TextBox description;
	
	/**
	 * Constructor for the EventInfoInput class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventInfoInputPanel()
	{
		super();
		addFields();
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
		
		this.add(new Label("Event Name:"));
		this.name = new TextBox();
		this.add(this.name);
		
		this.add(new Label("Location:"));
		this.location = new TextBox();
		this.add(location);
		
		this.add(new Label("Cost:"));
		this.cost = new TextBox();
		this.add(cost);
		
		this.add(new Label("Duration:"));
		this.duration = new TextBox();
		this.add(this.duration);
		
		this.add(new Label("Description"));
		this.description = new TextBox();
		this.add(this.description);
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
	 * @pre coordinates != null;
	 * @post true;
	 * @param coordinates - the LatLng of the event.
	 */
	public void setCoordinates(LatLng coordinates)
	{
		this.mapCoordinates = coordinates;
	}
	
	/**
	 * Method to retrieve the address of the event.
	 * @pre true;
	 * @post true;
	 * @return the address of the event.
	 */
	public String getLocation()
	{
		return location.getText().trim();
	}

	/**
	 * Method to retrieve the coordinates of the event.
	 * @pre true;
	 * @post true;
	 * @return the coordinates of the event.
	 */
	public LatLng getMapCoordinates()
	{
		return mapCoordinates;
	}

	/**
	 * Method to retrieve the name of the event.
	 * @pre true;
	 * @post true;
	 * @return the name of the event.
	 */
	public String getName()
	{
		return name.getText().trim();
	}

	/**
	 * Method to retrieve the cost of the event.
	 * @pre true;
	 * @post true;
	 * @return the cost of the event.
	 */
	public String getCost()
	{
		return cost.getText().trim();
	}

	/**
	 * Method to retrieve the duration of the event.
	 * @pre true;
	 * @post true;
	 * @return the duration of the event.
	 */
	public String getDuration()
	{
		return duration.getText().trim();
	}

	/**
	 * Method to retrieve the description of the event.
	 * @pre true;
	 * @post true;
	 * @return the description of the event.
	 */
	public String getDescription()
	{
		return description.getText().trim();
	}
}
