package stuffplotter.client.services;

import stuffplotter.shared.AccountStatistic;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("astats")
public interface AccountStatsService extends RemoteService
{
	AccountStatistic getStats(String userEmail);
}
