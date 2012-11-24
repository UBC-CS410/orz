package stuffplotter.views;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsPageViewer;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventListView;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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
	
	private SimplePanel eventPanel;
	private ScrollDisplayPanel eventListPanel;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
		
		this.createButton = new Button("Create Event");
		this.listCurrentButton = new Button("View Current Events");
		this.listPastButton = new Button("View Past Events");
		
		VerticalPanel actionPanel = new VerticalPanel();
		actionPanel.add(this.createButton);
		actionPanel.add(this.listCurrentButton);
		actionPanel.add(this.listPastButton);
		
		this.eventPanel = new SimplePanel();
		this.eventListPanel = new ScrollDisplayPanel();
		
		this.add(actionPanel);
		this.add(this.eventPanel);
		this.add(this.eventListPanel);
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
	 * Get the anchors for each event from event list panel
	 * @pre true;
	 * @post true;
	 * @return list of HasClickHandlers
	 */
	@Override
	public List<HasClickHandlers> getEventViewers()
	{
		List<HasClickHandlers> links = new ArrayList<HasClickHandlers>();
		List<Widget> elements = this.eventListPanel.getElements();
		for (int i = 0; i < elements.size(); i++)
		{
			EventListView panel = (EventListView) elements.get(i);
			links.add(panel.getLink());
		}
		return links;	
	}
	
	/**
	 * Get the panel for displaying an event
	 * @pre true;
	 * @post true;
	 * @return a simple panel
	 */
	@Override
	public HasWidgets getEventViewerContainer()
	{
		return this.eventPanel;
	}
	
	/**
	 * Shows listPanel
	 * @pre this.listPanel.isVisible() == false;
	 * @post this.listPanel.isVisible() == true;
	 */
	@Override
	public void showEventViewers()
	{
		this.eventPanel.clear();
		this.eventListPanel.setVisible(true);
	}
	
	/**
	 * Hides listPanel
	 * @pre this.listPanel.isVisible() == true;
	 * @post this.listPanel.isVisible() == false;
	 */
	@Override
	public void hideEventViewers()
	{
		this.eventListPanel.setVisible(false);
	}
	
	
	/**
	 * Re-populates listPanel with EventListViews created from a list of Events toDisplay
	 * @pre true;
	 * @post this.listPanel.getDisplayer().getRowCount == toDisplay.size() && this.listPanel.isVisible() == true;
	 */
	@Override
	public void initialize(List<Event> events)
	{
		this.eventListPanel.clearDisplay();
		for (int i = 0; i < events.size(); i++)
		{
			String name, time, location; 
			name = events.get(i).getName();
			if(events.get(i).getDate() == null)
			{
				time = "TBD";
			}
			else
			{
				time = events.get(i).getDate().toString();
			}
			location = events.get(i).getLocation();
			
			EventListView rowPanel = new EventListView(name, time, location);
			this.eventListPanel.addElement(rowPanel);
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
}
