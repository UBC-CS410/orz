/**
 * 
 */
package stuffplotter.client;

import stuffplotter.shared.Account;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("account")
public interface AccountService extends RemoteService {
	Account login(String requestUri);
}
