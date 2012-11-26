package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.server.AchievementChecker;
import stuffplotter.server.LevelUpdater;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.friends.FriendPanel;
import stuffplotter.views.friends.FriendPanelView;
import stuffplotter.views.friends.PendingFriendPanel;


/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter implements Presenter
{
	public interface FriendsView
	{
		public HasClickHandlers getAddFriendBtn();
		public HasClickHandlers getSearchFriendsBtn();
		public HasAllFocusHandlers getFriendTextBox();
		public String getFriendBoxText();
		public void clearFriendBoxText();


		/**
		 * Retrieve the views containing the 
		 * @pre true;
		 * @post true;
		 * @return the list of friend views in the display.
		 */
		public List<FriendPanelView> getFriendPanels();

		/**
		 * Retrieve the views containing the 
		 * @pre true;
		 * @post true;
		 * @return the list of friend views in the display.
		 */
		public List<PendingFriendPanel> getPendingFriendPanels();

		/**
		 * Set the list of friends to display.
		 * @pre model != null;
		 * @post true;
		 * @param models - the list of friends to display. 
		 */
		public void setFriendData(List<AccountModel> models);

		/**
		 * Set the list of friends to display.
		 * @pre model != null;
		 * @post true;
		 * @param models - the list of friends to display. 
		 */
		public void setPendingData(List<AccountModel> models);

		/**
		 * Retrieve the FriendsView as a Widget.
		 * @pre true;
		 * @post true;
		 * @return the FriendsView as a Widget.
		 */
		public Widget asWidget();

	}

	private Account appUser;
	private AccountStatistic appStats;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final FriendsView friendsView;

	/**
	 * Constructor for the FriendsPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the FriendsView to associate with the FriendsPagePresenter.
	 */
	public FriendsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, FriendsView display, Account user)
	{
		this.appUser = user;
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.friendsView = display;
		this.dataBindFriends();

	}

	/**
	 * Helper method to data bind the Friends to the view.
	 * @pre true;
	 * @post true;
	 */

	private void dataBindFriends()
	{
		this.appServices.getStatsService().getStats(this.appUser.getUserEmail(),new AsyncCallback<AccountStatistic>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(AccountStatistic result)
					{
						appStats = result;
					}
			
				});
		
		
		
		final List<String> userFriends = appUser.getUserFriends();
		final List<String> pendingUsers = appUser.getPendingFriends();
		
		AccountServiceAsync accountService = appServices.getAccountService();
		accountService.getAccounts(userFriends, new AsyncCallback<Map<String, Account>>(){

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Failed to get Friend User Accounts");

			}

			@Override
			public void onSuccess(Map<String, Account> result)
			{
				List<AccountModel> userAccounts = new ArrayList<AccountModel>();
				for(String friend :userFriends)
				{
					userAccounts.add(result.get(friend));
				}

				Collections.sort(userAccounts, new Account());
				friendsView.setFriendData(userAccounts);
				bindFriendPanels();

			}});
		accountService.getAccounts(pendingUsers, new AsyncCallback<Map<String, Account>>(){

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Failed to get Pending User Accounts");

			}

			@Override
			public void onSuccess(Map<String, Account> result)
			{
				List<AccountModel> userAccounts = new ArrayList<AccountModel>();
				for(String friend :pendingUsers)
				{
					userAccounts.add(result.get(friend));
				}

				Collections.sort(userAccounts, new Account());
				friendsView.setPendingData(userAccounts);
				bindPendingFriendPanels();
			}

		});

	}



	/**
	 * Bind friends view components to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		this.eventBus.addHandler(RefreshPageEvent.TYPE, new RefreshPageEventHandler()
		{
			@Override
			public void onRefreshPage(RefreshPageEvent event)
			{
				// TODO Auto-generated method stub
				// place code here to refresh page each time a fresh event is sent
			}	
		});


		this.friendsView.getAddFriendBtn().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				String friendEmail = friendsView.getFriendBoxText();
				if(friendEmail.length()==0)
				{
					Window.alert("This field cannot be blank!");
				}	
				else if(friendEmail.contains(" "))
				{
					Window.alert("This field cannot contain and spaces!");
				}

				else{
					if(!friendEmail.contains("@gmail.com"))
						friendEmail = friendEmail+"@gmail.com";
					appServices.getAccountService().addFriend(appUser, friendEmail, new AsyncCallback<Void>(){
						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("Something went wrong when adding a friend. Please contact support.");
						}

						@Override
						public void onSuccess(Void result)
						{
							Window.alert("A notification has been sent to "+friendsView.getFriendBoxText()+"! Please await their confirmation. =D");
							friendsView.clearFriendBoxText();
						}
					});
				}

			}
		});

		friendsView.getFriendTextBox().addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event)
			{
				if(friendsView.getFriendBoxText().contains(" "))
					friendsView.clearFriendBoxText();
			}});

		



	}

	private void bindFriendPanels()
	{
		List<FriendPanelView> friendsPanels = friendsView.getFriendPanels();
		for(FriendPanelView panel: friendsPanels)
		{
			final String friendEmail = panel.getEmail();
			final String friendName = panel.getName();
			panel.getRemoveBtn().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event)
				{
					if(Window.confirm("Are you sure you want to remove this person from your Friends List (Don't worry, we won't tell them)?"))
					{
						AccountServiceAsync accountService = appServices.getAccountService();
						accountService.removeFriend(appUser, friendEmail , new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught)
							{

							}

							@Override
							public void onSuccess(Void result)
							{
								
							}

						});
					}


				}

			});
			panel.getViewBtn().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event)
				{
					// TODO Auto-generated method stub

				}

			});
		}
	}

	private void bindPendingFriendPanels()
	{
		List<PendingFriendPanel> pendingFriendsPanels = friendsView.getPendingFriendPanels();
		for(PendingFriendPanel panel: pendingFriendsPanels)
		{
			final String friendEmail = panel.getEmail();
			final String friendName = panel.getName();
			panel.getConfirmBtn().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event)
				{
					AccountServiceAsync accountService = appServices.getAccountService();
					accountService.confirmFriendReq(appUser, friendEmail, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("Friend was not successfully added...");
						}

						@Override
						public void onSuccess(Void result)
						{
							appStats.incrementFriends();
							Window.alert(friendName+" successfully added to your friends list!!");
							appStats.accept(new LevelUpdater().madeFriend());
							appStats.accept(new AchievementChecker());
							
							appServices.getStatsService().getStats(friendEmail,new AsyncCallback<AccountStatistic>()
									{

										@Override
										public void onFailure(Throwable caught)
										{
											// TODO Auto-generated method stub
											
										}

										@Override
										public void onSuccess(AccountStatistic result)
										{
											result.accept(new LevelUpdater().madeFriend());
										}
								
									});
							


						}

					});

					
				}});
			panel.getDenyBtn().addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event)
				{
					AccountServiceAsync accountService = appServices.getAccountService();
					if(Window.confirm("Are you sure you want to deny this friend request (Don't worry, we won't tell them)?"))
					{
						accountService.declineFriendReq(appUser, friendEmail, new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught)
							{
								Window.alert("Something went wrong while denying a friend request. Please contact support.");

							}

							@Override
							public void onSuccess(Void result)
							{


							}

						});
					}
					
				}});
		}
	}




	/**
	 * Present the friends view
	 * @pre true;
	 * @post this.homeView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.friendsView.asWidget());
	}




	public void doRefresh()
	{

	}
}
