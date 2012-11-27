package stuffplotter.presenters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.EventCreationPagePopulator;
import stuffplotter.client.EventCreationPageRetriever;
import stuffplotter.client.EventCreationPageValidator;
import stuffplotter.client.GoogleCalendar;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Event;
import stuffplotter.signals.EventCreatedEvent;
import stuffplotter.views.events.EventCreationPageVisitor;
import stuffplotter.views.events.EventSubmittable;
import stuffplotter.views.util.NotificationDialogBox;

import com.google.api.gwt.client.OAuth2Login;
import com.google.api.gwt.services.calendar.shared.Calendar;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarAuthScope;
import com.google.api.gwt.services.calendar.shared.Calendar.CalendarListContext.ListRequest.MinAccessRole;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext.ListRequest;
import com.google.api.gwt.services.calendar.shared.model.CalendarList;
import com.google.api.gwt.services.calendar.shared.model.Events;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.requestfactory.shared.Receiver;

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
		 * Retrieve the date picker in the display.
		 * @pre true;
		 * @post true;
		 * @return the date picker in the display.
		 */
		public HasValueChangeHandlers<Date> getCalendar();
		
		/**
		 * Populate the times on the time sheet panel.
		 * @pre shownDate != null && conflictDates != null;
		 * @post true;
		 * @param shownDate - the day to display.
		 * @param conflictDates - the time slots that already have events going on.
		 */
		public void populateTimeSheet(Date shownDate, List<Date> conflictDates );
		
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
		
		this.createEventDialogBox.getCalendar().addValueChangeHandler(new ValueChangeHandler<Date>()
		{
			@Override
			public void onValueChange(final ValueChangeEvent<Date> event)
			{
				// add google calendar here to get list of conflicts
				GoogleCalendar  calendar = new GoogleCalendar();
				final Calendar googleCalendar = calendar.getCalendar();
				
				OAuth2Login.get().authorize(GoogleCalendar.CLIENT_ID, CalendarAuthScope.CALENDAR, new Callback<Void, Exception>()
				{
					@Override
					public void onFailure(Exception reason)
					{
						// REMOVE AFTER TESTING DONE
						Window.alert(reason.getMessage());
					}
					
					@Override
					public void onSuccess(Void result)
					{
						googleCalendar.calendarList().list().setMinAccessRole(MinAccessRole.OWNER).fire(new Receiver<CalendarList>()
						{
							@Override
							public void onSuccess(CalendarList response) 
							{
								String calendarID = response.getItems().get(0).getId();
								ListRequest calRequest = googleCalendar.events().list(calendarID);
								calRequest.fire(new Receiver<Events>()
								{
									@Override
									public void onSuccess(Events response)
									{
										String result = "Events Found: ";
										List<com.google.api.gwt.services.calendar.shared.model.Event> events = response.getItems();
										if(events != null)
										{
											for(com.google.api.gwt.services.calendar.shared.model.Event event : events)
											{
												result += " " + event.getCreated();
											}
										}
										
										// add google calendar here to get list of conflicts
										createEventDialogBox.populateTimeSheet(event.getValue(), new ArrayList<Date>());
									
										Window.alert(result);
									}
								});
							}
						});
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
	}
}
