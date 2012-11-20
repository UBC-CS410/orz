package stuffplotter.signals;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event that gets fired when the page needs to be freshed.
 */
public class RefreshPageEvent extends GwtEvent<RefreshPageEventHandler>
{
	public static Type<RefreshPageEventHandler> TYPE = new Type<RefreshPageEventHandler>();
	
	@Override
	public Type<RefreshPageEventHandler> getAssociatedType()
	{
		return this.TYPE;
	}

	@Override
	protected void dispatch(RefreshPageEventHandler handler)
	{
		handler.onRefreshPage(this);
	}
}
