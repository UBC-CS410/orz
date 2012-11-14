package stuffplotter.ui.events;

import java.util.Collections;
import java.util.List;

import stuffplotter.client.AccountService;
import stuffplotter.client.AccountServiceAsync;
import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Account;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Class to display the friends to select to invite to an event.
 */
public class FriendSelectionPanel extends ScrollPanel implements EventSubmittable
{
	private static final int NUMOFCOLUMNS = 3;
	private FlexTable friendList;
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
		this.friendList = new FlexTable();
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
		AccountServiceAsync accountService = GWT.create(AccountService.class);
		accountService.getFriends(userAccount, new AsyncCallback<List<String>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				friendList.removeAllRows();
				friendList.setWidget(0, 0, new Label("Failed to retrieve friends, please" +
						"try again later."));
			}

			@Override
			public void onSuccess(List<String> friends)
			{
				Collections.sort(friends);
				
				int rowCount = 0;
				int colCount = 0;
				
				// for each loop to populate the list of friends to select from
				for(String friend : friends)
				{
					friendList.setWidget(rowCount, colCount, new CheckBox(friend));
					colCount++;
					if(colCount == NUMOFCOLUMNS)
					{
						colCount = 0;
						rowCount++;
					}
				}
			}
		});
		
		this.add(friendList);
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
		return this.friendList;
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
