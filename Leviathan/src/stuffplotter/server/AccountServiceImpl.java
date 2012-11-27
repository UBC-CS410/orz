package stuffplotter.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.client.services.AccountService;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.InvalidAccessTokenException;
import stuffplotter.shared.FriendNotification;
import stuffplotter.shared.FriendNotification.FriendNotificationType;
import stuffplotter.shared.Notification;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;

@SuppressWarnings("serial")
public class AccountServiceImpl extends RemoteServiceServlet implements AccountService
{	
	private DatabaseStore dbstore = new DatabaseStore();
	private EmailService email = new EmailService();

	/**
	 * Create a new Account for the current user or get an existing account.
	 * @pre	true;
	 * @post true;
	 * @return current user Account
	 */
	@Override
	public Account startSession(String host)
	{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    Account account = null;
	    try
	    {
    	  account = (Account) dbstore.simpleFetch(new Key<Account>(Account.class, user.getEmail()));
	    }
	    catch (NotFoundException nfe)
	    {
	      account = new Account(user.getEmail());
	    }
	    finally
	    {
    	  //If users choose to log out, redirect them back to login page
    	  account.setLogoutUrl(userService.createLogoutURL(userService.createLoginURL(host)));
    	  dbstore.simpleStore(account); 
	    }
	    
	    return account;
	}
	
	/**
	 * Authorizes an existing account by setting its access token
	 * @pre dbstore.fetchAccount(userService.getCurrentUser().getEmail()) != null
	 * @post dbstore.fetchAccount(userService.getCurrentUser().getUserAccessToken()) == token
	 * @param token - access token
	 * @return an authorized Account
	 */
	@Override
	public Account storeAccessToken(String token)
	{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
		Account account = (Account) dbstore.simpleFetch(new Key<Account>(Account.class, user.getEmail()));
		account.setAccessToken(token);
		dbstore.simpleStore(account);
		
		return account;
	}
	
	/**
	 * Saves full name and profile picture from Google account to user account
	 * @pre true;
	 * @post
	 */
	@Override
	public void storeUserinfo() throws InvalidAccessTokenException
	{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
		Account account = (Account) dbstore.simpleFetch(new Key<Account>(Account.class, user.getEmail()));
		
		String token = account.getAccessToken();
		String request = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;
		System.out.println(request);
		final StringBuffer r = new StringBuffer();
        try
        {
            final URL u = new URL(request);
            final URLConnection uc = u.openConnection();
            final int end = 1000;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try
            {
                isr = new InputStreamReader(uc.getInputStream());
                br = new BufferedReader(isr);
                final int chk = 0;
                while ((request = br.readLine()) != null)
                {
                    if ((chk >= 0) && ((chk < end)))
                    {
                        r.append(request).append('\n');
                    }
                }
            }
            catch (final java.net.ConnectException cex)
            {
                r.append(cex.getMessage());
            }
            catch (final Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try
                {
                    br.close();
                }
                catch (final Exception ex)
                {
                	ex.printStackTrace();
                }
            }
        }
        catch (final Exception e)
        {
        	e.printStackTrace();
        }
        
        try
        {
            final JsonFactory f = new JsonFactory();
            JsonParser jp;
            jp = f.createJsonParser(r.toString());
            jp.nextToken();
            while (jp.nextToken() != JsonToken.END_OBJECT)
            {
                final String fieldname = jp.getCurrentName();
                jp.nextToken();
                if ("error".equals(fieldname))
                {
                	account.setAccessToken(null);
                } 
                else if ("picture".equals(fieldname))
                {
                    account.setUserProfilePicture(jp.getText());
                }
                else if ("name".equals(fieldname))
                {
                	account.setUserFullName(jp.getText());
                } 
                else if ("birthdate".equals(fieldname))
                {
                	//account.setBirthDate(jp.getText());
                }
            }
        }
        catch (final JsonParseException e)
        {
        	e.printStackTrace();
        }
        catch (final IOException e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	dbstore.simpleStore(account);
        }
        
        if(account.getAccessToken() == null)
        {
        	throw new InvalidAccessTokenException();
        }
	}
	
	@Override
	public Account getAccount(String userId)
	{
		return dbstore.fetchAccount(userId);
	}
	
	@Override
	public Map<String, Account> getAccounts(List<String> userIds)
	{
		return dbstore.fetchAccounts(userIds);
	}

	@Override
	public void saveAccount(Account acc)
	{
		dbstore.simpleStore(acc);
	}
	
	@Override
	public void addFriend(Account acc, String friend)
	{
		Account temp = null;
		Notification newFriendNot = new FriendNotification(FriendNotificationType.FRIENDREQUEST, friend, acc.getUserEmail());
		dbstore.simpleStore(newFriendNot);
		Long notId = newFriendNot.getNotificationId();
		
		try
		{
			 temp = dbstore.fetchAccount(friend);
		}
		catch(NotFoundException nfe) 
		{
		      temp = new Account(friend);
		      dbstore.simpleStore(temp); // register account
		      email.sendNewUser(friend, acc.getUserEmail());
		}
		finally
		{
			if(!temp.getPendingFriends().contains(acc.getUserEmail()))
			{
				temp.addPendingRequest(acc.getUserEmail());
				temp.addUserNotification(notId);
				dbstore.simpleStore(temp);	
			}
		}
	}

	@Override
	public List<String> getFriends(Account acc)
	{
		Account temp = dbstore.fetchAccount(acc.getUserEmail());
		return temp.getUserFriends();
	}

	@Override
	public List<String> getPendingFriends(Account acc)
	{
		Account temp = dbstore.fetchAccount(acc.getUserEmail());
		return temp.getPendingFriends();
	}

	@Override
	public void confirmFriendReq(Account acc, String friend)
	{
		Notification myFriendAccept = new FriendNotification(FriendNotificationType.FRIENDACCEPTED, friend, acc.getUserEmail());
		Notification myselfAccept = new FriendNotification(FriendNotificationType.FRIENDACCEPTED,  acc.getUserEmail(), friend);
		
		dbstore.simpleStore(myFriendAccept);
		dbstore.simpleStore(myselfAccept);
		Long myFriendId = myFriendAccept.getNotificationId();
		Long myselfAcceptLong = myselfAccept.getNotificationId();
		
		Account temp = dbstore.fetchAccount(acc.getUserEmail());
		temp.confirmFriendRequest(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.confirmFriendRequest(acc.getUserEmail());
		
		temp.addUserNotification(myselfAcceptLong);
		newFriend.addUserNotification(myFriendId);
		
		dbstore.simpleStore(temp);
		dbstore.simpleStore(newFriend);
		
		AccountStatistic tempStats = dbstore.fetchAccountStats(temp.getUserEmail());
		AccountStatistic newFriendStats = dbstore.fetchAccountStats(newFriend.getUserEmail());
		
		tempStats.setNumberOfFriends(temp.getUserFriends().size());
		newFriendStats.setNumberOfFriends(temp.getUserFriends().size());
		
		dbstore.simpleStore(tempStats);
		dbstore.simpleStore(newFriendStats);
	}

	@Override
	public void removeFriend(Account acc, String friend)
	{
		Account temp = dbstore.fetchAccount(acc.getUserEmail());
		temp.getUserFriends().remove(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.getUserFriends().remove(acc.getUserEmail());
		dbstore.simpleStore(temp);
		dbstore.simpleStore(newFriend);	
		
		AccountStatistic tempStats = dbstore.fetchAccountStats(temp.getUserEmail());
		AccountStatistic newFriendStats = dbstore.fetchAccountStats(newFriend.getUserEmail());
		
		tempStats.setNumberOfFriends(temp.getUserFriends().size());
		newFriendStats.setNumberOfFriends(temp.getUserFriends().size());
		
		dbstore.simpleStore(tempStats);
		dbstore.simpleStore(newFriendStats);
	}

	@Override
	public void declineFriendReq(Account acc, String friend)
	{
		Account temp = dbstore.fetchAccount(acc.getUserEmail());
		temp.getPendingFriends().remove(friend);
		Account newFriend = dbstore.fetchAccount(friend);
		newFriend.getPendingFriends().remove(acc.getUserEmail());
		dbstore.simpleStore(temp);
		dbstore.simpleStore(newFriend);	
	}

	@Override
	public void addNotification(String user, Notification notification)
	{
		dbstore.simpleStore(notification);
		Long notID = notification.getNotificationId();
		
		Account account = dbstore.fetchAccount(user);
		account.addUserNotification(notID);
		dbstore.simpleStore(account);
	}

	@Override
	public Notification getNotification(Long id)
	{
		return dbstore.fetchNotification(id);
	}

	@Override
	public List<NotificationModel> getNotifications(List<Long> notIds)
	{
		List<NotificationModel> result = new ArrayList<NotificationModel>();
		for(Long id : notIds)
		{
			result.add(getNotification(id));
		}
		
		return result;
	}

	@Override
	public void readNotifications(List<Long> notIds)
	{
		for(Long ids: notIds)
		{
			Notification notif = dbstore.fetchNotification(ids);
			notif.setNewNotification(false);
			dbstore.simpleStore(notif);
		}
	}

	@Override
	public void saveNotifications(List<NotificationModel> notifs)
	{
		for(NotificationModel notif : notifs)
		{
			dbstore.simpleStore((Notification) notif);
		}
		
	}
}
