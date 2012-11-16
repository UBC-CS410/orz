package stuffplotter.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.shared.AccountStatistic;

public interface AccountStatsServiceAsync
{

	void getStats(String userEmail, AsyncCallback<AccountStatistic> callback);
	void save(AccountStatistic astat, AsyncCallback<Void> asyncCallback);

}
