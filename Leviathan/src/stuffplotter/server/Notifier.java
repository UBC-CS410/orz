package stuffplotter.server;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.client.services.AccountService;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.shared.Account;
import stuffplotter.shared.Achievement;
import stuffplotter.shared.AchievementNotification;
import stuffplotter.shared.Notification;

public class Notifier
{
	
	private Account user;
	
	public Notifier()
	{
		
	}
	
	public Notifier(String user)
	{
		final Account result = null;
		AccountServiceAsync accountService = GWT.create(AccountService.class);
		accountService.getAccount(user, new AsyncCallback<Account>(){

			@Override
			public void onFailure(Throwable arg0)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Account arg0)
			{
				Account result = arg0;
			}
			
		});
		this.user = result;

	}
	
	public void addNotification(Achievement ach)
	{
		// Wait till user is not null
		while(user==null)
		{
			
		}
		Notification notification = new AchievementNotification(user.getUserEmail(), ach);
		this.user.addUserNotification(notification);
	}
	
}
