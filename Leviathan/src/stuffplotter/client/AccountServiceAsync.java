package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync {
	void registerAccount(String hostUri, AsyncCallback<Account> callback);
	void addFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void saveAccount(Account acc, AsyncCallback<Void> callback);
	void getFriends(Account acc, AsyncCallback<List<String>> callback);
	void getAccount(String userId, AsyncCallback<Account> callback);
	void getPendingFriends(Account acc, AsyncCallback<List<String>> callback);
	void confirmFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
	void removeFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void declineFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
}
