package stuffplotter.presenters;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.views.friends.FriendPanel;


/**
 * Class for the Friends Page presenter.
 */
public class FriendsPagePresenter implements Presenter
{
	public interface FriendsView
	{
		public HasClickHandlers getAddFriendBtn();
		public HasClickHandlers getSearchFriendsBtn();
		public String getFriendBoxText();
		public void clearFriendBoxText();
		public void addPendingUsers(String pendUser, Account appUser);
		void addFriendPanel(FriendPanel friendPan);
		//public FriendList getFriendDisplay() create presenter for this
		/**
		 * Retrieve the FriendsView as a Widget.
		 * @pre true;
		 * @post true;
		 * @return the FriendsView as a Widget.
		 */
		public Widget asWidget();
		
		
	}
	
	private final Account appUser;
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
				appServices.getAccountService().addFriend(appUser, friendsView.getFriendBoxText(), new AsyncCallback<Void>(){
					@Override
					public void onFailure(Throwable caught)
					{
					}

					@Override
					public void onSuccess(Void result)
					{
						friendsView.clearFriendBoxText();
					}
				});
			}
		});
		
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
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Account result)
						{
							Account friend = result;
							FriendPanel friendPan = new FriendPanel(friend.getUserEmail(), friend.getUserFullName(), friend.getUserTitle(), friend.getUserProfilePicture());
							friendsView.addFriendPanel(friendPan);
						}});
		}
		
	}

	
	private void fetchPendingFriends()
	{
		AccountServiceAsync accountService = appServices.getAccountService();
		accountService.getPendingFriends(appUser, new AsyncCallback<List<String>>(){

			@Override
			public void onFailure(Throwable caught)
			{
				
				
			}

			@Override
			public void onSuccess(List<String> result)
			{
				for(String pendUser: result)
				{
					friendsView.addPendingUsers(pendUser, appUser);
				}
			}
			
		});
	}
}
