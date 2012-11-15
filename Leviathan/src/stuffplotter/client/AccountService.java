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
	Account registerAccount(String hostUri);
	void addFriend(Account acc, String friend);
	void saveAccount(Account acc);
	List<String> getFriends(Account acc);
	List<String> getPendingFriends(Account acc);
	Account getAccount(String userId);
	void confirmFriendReq(Account acc, String friend);
	void removeFriend(Account acc, String friend);
	void declineFriendReq(Account acc, String friend);
}
