package stuffplotter.views.events;

import stuffplotter.client.EventCreationPageRetriever;
import stuffplotter.client.services.EventService;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.misc.CloseClickHandler;
import stuffplotter.misc.EventCreationPageVisitor;
import stuffplotter.misc.EventSubmittable;
import stuffplotter.presenters.EventCreationPresenter.CreateEventView;
import stuffplotter.shared.Event;
import stuffplotter.views.util.NotificationDialogBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the window for creating events.
 */
public class EventCreationView extends DialogBox implements CreateEventView
{
	private static final String NEXTBUTTONTEXT = "Next";
	private static final String SUBMITBUTTONTEXT = "Submit";
	private static final String TASKNAME = "Creating New Event"; 
	
	private EventCreationPagedPanel eventPages;
	private Button backBtn;
	private Button nextBtn;
	private Button cancelBtn;
	private HandlerRegistration nextAction;
	private HandlerRegistration submitAction;
	private EventCreationPageRetriever eventInfoRetriever;
	private Event eventToCreate;
	
	/**
	 * Constructor for the EventCreationDialogBox class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationView(final String creatorEmail)
	{
		super();
		
		VerticalPanel vertPanel = new VerticalPanel();
		HorizontalPanel btnHolder = new HorizontalPanel();
		this.eventInfoRetriever = new EventCreationPageRetriever(creatorEmail);
		this.eventPages = new EventCreationPagedPanel();
		vertPanel.add(this.eventPages);
		this.initializeButtons();
		btnHolder.add(backBtn);
		btnHolder.add(nextBtn);
		vertPanel.add(btnHolder);
		this.initializeCancelBtn(btnHolder);
		this.add(vertPanel);
		this.center();
		this.setText(TASKNAME);
		this.setGlassEnabled(true);
	}
	
	/**
	 * Method to retrieve the event information using an EventCreationPageVisitor.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to retrieve the event information.
	 */
	public void retrieveEventInfo(EventCreationPageVisitor eventVisitor)
	{
		this.eventPages.retrieveEventInfo(eventVisitor);
	}
	
	/**
	 * Helper method to initialize the cancel button for the window, adds the
	 * button to the panel passed to it.
	 * @pre panel != null;
	 * @post true;
	 * @param panel - the panel to add the close button to.
	 */
	private void initializeCancelBtn(Panel panel)
	{
		cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new CloseClickHandler(this));
		panel.add(cancelBtn);
	}
	
	/**
	 * Helper method to initialize the next and back buttons for the window.
	 * @pre true;
	 * @post true;
	 */
	private void initializeButtons()
	{
		backBtn = new Button("Back");
		nextBtn = new Button();
		this.backBtn.setEnabled(false);
		this.setAsNextButton();
	}
	
	/**
	 * Helper method to set nextBtn as the next button.
	 * @pre true;
	 * @post true;
	 */
	private void setAsNextButton()
	{
		this.nextBtn.setText(NEXTBUTTONTEXT);
		nextAction = this.nextBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventPages.nextPage();
				backBtn.setEnabled(true);
				if(!eventPages.hasNextPage())
				{
					nextAction.removeHandler();
					setAsSubmitButton();
				}
			}	
		});
	}
	
	/**
	 * Helper method to set nextBtn as the submit button.
	 * @pre true;
	 * @post true;
	 */
	private void setAsSubmitButton()
	{
		this.nextBtn.setText(SUBMITBUTTONTEXT);
		submitAction = this.nextBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				retrieveEventInfo(eventInfoRetriever);
				eventToCreate = new Event(eventInfoRetriever);
				EventServiceAsync eventService = GWT.create(EventService.class);
				eventService.createEvent(eventToCreate, eventInfoRetriever.getSelectedTimeSlots(), new AsyncCallback<Event>()
				{	
					@Override
					public void onSuccess(Event result)
					{
						hide();
						System.out.println(result.getComments().size());
						new NotificationDialogBox(TASKNAME, "The Event: " + result.getName() +
												  " was created successfully!");
					}
					
					@Override
					public void onFailure(Throwable caught)
					{
						hide();
						new NotificationDialogBox(TASKNAME, "Unfortunately your event " +
								"failed to be created, please try again later.");
					}
				});
			}	
		});
	}

	@Override
	public EventSubmittable getCurrentPage()
	{
		return this.eventPages.getCurrentPage();
	}

	@Override
	public HasClickHandlers getCancelBtn()
	{
		return this.cancelBtn;
	}

	@Override
	public HasClickHandlers getBackBtn()
	{
		return this.backBtn;
	}
	
	@Override
	public HasClickHandlers getNextBtn()
	{
		return this.nextBtn;
	}

	@Override
	public void nextPage()
	{
		this.eventPages.nextPage();
	}

	@Override
	public void previousPage()
	{
		this.eventPages.previousPage();
	}
	
	@Override
	public boolean hasNextPage()
	{
		return this.eventPages.hasNextPage();
	}
	
	@Override
	public boolean hasPreviousPage()
	{
		return this.eventPages.hasPreviousPage();
	}
}
