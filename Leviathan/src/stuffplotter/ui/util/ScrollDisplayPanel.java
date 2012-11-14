package stuffplotter.ui.util;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel to display content in a scrolled panel with a set number of columns and any
 * number of rows.
 */
public class ScrollDisplayPanel extends ScrollPanel
{
	private int currentRow = 0;
	private int currentColumn = 0;
	private int numberOfColumns = 1;
	
	private FlexTable displayedComponents;
	
	/**
	 * Constructor for the ScrollDisplayPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public ScrollDisplayPanel()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.displayedComponents = new FlexTable();
		this.add(displayedComponents);
	}
	
	/**
	 * Add the given Widget to the display.
	 * @pre true;
	 * @post true;
	 * @param element
	 */
	public void addElement(Widget element)
	{
		this.displayedComponents.setWidget(currentRow, currentColumn, element);
		currentColumn++;
		if(currentColumn == numberOfColumns)
		{
			currentColumn = 0;
			currentRow++;
		}
	}
	
	/**
	 * Clear the display.
	 * @pre true;
	 * @post this.displayedComponents.getRowCount() == 0;
	 */
	public void clearDisplay()
	{
		this.displayedComponents.removeAllRows();
	}
	
	/**
	 * Retrieve the underlying FlexTable for the ScrollDisplayPanel.
	 * @pre true;
	 * @post true;
	 * @return the FlexTable displaying all the content on the screen.
	 */
	public FlexTable getDisplayer()
	{
		return this.displayedComponents;
	}
}
