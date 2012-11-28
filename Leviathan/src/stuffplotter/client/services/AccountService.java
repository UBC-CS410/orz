/**
 * 
 */
package stuffplotter.client.services;

import java.util.List;
import java.util.Map;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.shared.Account;
import stuffplotter.shared.InvalidAccessTokenException;
import stuffplotter.shared.Notification;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("account")
public interface AccountService extends RemoteService
{
	Account startSession(String host);
	Account storeAccessToken(String token);
	void storeUserinfo() throws InvalidAccessTokenException;
	void addFriend(Account acc, String friend);
	void saveAccount(Account acc);
	List<String> getFriends(Account acc);
	List<String> getPendingFriends(Account acc);
	Account getAccount(String userId);
	void confirmFriendReq(Account acc, String friend);
	void removeFriend(Account acc, String friend);
	void declineFriendReq(Account acc, String friend);
	void addNotification(String user, Notification notification);
	NotificationModel getNotification(Long id);
	List<NotificationModel> getNotifications(List<Long> notIds);
	void saveNotifications(List<NotificationModel> notifs);
	void readNotifications(List<Long> notIds);
	
	/**
	 * Retrieve the list of accounts associated with the given list of e-mail addresses.
	 * @pre userIds != null;
	 * @post true;
	 * @param userIds - the list of accounts to retrieve.
	 * @param callback - the call back for the method.
	 */
	Map<String, Account> getAccounts(List<String> userIds);
}
