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
	private EmailService email = new EmailService();

	@Override
	public Account registerAccount(String hostUri) {
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    Account account = null;

	    try {
    	  account = dbstore.fetchAccount(user.getNickname());
	    } catch (NotFoundException nfe) {
	      account = new Account(user.getNickname(), user.getEmail());
	      dbstore.store(account); // register account
	    } finally {
    	  account.setLoginUrl(userService.createLoginURL(hostUri));
    	  account.setLogoutUrl(userService.createLogoutURL(account.getLoginUrl()));
	    }
	    return account;
	}

	@Override
	public void addFriend(Account acc, String friend) {
		Account temp = null;
		try
		{
			 temp = dbstore.fetchAccount(friend);
		}
		catch(NotFoundException nfe) 
		{
		      temp = new Account(friend, friend+"@gmail.com");
		      dbstore.store(temp); // register account
		      email.sendNewUser(friend, acc.getUserName());
		}
		finally
		{
			if(!temp.getPendingFriends().contains(acc.getUserName()))
			{
				temp.addPendingRequest(acc.getUserName());
				dbstore.store(temp);	
			}
		}

		
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
		newFriend.confirmFriendReq(acc.getUserName());
		dbstore.store(temp);
		dbstore.store(newFriend);
	}

	@Override
	public void removeFriend(Account acc, String friend) {
		Account temp = dbstore.fetchAccount(acc.getUserName());
		temp.getUserFriends().remove(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.getUserFriends().remove(acc.getUserName());
		dbstore.store(temp);
		dbstore.store(newFriend);
		
	}

	@Override
	public void declineFriendReq(Account acc, String friend) {
		Account temp = dbstore.fetchAccount(acc.getUserName());
		temp.getPendingFriends().remove(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.getPendingFriends().remove(acc.getUserName());
		dbstore.store(temp);
		dbstore.store(newFriend);
		
	}
	
	

}
