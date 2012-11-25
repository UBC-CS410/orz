package stuffplotter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.shared.Event.Frame;
import stuffplotter.views.events.EventCreationPageVisitor;
import stuffplotter.views.events.EventDateSelectionPanel;
import stuffplotter.views.events.EventInfoInputPanel;
import stuffplotter.views.events.EventInfoPanel;
import stuffplotter.views.events.FriendSelectionPanel;
import stuffplotter.views.events.TimeSheetPanel;

/**
 * Class that is a visitor for any panel that implements the EventSubmittable interface.
 * Retrieves the selected values from each page to help create an Event object.
 */
public class EventCreationPageRetriever implements EventCreationPageVisitor
{
	/**
	 * Full name of the event owner.
	 */
	private String eventOwner;
	/**
	 * E-mail of the event owner (unique ID).
	 */
	private String eventOwnerID;
	private String eventName;
	private String eventLocation;
	private Double[] eventCoordinates;
	private String eventCost;
	private Frame eventFrame;
	private String eventDuration;
	private String eventDescription;
	private List<Date> selectedTimeSlots;
	private List<String> selectedFriends;
	
	/**
	 * Constructor for the EventCreationPageRetriever.
	 * @pre eventOwner != null;
	 * @post true;
	 * @param eventOwnerID - the e-mail address of the user that owns the event.
	 */
	public EventCreationPageRetriever(String eventOwnerID)
	{
		this.eventOwnerID = eventOwnerID;
	}

	/**
	 * Retrieve the owner (full name) of the event.
	 * @pre true;
	 * @post true;
	 * @return the owner (full name) of the event.
	 */
	public String getOwner()
	{
		return this.eventOwner;
	}
	
	/**
	 * Retrieve the owner ID (e-mail address) of the event.
	 * @pre true;
	 * @post true;
	 * @return the owner ID (e-mail address) of the event.
	 */
	public String getOwnerID()
	{
		return this.eventOwnerID;
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
	 * Retrieve the time frame for the event.
	 * @pre true;
	 * @post true;
	 * @return the time frame for the event.
	 */
	public Frame getFrame()
	{
		return this.eventFrame;
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
	 * Retrieve the selected friends (e-mail addresses) for the event.
	 * @pre true;
	 * @post true;
	 * @return the friends selected (e-mail addresses) for the event.
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
	public List<Date> getSelectedTimeSlots()
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
		this.eventOwner = inputPanel.getCreator();
		this.eventName = inputPanel.getName();
		this.eventLocation = inputPanel.getLocation();
		this.eventCoordinates = inputPanel.getMapCoordinatesAsArray();
		this.eventCost = inputPanel.getCost();
		this.eventFrame = inputPanel.getFrame();
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
			Widget friend = iterator.next();
			if(friend instanceof CheckBox)
			{
				if(((CheckBox) friend).getValue())
				{
					selectedFriends.add(((CheckBox) friend).getFormValue());
				}
			}
		}
	}
}
