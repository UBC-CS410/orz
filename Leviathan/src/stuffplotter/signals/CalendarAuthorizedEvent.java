package stuffplotter.signals;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that gets fired when an google calendar is successfully authorized.
 */
public class CalendarAuthorizedEvent extends GwtEvent<CalendarAuthorizedEventHandler>
{
	public static Type<CalendarAuthorizedEventHandler> TYPE = new Type<CalendarAuthorizedEventHandler>();

	@Override
	public Type<CalendarAuthorizedEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(CalendarAuthorizedEventHandler handler)
	{
		handler.onCalendarAuthorized(this);
	}

}
