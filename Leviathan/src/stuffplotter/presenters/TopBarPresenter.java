package stuffplotter.presenters;

import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.global.UserNotificationsPopupPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class TopBarPresenter implements Presenter
{
	public interface TopBarView
	{
		/**
		 * Returns the Notification Label
		 * @pre true;
		 * @post true; 
		 * @return returns the NotificationLabel
		 */
		public HasClickHandlers getNotificationsLabel();
		
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
		
		/**
		 * Set the notifications to display in the notification window.
		 * @pre true;
		 * @post true;
		 * @param text
		 */
		public void setNotificationLabelText(String text);
		
		public UserNotificationsPopupPanel getPopUp();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final TopBarView topBarDisplay;
	private Account appUser;
	
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
		this.topBarDisplay.getNotificationsLabel().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				topBarDisplay.getPopUp().toggleVisibility();
				topBarDisplay.getPopUp().setPopupPosition(((UIObject) topBarDisplay.getNotificationsLabel()).getAbsoluteLeft(), ((UIObject) topBarDisplay.getNotificationsLabel()).getAbsoluteTop() + 20);
					
				readNotifications();
				
			}

			/**
			 * Help method that reads through all the notifications
			 * @pre
			 * @post
			 */
			private void readNotifications()
			{
				List<Long> notIds = appUser.getUserNotifications();
				AccountServiceAsync accountService = appServices.getAccountService();
				accountService.readNotifications(notIds, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught)
					{
						
					}

					@Override
					public void onSuccess(Void result)
					{
						fetchNotifications();
						eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
						{
							@Override
							public void onRefreshPage(RefreshPageEvent event)
							{

							}
						});
					}
					
				});
				
				
			}
		});
		
		this.eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
		{
			@Override
			public void onRefreshPage(RefreshPageEvent event)
			{
				AccountServiceAsync accountService = appServices.getAccountService();
				accountService.getAccount(appUser.getUserEmail(), new AsyncCallback<Account>(){

					@Override
					public void onFailure(Throwable caught)
					{

						
					}

					@Override
					public void onSuccess(Account result)
					{
						appUser = result;
						fetchNotifications();
					}
					
				});
				
			}
		});
		
		fetchNotifications();
		
		
		
	}

	/**
	 * Helper functions to fetch notifications
	 * @pre true
	 * @post true
	 */
	private void fetchNotifications()
	{
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
