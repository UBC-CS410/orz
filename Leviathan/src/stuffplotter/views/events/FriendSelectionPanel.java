package stuffplotter.views.events;

import java.util.Collections;
import java.util.List;

import stuffplotter.client.AccountService;
import stuffplotter.client.AccountServiceAsync;
import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Account;
import stuffplotter.views.util.ScrollDisplayPanel;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the friends to select to invite to an event.
 */
public class FriendSelectionPanel extends SimplePanel implements EventSubmittable
{
	private static final int NUMOFCOLUMNS = 3;
	private ScrollDisplayPanel friendDisplayer;
	private static final String PANELWIDTH = "500px";
	private static final String PANELHEIGHT = "300px";
	
	/**
	 * Constructor for FriendSelectionPanel.
	 * @pre userAccount != null;
	 * @post this.isVisible() == true;
	 */
	public FriendSelectionPanel(Account userAccount)
	{
		super();
		this.initializeUI(userAccount);
	}

	/**
	 * Helper method to add the friends to the FriendSelectionPanel.
	 * @pre userAccount != null; 
	 * @post true;
	 * @param userAccount
	 */
	private void initializeUI(Account userAccount)
	{	
		this.friendDisplayer = new ScrollDisplayPanel(NUMOFCOLUMNS);
		this.friendDisplayer.setSize(PANELWIDTH, PANELHEIGHT);
		
		AccountServiceAsync accountService = GWT.create(AccountService.class);
		accountService.getFriends(userAccount, new AsyncCallback<List<String>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				friendDisplayer.clearDisplay();
				friendDisplayer.addElement(new Label("Failed to retrieve friends, please" +
						"try again later."));
			}

			@Override
			public void onSuccess(List<String> friends)
			{
				Collections.sort(friends);
				
				// for each loop to populate the list of friends to select from
				for(String friend : friends)
				{
					friendDisplayer.addElement(new CheckBox(friend));
				}
			}
		});
		
		this.add(friendDisplayer);
		this.setSize(PANELWIDTH, PANELHEIGHT);
	}

	/**
	 * Helper method to retrieve the friends selected to invite to an event.
	 * @pre true;
	 * @post true;
	 * @return the list of friends (populated in the UI) to invite to the event.
	 */
	public FlexTable getFriendList()
	{
		return this.friendDisplayer.getDisplayer();
	}
	
	/**
	 * Method to accept an EventCreationPageVisitor and have it perform certain tasks.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	@Override
	public void accept(EventCreationPageVisitor eventVisitor)
	{
		eventVisitor.visit(this);
	}
}
