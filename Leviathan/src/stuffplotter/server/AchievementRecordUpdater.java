package stuffplotter.server;

import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Event;

public class AchievementRecordUpdater implements RecordVisitor
{
	/*flags to update*/
	private boolean incLogin = false;
	private boolean incEventCreated = false;
	private boolean incEventParticipated = false;

	@Override
	public void visit(AccountStatistic account)
	{
		
		if(incLogin)
		{
			account.increamentLogin();
		}
			
		
	}



	@Override
	public void visit(AccountStatistic account, Event event)
	{
		if(incEventCreated)
		{
			account.increamentHostedEvents();
		}
		if(incEventParticipated)
		{
			account.increamentParticipatedEvents();
		}
		
		
	}
	

	
	/**
	 * Sets the incrementLogin to true
	 * @pre
	 * @post
	 */
	public AchievementRecordUpdater incrementLogin()
	{
		incLogin = true;
		return this;
	}
	
	public AchievementRecordUpdater increamentEventCreated()
	{
		incEventCreated = true;
		return this;
	}
	
	public AchievementRecordUpdater increamentEventParticipated()
	{
		incEventParticipated = true;
		return this;
	}

}
