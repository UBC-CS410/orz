package stuffplotter.signals;

import java.util.List;

import stuffplotter.shared.Event;

import com.google.gwt.event.shared.GwtEvent;

public class EventSchedulerEvent extends GwtEvent<EventSchedulerEventHandler>
{
	
	public static Type<EventSchedulerEventHandler> TYPE = new Type<EventSchedulerEventHandler>();
	
	private Event updatedEvent;
	private int updatedEventIndex;
	private List<Long> availabilitiesIds;

	@Override
	public Type<EventSchedulerEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(EventSchedulerEventHandler handler)
	{
		handler.onSchedulerUpdate(this);
	}
	
	/**
	 * Constructor for SubmittedAvailabilitiesEvent
	 * @pre true;
	 * @post true;
	 */
	public EventSchedulerEvent(Event event, int index)
	{
		this.updatedEvent = event;
		this.updatedEventIndex = index;
	}
	
	/**
	 * Constructor for SubmittedAvailabilitiesEvent
	 * @pre true;
	 * @post this.availabilitiesIds == availabilityIds;;
	 * @param availabilityIds - list of submitted availability ids
	 */
	public EventSchedulerEvent(List<Long> availabilityIds)
	{
		//super();
		this.availabilitiesIds = availabilityIds;
	}
	
	/**
	 * Gets the updated event
	 * @pre true;
	 * @post true;
	 * @return this.updatedEvent
	 */
	public Event getUpdatedEvent()
	{
		return this.updatedEvent;
	}
	
	/**
	 * Gets the updated event index
	 * @pre true;
	 * @post true;
	 * @return this.updatedEventIndex
	 */
	public int getUpdatedEventIndex()
	{
		return this.updatedEventIndex;
	}
	
	/**
	 * Gets the list of availability ids
	 * @pre true;
	 * @post true;
	 * @return this.availbilityIds
	 */
	public List<Long> getAvailabilityIds()
	{
		return this.availabilitiesIds;
	}
	
	
}
