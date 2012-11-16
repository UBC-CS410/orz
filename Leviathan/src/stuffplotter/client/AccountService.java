/**
 * 
 */
package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("account")
public interface AccountService extends RemoteService {
	Account login(String back);
	void loadProfile(Account account, String hash);
	Account getAccount(String userId);
	void saveAccount(Account acc);
	void addFriend(Account acc, String friend);
	List<String> getFriends(Account acc);
	List<String> getPendingFriends(Account acc);
	void confirmFriendReq(Account acc, String friend);
	void removeFriend(Account acc, String friend);
	void declineFriendReq(Account acc, String friend);
}
