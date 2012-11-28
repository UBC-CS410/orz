package stuffplotter.views.util;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Class for displaying the information for each field of a user.
 * Contains the fields name, fields value.
 */
public class VerticalInfoPanel extends VerticalPanel
{
	public Label fieldValue;
	
	/**
	 * Constructor for the InfoPanel.
	 * @pre fieldName != null && fieldValue != null;
	 * @post this.isVisible() == true;
	 * @param fieldName - the name of the attribute.
	 * @param fieldValue - the value of the attribute.
	 */
	public VerticalInfoPanel(String fieldName, String fieldValue)
	{
		this.add(new Label(fieldName + ": "));
		if(fieldValue != null && !fieldValue.trim().isEmpty())
		{
			this.fieldValue = new Label(fieldValue);
		}
		else
		{
			this.fieldValue = new Label("--");
		}
		
		this.add(this.fieldValue);
	}
	
	/**
	 * Set the value that is displayed in the InfoPanel for the attribute.
	 * @pre value != null;
	 * @post true;
	 * @param value - the value to display in the InfoPanel for the attribute.
	 */
	public void setValue(String value)
	{
		this.fieldValue.setText(value);
	}
}
