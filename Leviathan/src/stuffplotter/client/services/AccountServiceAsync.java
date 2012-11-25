package stuffplotter.client.services;

import java.util.List;

import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.shared.Account;
import stuffplotter.shared.AuthenticationException;
import stuffplotter.shared.Notification;

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
	void addNotification(String user, Notification notification,
			AsyncCallback<Void> callback);
	void getNotification(Long id, AsyncCallback<NotificationModel> callback);
	void getNotifications(List<Long> notIds,
			AsyncCallback<List<NotificationModel>> callback);
	void readNotifications(List<Long> notIds,
			AsyncCallback<Void> callback);

}
