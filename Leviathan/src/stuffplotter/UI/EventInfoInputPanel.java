package stuffplotter.UI;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class for displaying the input view for creating a new event.
 */
public class EventInfoInputPanel extends VerticalPanel
{
	private TextBox location;
	
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
		this.add(new TextBox());
		this.add(new Label("Location:"));
		this.location = new TextBox();
		this.add(location);
		this.add(new Label("Cost:"));
		this.add(new TextBox());
		this.add(new Label("Duration:"));
		this.add(new TextBox());
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
}
