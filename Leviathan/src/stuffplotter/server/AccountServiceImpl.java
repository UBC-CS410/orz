package stuffplotter.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import stuffplotter.client.AccountService;
import stuffplotter.shared.Account;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.NotFoundException;

@SuppressWarnings("serial")
public class AccountServiceImpl extends RemoteServiceServlet implements AccountService {
	
	private DatabaseStore dbstore = new DatabaseStore();
	private EmailService email = new EmailService();

	@Override
	public Account login(String redirect, String token) {
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    Account account = null;
	    try {
    	  account = dbstore.fetchAccount(user.getNickname());
	    } catch (NotFoundException nfe) {
	      account = new Account(user.getNickname(), user.getEmail());
	      account = this.fillUserinfo(account, token);
	      this.saveAccount(account); // registers a new account
	    } finally {
    	  account.setLoginUrl(userService.createLoginURL(redirect));
    	  account.setLogoutUrl(userService.createLogoutURL(account.getLoginUrl()));
	    }
	    
	    
	    return account;
	}
	
	private Account fillUserinfo(Account account, String token) 
	{
		String accessToken = token.substring(0, token.indexOf('&'));
		String accessRequest = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + accessToken;
		
		final StringBuffer r = new StringBuffer();
        try {
            final URL u = new URL(accessRequest);
            final URLConnection uc = u.openConnection();
            final int end = 1000;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                isr = new InputStreamReader(uc.getInputStream());
                br = new BufferedReader(isr);
                final int chk = 0;
                while ((accessRequest = br.readLine()) != null) {
                    if ((chk >= 0) && ((chk < end))) {
                        r.append(accessRequest).append('\n');
                    }
                }
            } catch (final java.net.ConnectException cex) {
                r.append(cex.getMessage());
            } catch (final Exception ex) {
                System.out.println(ex.toString() + " : " + ex.getMessage());
            } finally {
                try {
                    br.close();
                } catch (final Exception ex) {
                	System.out.println(ex.toString() + " : " + ex.getMessage());
                }
            }
        } catch (final Exception e) {
        	System.out.println(e.toString() + " : " + e.getMessage());
        }
        
        System.out.println(r.toString());
        
        try {
            final JsonFactory f = new JsonFactory();
            JsonParser jp;
            jp = f.createJsonParser(r.toString());
            jp.nextToken();
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                final String fieldname = jp.getCurrentName();
                jp.nextToken();
                if ("picture".equals(fieldname)) {
                    System.out.println(jp.getText());
                } else if ("name".equals(fieldname)) {
                	System.out.println(jp.getText());
                } else if ("email".equals(fieldname)) {
                	System.out.println(jp.getText());
                }
            }
        } catch (final JsonParseException e) {
        	System.out.println(e.toString() + " : " + e.getMessage());
        } catch (final IOException e) {
        	System.out.println(e.toString() + " : " + e.getMessage());
        }
        
		return account;
	}
	
	@Override
	public Account getAccount(String userId) {
		return dbstore.fetchAccount(userId);
	}

	@Override
	public void saveAccount(Account acc)
	{
		dbstore.store(acc);
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
