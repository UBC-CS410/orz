package stuffplotter.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.views.events.EventDateSelectionPanel;
import stuffplotter.views.events.EventInfoInputPanel;
import stuffplotter.views.events.EventInfoPanel;
import stuffplotter.views.events.FriendSelectionPanel;
import stuffplotter.views.events.TimeSheetPanel;
import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.shared.MonthContainer;

/**
 * Class that is a visitor for any panel that implements the EventSubmittable interface.
 * Retrieves the selected values from each page to help create an Event object.
 */
public class EventCreationPageRetriever implements EventCreationPageVisitor
{
	private String eventOwner;
	private String eventName;
	private String eventLocation;
	private Double[] eventCoordinates;
	private String eventCost;
	private String eventDuration;
	private String eventDescription;
	private List<MonthContainer> selectedTimeSlots;
	private List<String> selectedFriends;
	
	/**
	 * Constructor for the EventCreationPageRetriever.
	 * @pre eventOwner != null;
	 * @post true;
	 * @param eventOwner - the name of the user that owns the event.
	 */
	public EventCreationPageRetriever(String eventOwner)
	{
		this.eventOwner = eventOwner;
	}

	/**
	 * Retrieve the owner of the event.
	 * @pre true;
	 * @post true;
	 * @return the owner of the event.
	 */
	public String getOwner()
	{
		return this.eventOwner;
	}

	/**
	 * Retrieve the name of the event.
	 * @pre true;
	 * @post true;
	 * @return the name of the event.
	 */
	public String getName()
	{
		return this.eventName;
	}

	/**
	 * Retrieve the address of the event.
	 * @pre true;
	 * @post true;
	 * @return the address of the event.
	 */
	public String getLocation()
	{
		return this.eventLocation;
	}

	/**
	 * Retrieve the coordinates for the event.
	 * @pre true;
	 * @post true;
	 * @return the coordinates for the event.
	 */
	public Double[] getCoordinates()
	{
		return this.eventCoordinates;
	}

	/**
	 * Retrieve the cost of the event.
	 * @pre true;
	 * @post true;
	 * @return the cost of the event.
	 */
	public String getCost()
	{
		return this.eventCost;
	}

	/**
	 * Retrieve the duration for the event.
	 * @pre true;
	 * @post true;
	 * @return the duration of the event.
	 */
	public String getDuration()
	{
		return this.eventDuration;
	}

	/**
	 * Retrieve the description for the event.
	 * @pre true;
	 * @post true;
	 * @return the description of the event.
	 */
	public String getDescription()
	{
		return this.eventDescription;
	}

	/**
	 * Retrieve the selected friends for the event.
	 * @pre true;
	 * @post true;
	 * @return the friends selected for the event.
	 */
	public List<String> getSelectedFriends()
	{
		return this.selectedFriends;
	}
	
	/**
	 * Retrieve the selected time slots for the event.
	 * @pre true;
	 * @post true;
	 * @return the selected time slots for the event.
	 */
	public List<MonthContainer> getSelectedTimeSlots()
	{
		return this.selectedTimeSlots;
	}
	
	/**
	 * Visit an EventInfoInputPanel to retrieve general information for an event.
	 * @pre infoPanel != null;
	 * @post true;
	 * @param infoPanel - the EventInfoInputPanel to retrieve values from.
	 */
	@Override
	public void visit(EventInfoPanel infoPanel)
	{
		EventInfoInputPanel inputPanel = infoPanel.getEventInfoInputPanel();
		this.eventName = inputPanel.getName();
		this.eventLocation = inputPanel.getLocation();
		this.eventCoordinates = inputPanel.getMapCoordinatesAsArray();
		this.eventCost = inputPanel.getCost();
		this.eventDuration = inputPanel.getDuration();
		this.eventDescription = inputPanel.getDescription();
	}

	/**
	 * Visit an EventDateSelectionPanel to retrieve proposed times for an event.
	 * @pre dateSelectionPanel != null;
	 * @post true;
	 * @param dateSelectionPanel - the EventDateSelectionPanel to retrieve values from.
	 */
	@Override
	public void visit(EventDateSelectionPanel dateSelectionPanel)
	{
		TimeSheetPanel timeSheetPanel = dateSelectionPanel.getTimeSheetPanel();
		this.selectedTimeSlots = timeSheetPanel.retrieveSubmission();
	}

	/**
	 * Visit a FriendSelectionPanel to retrieve the selected friends for an event.
	 * @pre friendSelection != null;
	 * @post true;
	 * @param friendSelection - the FriendSelectionPanel to retrieve values from.
	 */
	@Override
	public void visit(FriendSelectionPanel friendSelection)
	{
		selectedFriends = new ArrayList<String>();
		
		FlexTable friendList = friendSelection.getFriendList();
		Iterator<Widget> iterator = friendList.iterator();
		
		// while loop to retrieve the selected friends
		while(iterator.hasNext())
		{
			CheckBox friend = (CheckBox) iterator.next();
			if(friend.getValue())
			{
				selectedFriends.add(friend.getFormValue());
			}
		}
	}
}
