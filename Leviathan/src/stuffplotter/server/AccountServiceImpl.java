package stuffplotter.server;

import java.io.IOException;

import stuffplotter.client.AccountService;
import stuffplotter.shared.Account;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class AccountServiceImpl extends RemoteServiceServlet implements AccountService {

	@Override
	public Account login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    Account account = new Account();

	    if (user != null) {
	      account = new Account(user.getUserId(), user.getNickname(), user.getEmail());
	      account.setSession(true);
	      account.setLogout(userService.createLogoutURL(requestUri));
	    } else {
	      account.setSession(false);
	      account.setLogin(userService.createLoginURL(requestUri));
	    }
	    return account;
	}

}
