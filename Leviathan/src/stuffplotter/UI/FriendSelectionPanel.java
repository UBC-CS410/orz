package stuffplotter.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import stuffplotter.client.AccountService;
import stuffplotter.client.AccountServiceAsync;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the friends to select to invite to an event.
 */
public class FriendSelectionPanel extends ScrollPanel implements EventSubmittable
{
	private int numOfColumns = 3;
	private FlexTable friendList;
	final private String panelWidth = "500px";
	final private String panelHeight = "300px";
	
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
					if(colCount == numOfColumns)
					{
						colCount = 0;
						rowCount++;
					}
				}
			}
		});
		
		this.add(friendList);
		this.setSize(panelWidth, panelHeight);
	}
	
	/**
	 * Method to retrieve the submission information from the FlexTable.
	 * @pre event != null;
	 * @post true;
	 * @param event - the Event to have its fields populated with before sending to
	 * 				  the backend.
	 */
	@Override
	public void retrieveSubmission(Event event)
	{
		event.setGuests(this.getSelectedFriends());
	}
	
	/**
	 * Helper method to retrieve the friends selected to invite to an event.
	 * @pre true;
	 * @post true;
	 * @return the list of friends to invite to the event.
	 */
	private List<String> getSelectedFriends()
	{
		List<String> selectedFriends = new ArrayList<String>();
		
		Iterator<Widget> iterator = this.friendList.iterator();
		
		// while loop to retrieve the selected friends
		while(iterator.hasNext())
		{
			CheckBox friend = (CheckBox) iterator.next();
			if(friend.getValue())
			{
				selectedFriends.add(friend.getFormValue());
			}
		}
		
		return selectedFriends;
	}
}
