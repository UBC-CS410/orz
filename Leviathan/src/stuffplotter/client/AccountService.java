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
	Account login(String requestUri);
	void addFriend(Account acc, String friend);
	List<String> getFriends(Account acc);
}
