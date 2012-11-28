package stuffplotter.presenters;

import java.util.ArrayList;
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
	public interface EventsPageViewer
	{
		/**
		 * Retrieve the EventsPageViewer as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventsPageViewer as a widget.
		 */
		public Widget asWidget();
		
		public int initialize(Account user, List<Event> events);
		public void setFocus(int row);
		
		public HasClickHandlers getCreateEventButton();
		
		public HasClickHandlers getCurrentEventsButton();
		public HasClickHandlers getFinishedEventsButton();
		
		public HasClickHandlers getAcceptInviteButton();
		public HasClickHandlers getDeclineInviteButton();
		
		public HasClickHandlers getSubmitTimesButton();
		public HasClickHandlers getSelectTimeButton();
		
		public HasClickHandlers getRateEventButton();
			
		public void showInvitationButtons();
		public void showSubmitTimesButton();
		public void showSelectTimeButton();
		public void showRateEventButton();
		public void hideEventActionButtons();
		
		public HasWidgets getEventViewContainer();
		public void clearEventViewContainer();
		
		public List<HasClickHandlers> getEventListingLinks();
	}
	
	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventsPageViewer eventsView;
	
	private List<Event> currentEvents;
	private List<HandlerRegistration> eventActionListeners = new ArrayList<HandlerRegistration>();	
	
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
		eventsView.getCreateEventButton().addClickHandler(new ClickHandler() {
			
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
		
		eventsView.getCurrentEventsButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				fetchCurrentEvents();
			}
		});
		
		eventsView.getFinishedEventsButton().addClickHandler(new ClickHandler()
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
				}
				else
				{
					Window.alert("Event scheduled.");
				}
				
				fetchCurrentEvents();

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
		for (int i = 0; i < eventsView.getEventListingLinks().size(); i++)
		{
			final int eventsIndex = i;
			eventsView.getEventListingLinks().get(i).addClickHandler(new ClickHandler()
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
		final int selectedIndex = index; //TODO: remove this later
		final Event selectedEvent = currentEvents.get(selectedIndex);
		final EventServiceAsync eventService = appServices.getEventService();
		
		for (HandlerRegistration listener : eventActionListeners)
		{
			listener.removeHandler();
		}
		eventActionListeners = new ArrayList<HandlerRegistration>();
		
		eventActionListeners.add(this.eventsView.getAcceptInviteButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				// TODO Auto-generated method stub
				
			}
			
		}));
		
		eventActionListeners.add(this.eventsView.getDeclineInviteButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				// TODO Auto-generated method stub
				
			}
			
		}));
		
		eventActionListeners.add(this.eventsView.getSubmitTimesButton().addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event)
			{
				eventService.retrieveAvailabilities(selectedEvent.getEventScheduler(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						new AvailabilitySubmitterDialogBox(result, eventBus);
					}

				});	
			}
			
		}));
		
		eventActionListeners.add(this.eventsView.getSelectTimeButton().addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event)
			{
				eventService.retrieveAvailabilities(selectedEvent.getEventScheduler(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						new EventDateFinalizerDialogBox(result, selectedEvent, appServices, eventBus);
					}

				});	
			}
			
		}));
		
		eventActionListeners.add(this.eventsView.getRateEventButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				// TODO Auto-generated method stub
				
			}
			
		}));
		
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
		eventService.retrieveEvents(appUser, appUser.getCurrentEvents(), new AsyncCallback<List<Event>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				System.out.println("failed.");
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result;
				eventsView.clearEventViewContainer();
				eventsView.hideEventActionButtons();
				
				if(eventsView.initialize(appUser, currentEvents) > 0)
				{
					bindEventViewers();
				}

				if (currentEvents.size() > 0)
				{
					displayEvent(currentEvents.get(0), 0);
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
		eventService.retrieveEvents(appUser, appUser.getPastEvents(), new AsyncCallback<List<Event>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result;
				eventsView.clearEventViewContainer();
				eventsView.hideEventActionButtons();
				
				if(eventsView.initialize(appUser, currentEvents) > 0)
				{
					bindEventViewers();
				}

				if (currentEvents.size() > 0)
				{
					displayEvent(currentEvents.get(0), 0);
				}
			}
		});
	}
	
	/**
	 * Displays an event from the event roll
	 * @pre true;
	 * @post true;
	 * @param event - DEPRECATED
	 * @param index - the row index of the event to display
	 */
	private void displayEvent(Event event, int index)
	{		
		this.eventsView.setFocus(index);
		
		//Present the event
		Event selectedEvent = currentEvents.get(index);
		Presenter presenter = new EventPresenter(appServices,
													 eventBus,
													 new EventView(),
													 appUser,
													 selectedEvent);
		presenter.go(eventsView.getEventViewContainer());
	
		//Present the event specific action buttons
		this.eventsView.hideEventActionButtons();
		this.bindEventButtons(index);
		if(selectedEvent.getInvitees().contains(appUser.getUserEmail()))
		{
			eventsView.showInvitationButtons();
		}
		else 
		{
			switch(selectedEvent.getStatus())
			{
				case PROPOSED:
					if(selectedEvent.getOwnerID() == appUser.getUserEmail())
					{
						eventsView.showSelectTimeButton();
					}
					else
					{
						eventsView.showSubmitTimesButton();
					}
					break;
				case SCHEDULED:
					break;
				case FINISHED:
					eventsView.showRateEventButton();
					break;
			}
		}
	}
}
