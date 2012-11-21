package stuffplotter.server;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.client.services.AccountService;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.shared.Achievement;
import stuffplotter.shared.AchievementNotification;
import stuffplotter.shared.Notification;

public class Notifier
{
	
	AccountServiceAsync accountService = GWT.create(AccountService.class);
	
	
	public void addNotification(String user, Achievement ach)
	{
		Notification notification = new AchievementNotification(ach, user);
		accountService.addNotification(user, notification, new AsyncCallback<Void>(){

			@Override
			public void onFailure(Throwable caught)
			{
				
				
			}

			@Override
			public void onSuccess(Void result)
			{
				
				
			}}
			);
		
	}
	
}
