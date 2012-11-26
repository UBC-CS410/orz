package stuffplotter.server;

import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Event;

public class LevelUpdater implements RecordVisitor
{

	/*Flags to update*/
	boolean madeFriend = false;
	boolean createEvent = false;
	boolean participatedEvent = false;
	boolean commentEvent = false;
	boolean rateEvent = false;
	
	
	@Override
	public void visit(AccountStatistic account)
	{
		LevelSystem leveler = new LevelSystem(account);
		if(madeFriend)
		{
			leveler.addExperience(35);
		}
		if(createEvent)
		{
			leveler.addExperience(40);
		}
		if(participatedEvent)
		{
			leveler.addExperience(25);
		}
		if(commentEvent)
		{
			leveler.addExperience(10);
		}
		if(rateEvent)
		{
			leveler.addExperience(15);
		}
	}

	@Override
	public void visit(AccountStatistic account, Event event)
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
		participatedEvent = true;
		return this;
	}
	
	public LevelUpdater commentEvent()
	{
		commentEvent = true;
		return this;
	}
	
	public LevelUpdater rateEvent()
	{
		rateEvent = true;
		return this;
	}


}
