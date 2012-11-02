package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync {
	void login(String requestUri, AsyncCallback<Account> callback);
	void addFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void getFriends(Account acc, AsyncCallback<List<String>> callback);
	void getAccount(String userId, AsyncCallback<Account> callback);
	void getPendingFriends(Account acc, AsyncCallback<List<String>> callback);
	void confirmFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
}
