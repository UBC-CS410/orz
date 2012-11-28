package stuffplotter.views;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsPageViewer;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventListingView;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
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
	private final Button rateEventButton;
	
	private SimplePanel eventPanel;
	private ScrollDisplayPanel eventRollPanel;
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
		this.rateEventButton = new Button("Like");
		
		this.acceptInvitationButton.addStyleName("greenActionButton");
		this.declineInvitationButton.addStyleName("redAcitonButton");
		
		this.submitAvailabilityButton.addStyleName("greenActionButton");
		this.finalizeTimeButton.addStyleName("redActionButton");
		
		VerticalPanel actionPanel = new VerticalPanel();

		VerticalPanel commonActionPanel = new VerticalPanel();
		commonActionPanel.add(this.createButton);
		commonActionPanel.add(this.listCurrentButton);
		commonActionPanel.add(this.listPastButton);
	
		this.eventActionPanel = new VerticalPanel();
		this.eventActionPanel.add(this.acceptInvitationButton);
		this.eventActionPanel.add(this.declineInvitationButton);
		this.eventActionPanel.add(this.submitAvailabilityButton);
		this.eventActionPanel.add(this.finalizeTimeButton);
		this.eventActionPanel.add(this.rateEventButton);
		
		actionPanel.setSpacing(10);
		actionPanel.setStyleName("actionBar");
		commonActionPanel.setStyleName("commonActionBar");
		this.eventActionPanel.setStyleName("eventActionBar");
		
		actionPanel.add(commonActionPanel);
		actionPanel.add(eventActionPanel);
		
		this.eventPanel = new SimplePanel();
		this.eventPanel.setStyleName("eventContainer");
		this.eventRollPanel = new ScrollDisplayPanel();
		this.eventRollPanel.setStyleName("eventRoll");
		
		this.add(actionPanel);
		this.setCellWidth(actionPanel, "175px");
		this.add(this.eventPanel);
		
		//SimplePanel testPanel = new SimplePanel();
		this.add(this.eventRollPanel);
		this.setCellWidth(this.eventRollPanel, "225px");
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
	
	/**
	 * Re-populates listPanel with EventListViews created from a list of Events toDisplay
	 * @pre true;
	 * @post this.listPanel.getDisplayer().getRowCount == toDisplay.size() && this.listPanel.isVisible() == true;
	 * @return number of events displayed
	 */
	@Override
	public int initialize(Account user, List<Event> events)
	{
		this.eventRollPanel.clearDisplay();
		for (int i = 0; i < events.size(); i++)
		{	
			EventListingView rowPanel = new EventListingView(user, events.get(i));
			this.eventRollPanel.addElement(rowPanel);
		}
		return events.size();
	}
	
	/**
	 * TODO: Move this to EventRollPresenter
	 * Highlights the selected event listing
	 * @pre true;
	 * @post true;
	 * @param row - the row index of the flex table
	 */
	@Override
	public void setFocus(int row)
	{
		FlexTable eventListings = this.eventRollPanel.getDisplayer();
		for (int i = 0; i < eventListings.getRowCount(); i++)
		{
			eventListings.getWidget(i, 0).removeStyleName("eventSelected");
		}
		eventListings.getWidget(row, 0).addStyleName("eventSelected");
	}
	
	
	/**
	 * Gets the create events button
	 * @pre true;
	 * @post true;
	 * @return a HasClickHandlers 
	 */
	@Override
	public HasClickHandlers getCreateEventButton()
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
	public HasClickHandlers getCurrentEventsButton()
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
	public HasClickHandlers getFinishedEventsButton()
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
	public HasClickHandlers getAcceptInviteButton()
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
	public HasClickHandlers getDeclineInviteButton()
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
	public HasClickHandlers getSubmitTimesButton()
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
	public HasClickHandlers getSelectTimeButton()
	{
		return this.finalizeTimeButton;
	}
	
	/**
	 * Gets the rate event button.
	 * @pre true;
	 * @post true;
	 * @return HasClickHandlers
	 */
	@Override
	public HasClickHandlers getRateEventButton()
	{
		return this.rateEventButton;
	}
	
	/**
	 * Clears the event buttons
	 * @pre true;
	 * @post this.eventActionPanel.isVisible() == false;
	 */
	@Override
	public void hideEventActionButtons()
	{
		this.acceptInvitationButton.setVisible(false);
		this.declineInvitationButton.setVisible(false);
		this.submitAvailabilityButton.setVisible(false);
		this.finalizeTimeButton.setVisible(false);
		this.rateEventButton.setVisible(false);
	}
	
	
	/**
	 * Shows the invitation buttons
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showInvitationButtons()
	{
		this.hideEventActionButtons();
		this.acceptInvitationButton.setVisible(true);
		this.declineInvitationButton.setVisible(true);
	}
	
	/**
	 * Shows the submit availability button
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showSubmitTimesButton()
	{
		this.hideEventActionButtons();
		this.submitAvailabilityButton.setVisible(true);
	}
	
	/**
	 * Shows the finalize time button
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showSelectTimeButton()
	{
		this.hideEventActionButtons();
		this.finalizeTimeButton.setVisible(true);
	}

	/**
	 * Shows the rate event button.
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void showRateEventButton()
	{
		this.hideEventActionButtons();
		this.rateEventButton.setVisible(true);
	}
	
	/**
	 * Get the panel for displaying an event
	 * @pre true;
	 * @post true;
	 * @return a simple panel
	 */
	@Override
	public HasWidgets getEventViewContainer()
	{
		return this.eventPanel;
	}
	
	/**
	 * Clears the event container
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void clearEventViewContainer()
	{
		this.eventPanel.clear();
	}
	
	/**
	 * Get the anchors for each event from event list panel
	 * @pre true;
	 * @post true;
	 * @return list of HasClickHandlers
	 */
	@Override
	public List<HasClickHandlers> getEventListingLinks()
	{
		List<HasClickHandlers> links = new ArrayList<HasClickHandlers>();
		List<Widget> elements = this.eventRollPanel.getElements();
		for (int i = 0; i < elements.size(); i++)
		{
			EventListingView panel = (EventListingView) elements.get(i);
			links.add(panel.getLink());
		}
		return links;	
	}
}
