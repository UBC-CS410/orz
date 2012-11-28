package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class NewsFeedPresenter implements Presenter
{
	public interface NewsFeedView
	{
		/**
		 * Set the user notification data to display in the NewsFeedView.
		 * @pre model != null;
		 * @post true;
		 */
		public void setNewsFeedData(List<NotificationModel> notifmodels, List<AccountModel> friends);
		/**
		 * Retrieve the UserAccountView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the UserAccountView as a widget.
		 */
		public Widget asWidget();

	}

	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final NewsFeedView newsView;
	private Account appUser;
	private List<AccountModel> friendAccounts;
	private List<NotificationModel> notifications;

	public NewsFeedPresenter(ServiceRepository appServices,
			HandlerManager eventBus,
			NewsFeedView display,
			Account appUser)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.newsView = display;
		this.appUser = appUser;
		this.dataBind();
	}


	private void dataBind()
	{
		this.appServices.getAccountService().getFriends(appUser, new AsyncCallback<List<String>>()
				{

			@Override
			public void onSuccess(List<String> result)
			{
				final List<String> friends = result;
				friendAccounts = new ArrayList<AccountModel>();
				appServices.getAccountService().getAccounts(result, new AsyncCallback<Map<String, Account>>()
						{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Map<String, Account> result)
					{
						for(String friend: friends)
						{
							friendAccounts.add(result.get(friend));
						}

						List<Long> notifIDs= new ArrayList<Long>();
						for(AccountModel friend:friendAccounts)
						{
							notifIDs.addAll(friend.getUserNotifications());
						}
						
						appServices.getAccountService().getNotifications(notifIDs, new AsyncCallback<List<NotificationModel>>()
								{

							@Override
							public void onFailure(Throwable caught)
							{
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(List<NotificationModel> result)
							{
								notifications = new ArrayList<NotificationModel>();
								for(NotificationModel not : result)
								{
									if(!doesContain(notifications, not))
										notifications.add(not);
								}
								Collections.sort(notifications, new Comparator<NotificationModel>()
										{

									@Override
									public int compare(NotificationModel o1, NotificationModel o2)
									{
										return o2.getNotificationTime().compareTo(o1.getNotificationTime());
									}

										});
								
								newsView.setNewsFeedData(notifications, friendAccounts);
							}

							private boolean doesContain(List<NotificationModel> notifications, NotificationModel not)
							{
								for(NotificationModel notif: notifications)
									if(notif.getNotificationId().equals(not.getNotificationId()))
										return true;

								return false;
							}
								});



					}
						});

			}

			@Override
			public void onFailure(Throwable caught)
			{

			}
				});

	}


	private void bind()
	{
		this.eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
		{

			@Override
			public void onRefreshPage(RefreshPageEvent event)
			{
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
						dataBind();
					}

						});

			}
		});
	}

	@Override
	public void go(HasWidgets container)
	{
		this.bind();
		//container.add(this.newsView.asWidget());

	}



}
