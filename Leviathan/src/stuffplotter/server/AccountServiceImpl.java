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
	    	  account = dbstore.fetchAccount(user.getNickname());
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
		Account temp = dbstore.fetchAccount(friend);
		temp.addPendingRequest(acc.getUserName());
		dbstore.store(temp);
	}

	@Override
	public List<String> getFriends(Account acc) {
		Account temp = dbstore.fetchAccount(acc.getUserName());
		return temp.getUserFriends();
	}

	@Override
	public Account getAccount(String userId) {
		return dbstore.fetchAccount(userId);
	}

	@Override
	public List<String> getPendingFriends(Account acc) {
		Account temp = dbstore.fetchAccount(acc.getUserName());
		return temp.getPendingFriends();
	}

	@Override
	public void confirmFriendReq(Account acc, String friend) {
		Account temp = dbstore.fetchAccount(acc.getUserName());
		temp.confirmFriendReq(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.addUserFriend(acc.getUserName());
		dbstore.store(temp);
		dbstore.store(newFriend);
		
	}
	
	

}
