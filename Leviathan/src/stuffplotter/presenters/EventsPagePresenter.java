package stuffplotter.presenters;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
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
import stuffplotter.signals.EventSchedulerEvent;
import stuffplotter.signals.EventSchedulerEventHandler;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;
import stuffplotter.views.events.EventCreationView;
import stuffplotter.views.events.EventDateFinalizerDialogBox;
import stuffplotter.views.events.EventView;

/**
 * Class for the Events Page presenter.
 */
public class EventsPagePresenter implements Presenter
{
	private List<Event> currentEvents;
	private HandlerRegistration submitAvailabilities;
	private HandlerRegistration finalizeTime;
	
	public interface EventsPageViewer
	{
		public HasClickHandlers getCreateButton();
		public HasClickHandlers getListCurrentButton();
		public HasClickHandlers getListPastButton();
		
		public HasWidgets getEventViewerContainer();
		public List<HasClickHandlers> getEventViewers();
		public void showEventSelected(int row);
		
		public HasClickHandlers getAcceptButton();
		public HasClickHandlers getDeclineButton();
		public HasClickHandlers getSubmitAvailabilitiesButton();
		public HasClickHandlers getFinalizeTimeButton();
		
		public void showInvitationButtons();
		public void showSubmitAvailabilitiesButton();
		public void showFinalizeTimeButton();
		
		public void removeSubmitAvailabilitiesButton();
		public void removeFinalizeTimeButton();
		public void clearEventView();
		
		public void initialize(Account user, List<Event> events);
		
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
		this.eventBus.addHandler(EventSchedulerEvent.TYPE, new EventSchedulerEventHandler()
		{
			@Override
			public void onSchedulerUpdate(EventSchedulerEvent event)
			{
				if (event.getAvailabilityIds() != null)
				{
					appServices.getEventService().updateScheduler(event.getAvailabilityIds(), new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("Failed to submit availabilities...");
							
						}

						@Override
						public void onSuccess(Void result)
						{
							Window.alert("Submitted availabilities.");
						}
						
					});
					eventsView.removeSubmitAvailabilitiesButton();
				}
				else
				{
					displayEvent(event.getUpdatedEvent(), event.getUpdatedEventIndex());
					Window.alert("Event scheduled.");
					eventsView.removeFinalizeTimeButton();
				}

			}
		});
		
		this.eventBus.addHandler(EventCreatedEvent.TYPE, new EventCreatedEventHandler()
		{

			@Override
			public void onEventCreated(EventCreatedEvent event)
			{
				appUser.addUserEvent(event.getEventID());
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
					displayEvent(currentEvents.get(eventsIndex), eventsIndex);
				}	
			});
		}
	}
	
	/**
	 * Binds event specific buttons to their respective handlers
	 * @pre true;
	 * @post true;
	 */
	private void bindEventButtons(int index) 
	{
		final int selectedIndex = index;
		final Event selectedEvent = currentEvents.get(selectedIndex);
		final EventServiceAsync eventService = appServices.getEventService();
		
		if (submitAvailabilities != null) 
		{
			submitAvailabilities.removeHandler();
		}
		submitAvailabilities = this.eventsView.getSubmitAvailabilitiesButton().addClickHandler(new ClickHandler() {

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
						AvailabilitySubmitterDialogBox ignore = new AvailabilitySubmitterDialogBox(result, eventBus);
					}

				});	
			}
		});
		
		if (finalizeTime != null) 
		{
			finalizeTime.removeHandler();
		}
		finalizeTime = this.eventsView.getFinalizeTimeButton().addClickHandler(new ClickHandler() {

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
						EventDateFinalizerDialogBox ignore = new EventDateFinalizerDialogBox(result, selectedEvent, selectedIndex, appServices, eventBus);
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
				eventsView.initialize(appUser, currentEvents);
				eventsView.removeSubmitAvailabilitiesButton();
				bindEventViewers();
				if (currentEvents.size() > 0)
				{
					displayEvent(currentEvents.get(0), 0);
				}
				else
				{
					eventsView.clearEventView();
				}
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
				eventsView.initialize(appUser, currentEvents);
				eventsView.removeSubmitAvailabilitiesButton();
				bindEventViewers();
				if (currentEvents.size() > 0)
				{
					displayEvent(currentEvents.get(0), 0);
				}
				else
				{
					eventsView.clearEventView();
				}
			}
		});
	}
	
	/**
	 * Displays an event from the event roll
	 * @pre true;
	 * @post true;
	 * @param event - the event to display
	 * @param index - the row index of the event to display
	 */
	private void displayEvent(Event event, int index)
	{		
		Presenter presenter = new EventPresenter(appServices,
													 eventBus,
													 new EventView(),
													 appUser,
													 event);
		presenter.go(eventsView.getEventViewerContainer());
		
		eventsView.showEventSelected(index);
		
		if(event.getInvitees().contains(appUser.getUserEmail()))
		{
			eventsView.showInvitationButtons();
		}
		else 
		{
			if(event.getStatus() == Status.PROPOSED)
			{
				if(event.getOwnerID() == appUser.getUserEmail())
				{
					eventsView.showFinalizeTimeButton();
				}
				else
				{
					eventsView.showSubmitAvailabilitiesButton();
				}
			}
		}	
		bindEventButtons(index);
	}
}
