package stuffplotter.server;

import stuffplotter.client.services.AccountStatsService;
import stuffplotter.shared.AccountStatistic;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.NotFoundException;

/**
 * Class to process AccountStatistic information.
 */
@SuppressWarnings("serial")
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
		
		astat.incrementLogin();
		dbstore.store(astat);
		return astat;
	}

	@Override
	public void save(AccountStatistic astat)
	{
		dbstore.store(astat);
	}
}
