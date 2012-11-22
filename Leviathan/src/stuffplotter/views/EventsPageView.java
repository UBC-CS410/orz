package stuffplotter.views;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsPageViewer;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventListView;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the events page view.
 */
public class EventsPageView extends HorizontalPanel implements EventsPageViewer
{
	private final Button createButton;
	private final Button listCurrentButton;
	private final Button listPastButton;
	private ScrollDisplayPanel listPanel;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
		
		createButton = new Button("Create Event");
		listCurrentButton = new Button("View Current Events");
		listPastButton = new Button("View Past Events");

		listPanel = new ScrollDisplayPanel();
		//listPanel.getDisplayer().getElement().getStyle().setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * Helper method to initialize this view
	 * @pre true;
	 * @post this.createButton.isVisible() == true && this.currentButton.isVisible() && this.pastButton.isVisible();
	 * @post this.listPanel.isVisible() == true;
	 */
	private void initializeUI()
	{
		this.clear();
		VerticalPanel actionBar = new VerticalPanel();
		actionBar.add(createButton);
		actionBar.add(listCurrentButton);
		actionBar.add(listPastButton);
		
		this.add(actionBar);
		this.add(listPanel);
	}

	/**
	 * Gets the create events button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers 
	 */
	@Override
	public HasClickHandlers getCreateButton()
	{
		return this.createButton;
	}

	/**
	 * Gets the list current events button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getListCurrentButton()
	{
		return this.listCurrentButton;
	}

	/**
	 * Gets the list past events button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getListPastButton()
	{
		return this.listPastButton;
	}
	
	/**
	 * Gets the event name Anchor from each EventListView in listPanel
	 * @pre true;
	 * @post true;
	 * @return list of HasClickHandlers
	 */
	@Override
	public List<HasClickHandlers> getListedLinks()
	{
		List<HasClickHandlers> links = new ArrayList<HasClickHandlers>();
		List<Widget> elements = this.listPanel.getElements();
		for (int i = 0; i < elements.size(); i++)
		{
			EventListView panel = (EventListView) elements.get(i);
			links.add(panel.getLink());
		}
		return links;	
	}
	
	/**
	 * Hides listPanel
	 * @pre this.listPanel.isVisible() == true;
	 * @post this.listPanel.isVisible() == false;
	 */
	@Override
	public void hideListPanel()
	{
		this.remove(this.listPanel);
	}
	
	/**
	 * Re-populates listPanel with EventListViews created from a list of Events toDisplay
	 * @pre true;
	 * @post this.listPanel.getDisplayer().getRowCount == toDisplay.size() && this.listPanel.isVisible() == true;
	 */
	@Override
	public void populateListPanel(List<Event> toDisplay)
	{
		this.listPanel.clearDisplay();
		this.initializeUI();
		for (int i = 0; i < toDisplay.size(); i++)
		{
			String name, time, location; 
			name = toDisplay.get(i).getName();
			if(toDisplay.get(i).getDate() == null)
			{
				time = "TBD";
			}
			else
			{
				time = toDisplay.get(i).getDate().toString();
			}
			location = toDisplay.get(i).getLocation();
			
			EventListView rowPanel = new EventListView(name, time, location);
			this.listPanel.addElement(rowPanel);
		}
	}
	
	/**
	 * Returns this as a widget so that it can be added to a container
	 * @pre true
	 * @post true
	 * @return this
	 */
	@Override
	public Widget asWidget()
	{
		return this;
	}

	/* 
	@Override
	public HasClickHandlers getEventsList()
	{
		return this.listPanel.getDisplayer();
	}
	
	@Override
	public int getClickedEvent(ClickEvent event)
	{
		int rowIndex = -1;
		HTMLTable.Cell cell = this.listPanel.getDisplayer().getCellForEvent(event);
		
		if (cell != null) 
		{
			rowIndex = cell.getRowIndex();
		}
		
		return rowIndex;
	}
	*/

}
