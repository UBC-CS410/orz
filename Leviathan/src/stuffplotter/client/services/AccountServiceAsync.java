package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync {
	void login(String back, AsyncCallback<Account> callback);
	void loadProfile(Account account, String hash, AsyncCallback<Void> callback);
	void addFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void saveAccount(Account acc, AsyncCallback<Void> callback);
	void getFriends(Account acc, AsyncCallback<List<String>> callback);
	void getAccount(String userId, AsyncCallback<Account> callback);
	void getPendingFriends(Account acc, AsyncCallback<List<String>> callback);
	void confirmFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
	void removeFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void declineFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
}
