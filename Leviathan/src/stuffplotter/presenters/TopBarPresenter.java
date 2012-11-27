package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.Notification;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.global.TopBarPanel;
import stuffplotter.views.global.UserNotificationsPopupPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
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

		void setNotificationLabelText(String text);

		HasClickHandlers getNotificationsLabel();

		UserNotificationsPopupPanel getPopUp();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final TopBarView topBarDisplay;
	private Account appUser;
	private List<NotificationModel> notifications;
	
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
		this.notifications = new ArrayList<NotificationModel>();
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
		this.appServices.getAccountService().getNotifications(this.appUser.getUserNotifications(), new AsyncCallback<List<NotificationModel>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						
						
					}

					@Override
					public void onSuccess(List<NotificationModel> result)
					{
						notifications = result;
						topBarDisplay.setNotificationData(notifications);
						
					}
			
				});
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
//				Window.alert("Attempting to Refresh Notification List" +
//						"Window alert in TopBarPresenter");
				//topBarDisplay.setNotificationData(notifications)
				AccountServiceAsync accountService = appServices.getAccountService();
				accountService.getAccount(appUser.getUserEmail(), new AsyncCallback<Account>()
						{

							@Override
							public void onFailure(Throwable caught)
							{
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Account result)
							{
								appUser = result;
								topBarDisplay.setUserData(result);
								
								List<Long> notIds = appUser.getUserNotifications();
								appServices.getAccountService().getNotifications(notIds, new AsyncCallback<List<NotificationModel>>()
										{

											@Override
											public void onFailure(Throwable caught)
											{
												// TODO Auto-generated method stub
												
											}

											@Override
											public void onSuccess(List<NotificationModel> result)
											{								
												for(NotificationModel not : result)
												{
													if(!notifications.contains(not))
														notifications.add(not);
												}
												Collections.sort(notifications, new Comparator<NotificationModel>()
														{

															@Override
															public int compare(NotificationModel o1, NotificationModel o2)
															{
																return o1.getNotificationTime().compareTo(o2.getNotificationTime());
															}
													
														});
												
												int NumberOfNewNotifications = 0;
												for(NotificationModel notif: notifications)
												{
													if(notif.getNewNotification())
														NumberOfNewNotifications++;
												}
												if(NumberOfNewNotifications>1)
													topBarDisplay.setNotificationLabelText("Notifications ("+NumberOfNewNotifications+")");
													else
													topBarDisplay.setNotificationLabelText("Notification ("+NumberOfNewNotifications+")");
												
												topBarDisplay.setNotificationData(notifications);
												System.out.println("I have refreshed btw");
											}
									
										});
							}
					
						});
				

				
				
				
			}
		});
		
		this.topBarDisplay.getNotificationsLabel().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				topBarDisplay.getPopUp().toggleVisibility();		
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
