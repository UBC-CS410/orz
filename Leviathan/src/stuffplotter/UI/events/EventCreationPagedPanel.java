package stuffplotter.ui.events;

import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Account;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

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
	public EventCreationPagedPanel(Account userAccount)
	{
		super();
		this.initializePages(userAccount);
		this.showWidget(0);
	}
	
	/**
	 * Helper method to initialize pages of the EventCreationPager.
	 * @pre true;
	 * @post true;
	 * @param userAccount - the account of the user to populate the pages with.
	 */
	private void initializePages(Account userAccount)
	{
		// initialize Page 1
		EventInfoPanel page1Panel = new EventInfoPanel();
		
		// initialize Page 2
		TimeSheetPanel timeSheet = new TimeSheetPanel();
		EventDateSelectionPanel page2Panel = new EventDateSelectionPanel(timeSheet);

		// initialize Page 3
		FriendSelectionPanel page3Panel = new FriendSelectionPanel(userAccount);
		
		this.add(page1Panel);
		this.add(page2Panel);
		this.add(page3Panel);
	}
	
	/**
	 * Helper method to retrieve the information for a new event.
	 * @pre eventPageVisitor != null;
	 * @post true;
	 * @param eventPageVisitor - the EventCreationPageVisitor to visit each of the
	 * 							 EventCreationDialogBox pages.
	 */
	public void retrieveEventInfo(EventCreationPageVisitor eventPageVisitor)
	{	
		// for loop to retrieve the information for the event from each page
		for(int i = 0; i < this.getWidgetCount(); i++)
		{
			Widget page = this.getWidget(i);
			if(page instanceof EventSubmittable)
			{
				((EventSubmittable) page).accept(eventPageVisitor);
			}
		}
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
