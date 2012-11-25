package stuffplotter.presenters;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.rpc.AsyncCallback;

import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Status;
import stuffplotter.signals.EventCreatedEvent;
import stuffplotter.signals.EventCreatedEventHandler;
import stuffplotter.signals.RefreshPageEvent;
import stuffplotter.signals.RefreshPageEventHandler;
import stuffplotter.signals.SubmittedAvailabilitiesEvent;
import stuffplotter.signals.SubmittedAvailabilitiesEventHandler;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;
import stuffplotter.views.events.EventCreationView;
import stuffplotter.views.events.EventView;

/**
 * Class for the Events Page presenter.
 */
public class EventsPagePresenter implements Presenter
{
	private List<Event> currentEvents;
	
	public interface EventsPageViewer
	{
		public HasClickHandlers getCreateButton();
		public HasClickHandlers getListCurrentButton();
		public HasClickHandlers getListPastButton();
		
		public HasWidgets getEventViewerContainer();
		public List<HasClickHandlers> getEventViewers();
		public void showEventViewers();
		public void showEventView();
		
		public HasClickHandlers getAcceptButton();
		public HasClickHandlers getDeclineButton();
		public HasClickHandlers getSubmitAvailabilitiesButton();
		public HasClickHandlers getFinalizeTimeButton();
		
		public void showInvitationButtons();
		public void showSubmitAvailabilitiesButton();
		public void showFinalizeTimeButton();
		public void clearEventActions();
		
		public void initialize(List<Event> events);
		
		/**
		 * Retrieve the EventsPageViewer as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventsPageViewer as a widget.
		 */
		public Widget asWidget();
	}
	
	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventsPageViewer eventsView;
	
	/**
	 * Constructor for an EventsPagePresenter.
	 * Presents a list of events from the user.
	 * @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post this.currentEvents == this.appUser.getUserEvents();
	 * @param appServices - the mapped services.
	 * @param eventBus - the global event bus.
	 * @param display - the view to present.
	 * @param user - the current user.
	 */
	public EventsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, EventsPageViewer display, Account user)
	{
		this.appServices = appServices;
		this.appUser = user;
		
		this.eventBus = eventBus;
		this.eventsView = display;

		fetchCurrentEvents();
	}
	
	/**
	 * Bind events deck panel view HasClickHandlers to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		eventsView.getCreateButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				Presenter presenter = new EventCreationPresenter(appServices,
																 eventBus,
																 new EventCreationView(),
																 appUser);
				presenter.go(null);
			}
			
		});
		
		eventsView.getListCurrentButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				fetchCurrentEvents();
			}
		});
		
		eventsView.getListPastButton().addClickHandler(new ClickHandler()
		{	
			@Override
			public void onClick(ClickEvent event)
			{
				fetchPastEvents();
			}
		});
		
		/*
		 * Global handlers
		 */
		this.eventBus.addHandler(SubmittedAvailabilitiesEvent.TYPE, new SubmittedAvailabilitiesEventHandler()
		{
			@Override
			public void onSubmitAvailabilities(SubmittedAvailabilitiesEvent event)
			{
				appServices.getEventService().updateScheduler(event.getAvailabilityIds(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Failed to submit timeslots");
						
					}

					@Override
					public void onSuccess(Void result)
					{
						eventsView.clearEventActions();
					}
					
				});
				
			}
		});
		
		this.eventBus.addHandler(EventCreatedEvent.TYPE, new EventCreatedEventHandler()
		{

			@Override
			public void onEventCreated(EventCreatedEvent event)
			{
				fetchCurrentEvents();
			}
			
		});
	}
	
	/**
	 * Binds each event viewer anchor to a handler to present it
	 * @pre true;
	 * @post true;
	 */
	private void bindEventViewers()
	{
		for (int i = 0; i < eventsView.getEventViewers().size(); i++)
		{
			final int eventsIndex = i;
			eventsView.getEventViewers().get(i).addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					Event selectedEvent;
					selectedEvent = currentEvents.get(eventsIndex);
					
					Presenter presenter = new EventPresenter(appServices,
																 eventBus,
																 new EventView(),
																 appUser,
																 selectedEvent);
					
					eventsView.showEventView();
					presenter.go(eventsView.getEventViewerContainer());
					
					if(selectedEvent.getStatus() == Status.PROPOSED)
					{
						if(selectedEvent.getOwnerID() == appUser.getUserEmail())
						{
							eventsView.showFinalizeTimeButton();
							eventsView.showSubmitAvailabilitiesButton(); //remove this later
						}
						else
						{
							eventsView.showSubmitAvailabilitiesButton();
						}
					}
					
					bindEventButtons(selectedEvent);
				}	
			});
		}
	}
	
	/**
	 * Binds event specific buttons to their respective handlers
	 * @pre true;
	 * @post true;
	 */
	private void bindEventButtons(Event event) 
	{
		final Event selectedEvent = event;
		final EventServiceAsync eventService = appServices.getEventService();
		
		this.eventsView.getSubmitAvailabilitiesButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event)
			{
				eventService.retrieveAvailabilities(selectedEvent.getEventScheduler(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Failed to retrieve timeslots");
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						AvailabilitySubmitterDialogBox submitter = new AvailabilitySubmitterDialogBox(result, eventBus);
					}

				});	
			}
		});
	}
	
	/**
	 * Present the events deck panel view
	 * @pre true;
	 * @post this.eventsView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.eventsView.asWidget());
	}
	
	/**
	 * Retrieves and displays the list of current events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getCurrentEvents());
	 */
	private void fetchCurrentEvents()
	{
		EventServiceAsync eventService = appServices.getEventService();
		eventService.retrieveEvents(appUser.getCurrentEvents(), new AsyncCallback<List<Event>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result; //used for bindEventViewers
				eventsView.initialize(currentEvents);
				eventsView.showEventViewers();
				bindEventViewers();
			}
		});
	}
	
	/**
	 * Retrieves and displays the list of past events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getPastEvents());
	 */
	private void fetchPastEvents()
	{
		EventServiceAsync eventService = appServices.getEventService();
		eventService.retrieveEvents(appUser.getPastEvents(), new AsyncCallback<List<Event>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result; //used for bindEventViewers
				eventsView.initialize(currentEvents);
				eventsView.showEventViewers();
				bindEventViewers();
			}
		});
	}
}
