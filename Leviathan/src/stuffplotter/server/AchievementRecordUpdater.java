package stuffplotter.server;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

public class AchievementRecordUpdater implements AchievementVisitor
{
	private boolean incLogin = false;
	private boolean incEventCreated = false;
	private boolean incEventParticipated = false;

	@Override
	public void visit(Account account)
	{
		
		if(incLogin)
		{
			int logins = account.getNumberOfLogins();
			logins++;
			account.setNumberOfLogins(logins);
		}
			
		
	}



	@Override
	public void visit(Account account, Event event)
	{
		if(incEventCreated)
		{
			int eventsCreated = account.getNumberOfHostedEvents();
			eventsCreated++;
			account.setNumberOfHostedEvents(eventsCreated);
		}
		if(incEventParticipated)
		{
			int eventsPart = account.getNumberOfParticipatedEvents();
			eventsPart++;
			account.setNumberOfParticipatedEvents(eventsPart);
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
