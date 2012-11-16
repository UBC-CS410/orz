package stuffplotter.server;

import stuffplotter.client.services.AccountStatsService;
import stuffplotter.shared.AccountStatistic;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.NotFoundException;

public class AccountStatsServiceImpl extends RemoteServiceServlet implements AccountStatsService
{
	private DatabaseStore dbstore = new DatabaseStore();
	
	@Override
	public AccountStatistic getStats(String userEmail)
	{
		AccountStatistic astat = null;
		
		try
		{
			astat = dbstore.fetchAccountStats(userEmail);
		}
		catch(NotFoundException nfe)
		{
			astat = new AccountStatistic(userEmail);
		}
		
		astat.increamentLogin();
		
		
		
		dbstore.store(astat);
		return astat;
	}

}
