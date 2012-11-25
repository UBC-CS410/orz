package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.global.TopBarPanel;

import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopBarPresenter implements Presenter
{
	public interface TopBarView
	{
		/**
		 * Sets the user data for the TopBarView.
		 * @pre true;
		 * @post true;
		 * @param userAccount - the user account to display in the TopBarView.
		 */
		public void setUserData(AccountModel userAccount);
		
		/**
		 * Sets the notification data to display.
		 * @pre true;
		 * @post true;
		 * @param notifications - the notifications to display.
		 */
		public void setNotificationData(List<NotificationModel> notifications);
		
		/**
		 * Retrieve the TopBarView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the TopBarView as a widget.
		 */
		public Widget asWidget();
		
		public void setNotificationLabelText(String text);
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final TopBarView topBarDisplay;
	private final Account appUser;
	
	/**
	 * Constructor for the TopBarPresenter.
	 * @pre true;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the app.
	 * @param eventBus - the event bus for the app.
	 * @param display - the TopBarView to associate with the TopBarPresenter.
	 * @param userAccount - the user account to display.
	 */
	public TopBarPresenter(ServiceRepository appServices,
						   HandlerManager eventBus,
						   TopBarView display,
						   Account userAccount)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.topBarDisplay = display;
		this.appUser = userAccount;
		this.dataBindAccount();
	}
	
	/**
	 * Helper method to data bind the account to the view.
	 * @pre true;
	 * @post true;
	 */
	private void dataBindAccount()
	{
		this.topBarDisplay.setUserData(this.appUser);
	}
	
	/**
	 * Bind top bar view components to handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
		{
			@Override
			public void onRefreshPage(RefreshPageEvent event)
			{
				// TO DO: Make backend call and repopulate the notifications panel
				Window.alert("Attempting to Refresh Notification List" +
						"Window alert in TopBarPresenter");
				//topBarDisplay.setNotificationData(notifications)
			}
		});
		
		AccountServiceAsync accountService = appServices.getAccountService();
		List<Long> notIds = appUser.getUserNotifications();
		
		accountService.getNotifications(notIds, new AsyncCallback<List<NotificationModel>>(){
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Fail to get Notifications");
				
			}

			@Override
			public void onSuccess(List<NotificationModel> result)
			{
				topBarDisplay.setNotificationData(result);
				int NumberOfNewNotifications = 0;
				for(NotificationModel notif: result)
				{
					if(notif.getNewNotification())
						NumberOfNewNotifications++;
				}
				if(NumberOfNewNotifications>1)
					topBarDisplay.setNotificationLabelText("Notifications ("+NumberOfNewNotifications+")");
				else
					topBarDisplay.setNotificationLabelText("Notification ("+NumberOfNewNotifications+")");
			}
		});
		
		
		
	}
	
	@Override
	public void go(final HasWidgets container)
	{
		this.bind();
		container.add(topBarDisplay.asWidget());
	}
}
