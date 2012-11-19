package stuffplotter.client.services;

import stuffplotter.shared.AccountStatistic;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("astats")
public interface AccountStatsService extends RemoteService
{
	/**
	 * Returns the AccountStatisitic Object
	 * 
	 * @param userEmail
	 * @return the desired AccountStatisitic Object
 	 */
	AccountStatistic getStats(String userEmail);
	/**
	 * Saves the AccountStatistic Object to the datastore
	 * @pre		Previous AccountStatistic is currently saved in the datastore
	 * @post 	New AccountStatistic is saved in the datastore
	 * @param astat
	 */
	void save(AccountStatistic astat);
}
