package stuffplotter.client;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync {
	void login(String requestUri, AsyncCallback<Account> callback);
}
