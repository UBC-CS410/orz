package stuffplotter.ui;

import stuffplotter.shared.Account;
import stuffplotter.ui.events.EventCreationDialogBox;
import stuffplotter.ui.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the events page view.
 */
public class EventsPagePanel extends HorizontalPanel
{
	// remove account from here to model MVP pattern
	private Account account;
	private ScrollDisplayPanel eventsDisplay;
	
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
		VerticalPanel buttonHolder = new VerticalPanel();
		this.eventsDisplay = new ScrollDisplayPanel();
		this.eventsDisplay.addElement(new Label("Display of Events"));
		this.eventsDisplay.addElement(new Label("Display of Events"));
		this.eventsDisplay.addElement(new Label("Display of Events"));
		this.eventsDisplay.addElement(new Label("Display of Events"));
		this.eventsDisplay.addElement(new Label("Display of Events"));
		this.eventsDisplay.addElement(new Label("Display of Events"));
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
		buttonHolder.add(createEventBtn);
		buttonHolder.add(new EventsBrowserPanel());
		this.add(buttonHolder);
		this.add(eventsDisplay);
	}
}
