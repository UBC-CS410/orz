package stuffplotter.client.services;

import java.util.List;
import java.util.Map;

import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AccountServiceAsync
{
	void startSession(String host, AsyncCallback<Account> callback);
	void storeAccessToken(String token, AsyncCallback<Account> callback);
	
	void storeUserinfo(AsyncCallback<Void> callback);
	void addFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void saveAccount(Account acc, AsyncCallback<Void> callback);
	void getFriends(Account acc, AsyncCallback<List<String>> callback);
	void getAccount(String userId, AsyncCallback<Account> callback);
	void getPendingFriends(Account acc, AsyncCallback<List<String>> callback);
	void confirmFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
	void removeFriend(Account acc, String friend, AsyncCallback<Void> callback);
	void declineFriendReq(Account acc, String friend, AsyncCallback<Void> callback);
	void addNotification(String user, Notification notification, AsyncCallback<Void> callback);
	void getNotification(Long id, AsyncCallback<NotificationModel> callback);
	void getNotifications(List<Long> notIds, AsyncCallback<List<NotificationModel>> callback);
	void readNotifications(List<Long> notIds, AsyncCallback<Void> callback);
	
	/**
	 * Retrieve the list of accounts associated with the given list of e-mail addresses.
	 * @pre userIds != null;
	 * @post true;
	 * @param userIds - the list of accounts to retrieve.
	 * @param callback - the call back for the method.
	 */
	void getAccounts(List<String> userIds, AsyncCallback<Map<String, Account>> callback);
	void saveNotifications(List<NotificationModel> notifs, AsyncCallback<Void> callback);
}
