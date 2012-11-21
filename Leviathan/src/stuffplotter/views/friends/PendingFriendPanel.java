package stuffplotter.views.friends;

import stuffplotter.client.services.AccountService;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.shared.Account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class PendingFriendPanel extends HorizontalPanel
{
	private Label pendingUser;
	private Button confirmButton;
	public PendingFriendPanel(final String pendUser, final Account appUser)
	{
		final AccountServiceAsync accountService = GWT.create(AccountService.class);
		this.pendingUser = new Label(pendUser);
		this.confirmButton = new Button("Confirm");
		confirmButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event)
			{
				accountService.confirmFriendReq(appUser, pendUser, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Friend was not successfully added...");
					}

					@Override
					public void onSuccess(Void result)
					{
						Window.alert("Friend successfully added!!");
						confirmButton.setVisible(false);
					}
					
				});
				
			}
			
		});
		this.add(pendingUser);
		this.add(confirmButton);
	}

}
