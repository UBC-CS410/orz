package stuffplotter.UI;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Class for paging in the EventCreationDialogBox class.
 */
public class EventCreationPagedPanel extends DeckPanel
{
	private int currentPage = 0;
	
	/**
	 * Constructor for the EventCreationPager.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationPagedPanel()
	{
		super();
		this.initializePages();
		this.showWidget(0);
	}
	
	/**
	 * Helper method to initialize pages of the EventCreationPager.
	 * @pre true;
	 * @post true;
	 */
	private void initializePages()
	{
		// initialize Page 1
		HorizontalPanel page1Panel = new HorizontalPanel();
		EventInfoInputPanel eventInputPanel = new EventInfoInputPanel();
		EventLocationMapPanel mapPanel = new EventLocationMapPanel(eventInputPanel);
		page1Panel.add(eventInputPanel);
		page1Panel.add(mapPanel);
		
		// initialize Page 2
		TimeSheetPanel timeSheet = new TimeSheetPanel();
		EventDateSelectionPanel page2Panel = new EventDateSelectionPanel(timeSheet);
		this.add(page1Panel);
		this.add(page2Panel);
	}
	
	/**
	 * Method to switch view to next page.
	 * @pre this.currentPage < this.getChildren().size();
	 * @post @pre.this.currentPage++;
	 */
	public void nextPage()
	{
		if(this.hasNextPage())
		{
			this.currentPage++;
			this.showWidget(currentPage);
		}
	}
	
	/**
	 * Method to determine if the current display has a next page.
	 * @pre true;
	 * @post true;
	 * @return true if the current display has a next page, otherwise false.
	 */
	public boolean hasNextPage()
	{
		return currentPage < this.getChildren().size() - 1;
	}
	
	/**
	 * Method to switch view to next page.
	 * @pre this.currentPage > 0;
	 * @post @pre.this.currentPage--;
	 */
	public void previousPage()
	{
		if(hasPreviousPage())
		{
			this.currentPage--;
			this.showWidget(currentPage);
		}
	}
	
	/**
	 * Method to determine if the current display has a previous page.
	 * @pre true;
	 * @post true;
	 * @return true if the current display has a previous page, otherwise false.
	 */
	public boolean hasPreviousPage()
	{
		return currentPage > 0;
	}
}
