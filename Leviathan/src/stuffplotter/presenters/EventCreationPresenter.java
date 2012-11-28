package stuffplotter.presenters;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.EventCreationPagePopulator;
import stuffplotter.client.EventCreationPageRetriever;
import stuffplotter.client.EventCreationPageValidator;
import stuffplotter.client.EventCreationPageVisitor;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.EventCreationPagedPresenter.EventCreationPagedView;
import stuffplotter.shared.Event;
import stuffplotter.signals.EventCreatedEvent;
import stuffplotter.views.events.EventSubmittable;
import stuffplotter.views.util.NotificationDialogBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Presenter for the event creation dialog box.
 */
public class EventCreationPresenter implements Presenter
{
	public interface CreateEventView
	{
		/**
		 * Retrieve the current page of the event creation procedure being displayed.
		 * @pre true;
		 * @post true;
		 * @return the current page of the event creation procedure being displayed.
		 */
		public EventSubmittable getCurrentPage();
		
		/**
		 * Retrieve the Cancel "button".
		 * @pre true;
		 * @post true;
		 * @return the Cancel "button".
		 */
		public HasClickHandlers getCancelBtn();
		
		/**
		 * Retrieve the Back "button".
		 * @pre true;
		 * @post true;
		 * @return the Back "button".
		 */
		public HasClickHandlers getBackBtn();
		
		/**
		 * Retrieve the Next "button".
		 * @pre true;
		 * @post true;
		 * @return the Next "button".
		 */
		public HasClickHandlers getNextBtn();
		
		/**
		 * Retrieve the Submit "button".
		 * @pre true;
		 * @post true;
		 * @return the Submit "button".
		 */
		public HasClickHandlers getSubmitBtn();
				
		/**
		 * Close the create event window.
		 * @pre true;
		 * @post true;
		 */
		public void closeDisplay();
		
		/**
		 * Go to the next page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void nextPage();
		
		/**
		 * Go to the previous page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void previousPage();
		
		/**
		 * Retrieve the panel containing the pages in the event creation view. 
		 * @pre true;
		 * @post true;
		 * @return the panel containing the pages in the event creation view.
		 */
		public EventCreationPagedView getPagedView();
		
		/**
		 * Retrieve the panel to place the EventCreationPagedView.
		 * @pre true;
		 * @post true;
		 * @return the panel to place the EventCreationPagedView.
		 */
		public HasWidgets getPagedViewHolder();
		
		/**
		 * Retrieve sends the visitor into the pages of the event creation procedure to carry
		 * out specific tasks.
		 * @pre true;
		 * @post true;
		 * @param eventPageVisitor - the EventCreationPageVisitor to visit each of the event
		 * 							 creation views.
		 */
		public void acceptVisitor(EventCreationPageVisitor eventPageVisitor);
	}
	
	private static final String TASKNAME = "Creating New Event";
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final CreateEventView createEventDialogBox;
	private final AccountModel appUser;
	
	/**
	 * Constructor for the EventCreationPresenter.
	 * @pre appServices != null && eventBus != null && display != null;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the CreateEventView to associate with the EventCreationPresenter.
	 * @param userAccount - the user account that is creating the events.
	 */
	public EventCreationPresenter(ServiceRepository appServices,
								  HandlerManager eventBus,
								  CreateEventView display,
								  AccountModel userAccount)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.createEventDialogBox = display;
		this.appUser = userAccount;
		this.populateDisplay();
	}
	
	/**
	 * Helper method to populate the event creation display with the information for the
	 * user creating the event.
	 * @pre true;
	 * @post true;
	 */
	private void populateDisplay()
	{
		EventCreationPagePopulator visitor = new EventCreationPagePopulator(this.appServices.getAccountService(),
																			this.appUser);
		this.createEventDialogBox.acceptVisitor(visitor);
	}
	
	/**
	 * Helper method to attach all the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.createEventDialogBox.getBackBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				createEventDialogBox.previousPage();
			}	
		});
		
		this.createEventDialogBox.getNextBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				EventCreationPageValidator validator = new EventCreationPageValidator();
				createEventDialogBox.getCurrentPage().accept(validator);
				if(validator.isPageValid())
				{
					createEventDialogBox.nextPage();
				}
			}	
		});
		
		this.createEventDialogBox.getSubmitBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				EventCreationPageRetriever eventInfoRetriever = new EventCreationPageRetriever(appUser.getUserEmail());
				createEventDialogBox.acceptVisitor(eventInfoRetriever);
				Event eventToCreate = new Event(eventInfoRetriever);
				EventServiceAsync eventService = appServices.getEventService();
				eventService.createEvent(eventToCreate, eventInfoRetriever.getSelectedTimeSlots(), new AsyncCallback<Event>()
				{	
					@Override
					public void onSuccess(Event result)
					{
						createEventDialogBox.closeDisplay();
						System.out.println(result.getComments().size());
						new NotificationDialogBox(TASKNAME, "The Event: " + result.getName() +
												  " was created successfully!");
						eventBus.fireEvent(new EventCreatedEvent(result.getId()));
					}
					
					@Override
					public void onFailure(Throwable caught)
					{
						createEventDialogBox.closeDisplay();
						new NotificationDialogBox(TASKNAME, "Unfortunately your event " +
								"failed to be created, please try again later.");
					}
				});
			}
		});
	}
	
	/**
	 * Initialize the CreateEventView.
	 * Note: Don't need to add panel to anything since it is a DialogBox.
	 * @pre true;
	 * @post true;
	 * @param container - just set this to null.
	 */
	@Override
	public void go(HasWidgets container)
	{
		this.bind();
		Presenter presenter = new EventCreationPagedPresenter(this.createEventDialogBox.getPagedView());
		presenter.go(this.createEventDialogBox.getPagedViewHolder());
	}
}
