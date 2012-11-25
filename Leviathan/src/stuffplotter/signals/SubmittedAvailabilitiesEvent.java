package stuffplotter.signals;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

public class SubmittedAvailabilitiesEvent extends GwtEvent<SubmittedAvailabilitiesEventHandler>
{
	
	public static Type<SubmittedAvailabilitiesEventHandler> TYPE = new Type<SubmittedAvailabilitiesEventHandler>();
	
	private List<Long> availabilitiesIds;

	@Override
	public Type<SubmittedAvailabilitiesEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(SubmittedAvailabilitiesEventHandler handler)
	{
		handler.onSubmitAvailabilities(this);
	}
	
	/**
	 * Constructor for SubmittedAvailabilitiesEvent
	 * @pre true;
	 * @post this.availabilitiesIds == availabilityIds;;
	 * @param availabilityIds - list of submitted availability ids
	 */
	public SubmittedAvailabilitiesEvent(List<Long> availabilityIds)
	{
		//super();
		this.availabilitiesIds = availabilityIds;
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
