package stuffplotter.views;

import stuffplotter.presenters.EventsPagePresenter.EventsView;
import stuffplotter.views.events.EventCreationDialogBox;
import stuffplotter.views.events.EventsBrowserPanel;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the events page view.
 */
public class EventsPageView extends HorizontalPanel implements EventsView
{
	private ScrollDisplayPanel eventsDisplay;
	private EventCreationDialogBox eventCreation;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
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

			@Override
			public void onClick(ClickEvent event)
			{
				 eventCreation = new EventCreationDialogBox();
			}
		});
		buttonHolder.add(createEventBtn);
		buttonHolder.add(new EventsBrowserPanel());
		this.add(buttonHolder);
		this.add(eventsDisplay);
	}

	@Override
	public HasClickHandlers getCreateEventBtn()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getCurrentEventsBtn()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HasClickHandlers getPastEventsBtn()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
