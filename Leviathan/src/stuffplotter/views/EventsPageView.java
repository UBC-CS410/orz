package stuffplotter.views;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsPageViewer;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventListingView;
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
	/*
	 * Common action buttons
	 */
	private final Button createButton;
	private final Button listCurrentButton;
	private final Button listPastButton;
	
	/*
	 * User specific action buttons
	 */
	private final Button acceptInvitationButton;
	private final Button declineInvitationButton;
	private final Button submitAvailabilityButton;
	private final Button finalizeTimeButton;
	
	private SimplePanel eventPanel;
	private ScrollDisplayPanel eventListPanel;
	private VerticalPanel eventActionPanel;
	
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
		
		this.acceptInvitationButton = new Button("Accept Invitation");
		this.declineInvitationButton = new Button("Decline Invitation");
		this.submitAvailabilityButton = new Button("Submit Availabilities");
		this.finalizeTimeButton = new Button("Finalize Time");
		
		VerticalPanel actionPanel = new VerticalPanel();
		actionPanel.setStyleName("actionBar");
		actionPanel.setSpacing(10);
		
		VerticalPanel commonActionPanel = new VerticalPanel();
		commonActionPanel.add(this.createButton);
		commonActionPanel.add(this.listCurrentButton);
		commonActionPanel.add(this.listPastButton);
	
		this.eventActionPanel = new VerticalPanel();
		this.eventActionPanel.addStyleName("eventActionBar");
		
		actionPanel.add(commonActionPanel);
		actionPanel.add(eventActionPanel);
		
		this.eventPanel = new SimplePanel();
		this.eventPanel.setStyleName("eventPage");
		this.eventListPanel = new ScrollDisplayPanel();
		this.eventListPanel.setStyleName("eventRoll");
		
		this.add(actionPanel);
		this.setCellWidth(actionPanel, "200px");
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
	 * Gets the accept invitations button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getAcceptButton()
	{
		return this.acceptInvitationButton;
	}

	/**
	 * Gets the decline invitations button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getDeclineButton()
	{
		return this.declineInvitationButton;
	}

	/**
	 * Gets the submit availabilities button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getSubmitAvailabilitiesButton()
	{
		return this.submitAvailabilityButton;
	}

	/**
	 * Gets the finalize time button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers
	 */
	@Override
	public HasClickHandlers getFinalizeTimeButton()
	{
		return this.finalizeTimeButton;
	}
	
	/**
	 * Shows the invitation buttons
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showInvitationButtons()
	{
		this.eventActionPanel.clear();
		this.eventActionPanel.add(this.acceptInvitationButton);
		this.eventActionPanel.add(this.declineInvitationButton);
	}
	
	/**
	 * Shows the submit availability button
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showSubmitAvailabilitiesButton()
	{
		this.eventActionPanel.clear();
		this.eventActionPanel.add(this.submitAvailabilityButton);
	}

	
	/**
	 * Shows the finalize time button
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showFinalizeTimeButton()
	{
		this.eventActionPanel.clear();
		this.eventActionPanel.add(this.finalizeTimeButton);
	}
	
	/**
	 * Clears the event action panel
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void clearEventActions()
	{
		this.eventActionPanel.clear();
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
			EventListingView panel = (EventListingView) elements.get(i);
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
	 * Shows eventListPanel 
	 * @pre this.eventListPanel.isVisible() == false && this.eventPanel.isVisible() == true;
	 * @post this.eventListPanel.isVisible() == true && this.eventPanel.isVisible() == false;
	 */
	@Override
	public void showEventViewers()
	{
		this.eventPanel.clear();
		this.remove(eventPanel);
		this.add(eventListPanel);
	}
	
	/**
	 * Shows eventPanel
	 * @pre this.eventListPanel.isVisible() == true && this.eventPanel.isVisible() == false;
	 * @post this.eventListPanel.isVisible() == false && this.eventPanel.isVisible() == true;
	 */
	@Override
	public void showEventView()
	{
		this.remove(eventListPanel);
		this.add(eventPanel);
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
			
			EventListingView rowPanel = new EventListingView(name, time, location);
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
