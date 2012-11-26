package stuffplotter.signals;

import com.google.gwt.event.shared.GwtEvent;

public class AccountAuthorizedEvent extends GwtEvent<AccountAuthorizedEventHandler>
{	
	public static Type<AccountAuthorizedEventHandler> TYPE = new Type<AccountAuthorizedEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AccountAuthorizedEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(AccountAuthorizedEventHandler handler)
	{
		handler.onAuthorizeAccount();
	}
}
