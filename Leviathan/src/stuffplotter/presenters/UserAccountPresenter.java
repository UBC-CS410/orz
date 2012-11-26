package stuffplotter.presenters;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Presenter for the user account information display.
 */
public class UserAccountPresenter implements Presenter
{
	public interface UserAccountView
	{
		/**
		 * Set the user account data to display in the UserAccountView.
		 * @pre model != null;
		 * @post true;
		 */
		public void setUserData(AccountModel model);

		/**
		 * Retrieve the UserAccountView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the UserAccountView as a widget.
		 */
		public Widget asWidget();
		
		/**
		 * Retrieves the Edit Button as a widget
		 * @pre true;
		 * @post true;
		 * @return the Edit Button as a widget.
		 */
		public HasClickHandlers getEditButton();
		
		/**
		 * Retrieves the Save Button as a widget
		 * @pre true;
		 * @post true;
		 * @return the Save Button as a widget.
		 */
		public HasClickHandlers getSaveButton();
		
		/**
		 * Retrieves the Cancel Button as a widget
		 * @pre true;
		 * @post true;
		 * @return the Cancel Button as a widget.
		 */
		public HasClickHandlers getCancelButton();
		
		/**
		 * Toggles edit mode on
		 * @pre true;
		 * @post true;
		 */
		public void enableEditMode();
		
		/**
		 * Toggles edit mode off
		 * @pre true;
		 * @post true;
		 */
		public void disableEditMode();
		
		/**
		 * Retrieves the selected Title
		 * @pre true;
		 * @post true;
		 * @return The selected title
		 */
		public String getTitleListBox();
		
		/**
		 * Retrieves the selected Phone number
		 * @pre true;
		 * @post true;
		 * @return The selected Phone number
		 */
		public String getPhoneBox();
		
		/**
		 * Retrieves the selected Age
		 * @pre true;
		 * @post true;
		 * @return The selected Age
		 */
		public String getAgeBox();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final UserAccountView userAccountView;
	private final Account appUser;
	
	/**
	 * Constructor for the UserAccountPresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && appUser != null;
	 * @post true;
	 * @param appServices - the mapped services.
	 * @param eventBus - the global event bus.
	 * @param display - the view to present.
	 * @param user - the current user.
	 */
	public UserAccountPresenter(ServiceRepository appServices,
								HandlerManager eventBus,
								UserAccountView display,
								Account appUser)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.userAccountView = display;
		this.appUser = appUser;
		this.dataBindAccount();
	}
	
	/**
	 * Helper method to data bind the account to the view.
	 * @pre true;
	 * @post true;
	 */
	private void dataBindAccount()
	{
		this.userAccountView.setUserData(this.appUser);
	}
	
	private void bind()
	{
		userAccountView.getEditButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				userAccountView.enableEditMode();
				
			}
			
		});
		
		userAccountView.getSaveButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				appUser.setTitle(userAccountView.getTitleListBox());
				appUser.setUserPhone(userAccountView.getPhoneBox());
				appUser.setUserAge(userAccountView.getAgeBox());
				AccountServiceAsync accountService = appServices.getAccountService();
				accountService.saveAccount(appUser, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result)
					{
						userAccountView.disableEditMode();
					}
					
				});
				
				
			}
			
		});
		
		userAccountView.getCancelButton().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				userAccountView.disableEditMode();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.userAccountView.asWidget());
	}


}
