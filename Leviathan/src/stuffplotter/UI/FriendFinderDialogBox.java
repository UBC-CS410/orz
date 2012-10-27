package stuffplotter.UI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the dialog box for making friend requests.
 */
public class FriendFinderDialogBox extends DialogBox
{
	private FlexTable userList;
	
	/**
	 * Constructor for FriendFinderDialogBox.
	 */
	public FriendFinderDialogBox()
	{
		super();
		initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI for the FriendFinderDialogBox.
	 */
	private void initializeUI()
	{
		userList = new FlexTable();
		VerticalPanel vertPanel = new VerticalPanel();
		HorizontalPanel horPanel = new HorizontalPanel();
		final TextBox friendInput = new TextBox();
		Button findFriend = new Button("Find");
		findFriend.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				userList.removeAllRows();
				List<String> friends = searchDB(friendInput.getValue());
				for(int i = 0; i < friends.size(); i++)
				{
					userList.setText(i, 0, friends.get(i));
					final int rowToRemove = i;
					Button friendRequest = new Button("Send Request");
					friendRequest.addClickHandler(new ClickHandler()
					{
						@Override
						public void onClick(ClickEvent event)
						{
							userList.removeRow(rowToRemove);
						}
					});
					userList.setWidget(i, 1, friendRequest);
				}
			}
		});
		
		horPanel.add(friendInput);
		horPanel.add(findFriend);
		vertPanel.add(horPanel);
		vertPanel.add(userList);
		this.add(vertPanel);
	}
	
	/**
	 * Helper method to search the database for a user containing the given name.
	 * @param friendName - the name to search for.
	 * @return List<String> of the user(s) containing the provided name.
	 */
	private List<String> searchDB(String friendName)
	{
		List<String> usersFound = new ArrayList<String>();
		
		// temp "DB" of user names
		String[] names = {"Richard", "Matthew", "Farez", "Allen", "Bill", "Cookie",
				"Dorothy", "Eddie", "Frank", "George", "etc"};
		for (String name : names)
		{
			if(name.contains(friendName))
			{
				usersFound.add(name);
			}
		}
		
		return usersFound;
	}
}
