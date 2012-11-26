package stuffplotter.presenters;

import java.util.List;

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

import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.views.friends.FriendPanel;
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
		public void addPendingUsers(PendingFriendPanel pendPan);
		void addFriendPanel(FriendPanel friendPan);

		/**
		 * Retrieve the FriendsView as a Widget.
		 * @pre true;
		 * @post true;
		 * @return the FriendsView as a Widget.
		 */
		public Widget asWidget();


	}

	private Account appUser;
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

	}



	/**
	 * Bind friends view components to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		friendsView.getAddFriendBtn().addClickHandler(new ClickHandler(){

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

		fetchPendingFriends();
		fetchFriends();

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

	private void fetchFriends()
	{
		List<String> userFriends = appUser.getUserFriends();
		AccountServiceAsync accountService = appServices.getAccountService();
		for(String friend: userFriends)
		{
			accountService.getAccount(friend,  new AsyncCallback<Account>(
					){

				@Override
				public void onFailure(Throwable caught)
				{

				}

				@Override
				public void onSuccess(Account result)
				{
					final Account friend = result;
					FriendPanel friendPan = new FriendPanel(friend.getUserEmail(), friend.getUserFullName(), friend.getUserTitle(), friend.getUserProfilePicture());
					friendPan.getViewProfileButton().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event)
						{
							// TODO Auto-generated method stub							
						}
						
					});
					
					friendPan.getRemoveFriendButton().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event)
						{
							if(Window.confirm("Are you sure you want to remove this person from your Friends List (Don't worry, we won't tell them)?"))
							{
								AccountServiceAsync accountService = appServices.getAccountService();
								accountService.removeFriend(appUser, friend.getUserEmail(), new AsyncCallback<Void>(){

									@Override
									public void onFailure(Throwable caught)
									{
										
									}

									@Override
									public void onSuccess(Void result)
									{
										Window.alert(friend.getUserFullName()+" has successfully been removed.");
									}
									
								});
							}

							
						}
						
					});
					friendsView.addFriendPanel(friendPan);
				}});
		}

	}


	private void fetchPendingFriends()
	{
		List<String> pendingFriends = appUser.getPendingFriends();
		AccountServiceAsync accountService = appServices.getAccountService();
		for(String friend: pendingFriends)
		{
			accountService.getAccount(friend, new AsyncCallback<Account>(){

				@Override
				public void onFailure(Throwable caught)
				{

				}

				@Override
				public void onSuccess(Account result)
				{
					final Account friend = result;
					PendingFriendPanel pendPanel = new PendingFriendPanel(friend.getUserEmail(), friend.getUserFullName(), friend.getUserProfilePicture());
					pendPanel.getConfirmButton().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event)
						{
							AccountServiceAsync accountService = appServices.getAccountService();
							accountService.confirmFriendReq(appUser, friend.getUserEmail(), new AsyncCallback<Void>(){

								@Override
								public void onFailure(Throwable caught)
								{
									Window.alert("Friend was not successfully added...");
								}

								@Override
								public void onSuccess(Void result)
								{
									Window.alert("Friend successfully added!!");
									
				
								}

							});

						}

					});
					
					pendPanel.getDenyButton().addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event)
						{
							AccountServiceAsync accountService = appServices.getAccountService();
							if(Window.confirm("Are you sure you want to deny this friend request (Don't worry, we won't tell them)?"))
							{
								accountService.declineFriendReq(appUser, friend.getUserEmail(), new AsyncCallback<Void>(){

									@Override
									public void onFailure(Throwable caught)
									{
										
										
									}

									@Override
									public void onSuccess(Void result)
									{
										doRefresh();
										
									}
									
								});
							}
			
							
						}
						
					});
					friendsView.addPendingUsers(pendPanel);
					

				}

			});
		}

	}
	
	public void doRefresh()
	{
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
					}
					
				});
				Window.alert("I refreshed");
			}
			
		});
	}
}
