package stuffplotter.UI;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class for displaying the input view for creating a new event.
 */
public class EventInfoInputPanel extends VerticalPanel
{
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
		this.add(new TextBox());
		this.add(new Label("Cost:"));
		this.add(new TextBox());
		this.add(new Label("Duration:"));
		this.add(new TextBox());
	}
}
