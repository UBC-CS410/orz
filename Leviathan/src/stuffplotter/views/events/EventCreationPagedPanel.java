package stuffplotter.views.events;

import stuffplotter.client.EventCreationPageVisitor;
import stuffplotter.presenters.EventCreationPagedPresenter.EventCreationPagedView;
import stuffplotter.presenters.EventDateSelectionPresenter.EventDateSelectionView;
import stuffplotter.presenters.EventFriendSelectionPresenter.EventFriendSelectionView;
import stuffplotter.presenters.EventInfoPresenter.EventInfoView;

import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for paging in the EventCreationDialogBox class.
 */
public class EventCreationPagedPanel extends DeckPanel implements EventCreationPagedView
{
	private int currentPage = 0;
	private EventInfoView page1Panel;
	private EventDateSelectionView page2Panel;
	private EventFriendSelectionView page3Panel;
	
	/**
	 * Constructor for the EventCreationPager.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationPagedPanel()
	{
		super();
		this.initializePages();
	}
	
	/**
	 * Helper method to initialize pages of the EventCreationPager.
	 * @pre true;
	 * @post true;
	 * @param userAccount - the account of the user to populate the pages with.
	 */
	private void initializePages()
	{
		//IMPORTANT: Only panels that implement EventSubmittable can be added to this panel.
		
		// initialize Page 1
		page1Panel = new EventInfoPanel();
		
		// initialize Page 2
		page2Panel = new EventDateSelectionPanel();

		// initialize Page 3
		page3Panel = new FriendSelectionPanel();
	}
	
	/**
	 * Helper method to pass the visitor to all panels in the paged view.
	 * @pre eventPageVisitor != null;
	 * @post true;
	 * @param eventPageVisitor - the EventCreationPageVisitor to visit each of the
	 * 							 EventCreationDialogBox pages.
	 */
	public void accept(EventCreationPageVisitor eventPageVisitor)
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
	 * Retrieve the currently displayed EventSubmittable panel.
	 * @pre true;
	 * @post true;
	 * @return the currently displayed EventSubmittable panel.
	 */
	public EventSubmittable getCurrentPage()
	{
		return (EventSubmittable) this.getWidget(this.getVisibleWidget());
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

	@Override
	public EventInfoView getEventInfoView()
	{
		return this.page1Panel;
	}

	@Override
	public EventDateSelectionView getDateSelectionView()
	{
		return this.page2Panel;
	}

	@Override
	public EventFriendSelectionView getFriendSelectionView()
	{
		return this.page3Panel;
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
