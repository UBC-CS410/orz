package stuffplotter.server;

import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

public class LevelUpdater implements RecordVisitor
{

	/*Flags to update*/
	boolean madeFriend = false;
	boolean createEvent = false;
	boolean acceptEvent = false;
	
	
	@Override
	public void visit(Account account)
	{
		LevelSystem leveler = new LevelSystem(account);
		if(madeFriend)
		{
			leveler.addExperience(20);
		}
		if(createEvent)
		{
			leveler.addExperience(20);
		}
		if(acceptEvent)
		{
			leveler.addExperience(10);
		}
	}

	@Override
	public void visit(Account account, Event event)
	{

	}
	
	
	public LevelUpdater madeFriend()
	{
		madeFriend = true;
		return this;
	}
	
	public LevelUpdater createEvent()
	{
		createEvent = true;
		return this;
	}
	
	public LevelUpdater acceptEvent()
	{
		acceptEvent = true;
		return this;
	}


}
