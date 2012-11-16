package stuffplotter.server;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

public class AchievementRecordUpdater implements RecordVisitor
{
	/*flags to update*/
	private boolean incLogin = false;
	private boolean incEventCreated = false;
	private boolean incEventParticipated = false;

	@Override
	public void visit(Account account)
	{
		
		if(incLogin)
		{
			account.getUserStats().increamentLogin();
		}
			
		
	}



	@Override
	public void visit(Account account, Event event)
	{
		if(incEventCreated)
		{
			account.getUserStats().increamentHostedEvents();
		}
		if(incEventParticipated)
		{
			account.getUserStats().increamentParticipatedEvents();
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
