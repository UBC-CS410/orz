package stuffplotter.signals;

import com.google.gwt.event.shared.GwtEvent;

public class UpdateStatsEvent extends GwtEvent<UpdateStatsEventHandler>
{
	public static Type<UpdateStatsEventHandler> TYPE = new Type<UpdateStatsEventHandler>();
	String accountId;
	
	
	/**
	 * Constructor for UpdateStatsEvent
	 * @pre true;
	 * @post true;
	 * @param userEmail - Account ID, their email
	 */
	public UpdateStatsEvent(String userEmail)
	{
		this.accountId = userEmail;
	}
	
	public String getAccountID()
	{
		return this.accountId;
	}
	
	
	@Override
	public Type<UpdateStatsEventHandler> getAssociatedType()
	{
		return TYPE;
	}



	@Override
	protected void dispatch(UpdateStatsEventHandler handler)
	{
		// TODO Auto-generated method stub
		
	}

}
