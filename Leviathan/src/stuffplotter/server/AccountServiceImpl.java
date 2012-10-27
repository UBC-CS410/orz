package stuffplotter.server;

import java.util.List;

import stuffplotter.client.AccountService;
import stuffplotter.shared.Account;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.NotFoundException;

public class AccountServiceImpl extends RemoteServiceServlet implements AccountService {
	
	private DatabaseStore dbstore = new DatabaseStore();

	@Override
	public Account login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    Account account = null;

	    if (user != null) {
	    	
	      try {
	    	  account = dbstore.fetchAccount(user.getUserId());
	      } catch (NotFoundException nfe) {
		      account = new Account(user.getUserId(), user.getNickname(), user.getEmail());
		      dbstore.store(account); // register account
	      } finally {
		      account.setSession(true);
		      account.setLogout(userService.createLogoutURL(requestUri));  
	      }
	    } else {
	      account = new Account();
	      account.setSession(false);
	      account.setLogin(userService.createLoginURL(requestUri));
	    }
	    return account;
	}

	@Override
	public void addFriend(Account acc, String friend) {
		Account temp = dbstore.fetchAccount(acc.getUserId());
		temp.addUserFriend(friend);
		dbstore.store(temp);
	}

	@Override
	public List<String> getFriends(Account acc) {
		Account temp = dbstore.fetchAccount(acc.getUserId());
		return temp.getUserFriends();
	}

}
