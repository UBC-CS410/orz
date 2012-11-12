package stuffplotter.ui;

import stuffplotter.shared.Account;
import stuffplotter.ui.events.EventCreationDialogBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the events page view.
 */
public class EventsPagePanel extends SimplePanel
{
	// remove account from here to model MVP pattern
	private Account account;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPagePanel(Account account)
	{
		super();
		this.account = account;
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		Button createEventBtn = new Button("Create Event");
		createEventBtn.addClickHandler(new ClickHandler()
		{
			EventCreationDialogBox eventCreation = null;
			@Override
			public void onClick(ClickEvent event)
			{
				 eventCreation = new EventCreationDialogBox(account);
			}
		});
		this.add(createEventBtn);
	}
}
