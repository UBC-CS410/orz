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

import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.server.AchievementChecker;
import stuffplotter.server.LevelUpdater;
import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Status;
import stuffplotter.shared.Scheduler;
import stuffplotter.signals.EventCreatedEvent;
import stuffplotter.signals.EventCreatedEventHandler;
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
			
		public void hideEventActionButtons();
		public void showInvitationButtons();
		public void showSubmitTimesButton();
		public void showSelectTimeButton();
		public void showRateEventButton();
				
		public HasWidgets getEventViewContainer();
		public void clearEventViewContainer();
		
		public List<HasClickHandlers> getEventListingLinks();
	}
	
	private final ServiceRepository applicationServices;
	private final HandlerManager eventBus;
	private final EventsPageViewer eventsPageView;
	
	private Account userAccount;
	private AccountStatistic userStatistic;
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
		this.applicationServices = appServices;
		this.eventBus = eventBus;
		this.eventsPageView = display;
		
		this.userAccount = user;
		appServices.getStatsService().getStats(userAccount.getUserEmail(), new AsyncCallback<AccountStatistic>()
		{

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("An unexpected error has occured");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(AccountStatistic result)
			{
				userStatistic = result;
				
			}
			
		});

		fetchCurrentEvents();
	}
	
	/**
	 * Bind events deck panel view HasClickHandlers to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		eventsPageView.getCreateEventButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				Presenter presenter = new EventCreationPresenter(applicationServices,
																 eventBus,
																 new EventCreationView(),
																 userAccount);
				presenter.go(null);
			}
			
		});
		
		eventsPageView.getCurrentEventsButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				fetchCurrentEvents();
			}
		});
		
		eventsPageView.getFinishedEventsButton().addClickHandler(new ClickHandler()
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
					applicationServices.getEventService().updateScheduler(userAccount.getUserEmail(), event.getSchedulerId(), event.getAvailabilityIds(), new AsyncCallback<Void>() 
					{

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
				userAccount.addUserEvent(event.getEventID());
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
		for (int i = 0; i < eventsPageView.getEventListingLinks().size(); i++)
		{
			final int eventsIndex = i;
			eventsPageView.getEventListingLinks().get(i).addClickHandler(new ClickHandler()
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
		final Event selectedEvent = currentEvents.get(index);
		
		for (HandlerRegistration listener : eventActionListeners)
		{
			listener.removeHandler();
		}
		eventActionListeners = new ArrayList<HandlerRegistration>();
		
		eventActionListeners.add(this.eventsPageView.getAcceptInviteButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				applicationServices.getEventService().confirmGuest(selectedEvent.getId(), userAccount.getUserEmail(), new AsyncCallback<Void>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured.");
						caught.printStackTrace();
						
					}

					@Override
					public void onSuccess(Void result)
					{
						fetchCurrentEvents();
					}
					
				});
				eventsPageView.hideEventActionButtons();
				
			}
			
		}));
		
		eventActionListeners.add(this.eventsPageView.getDeclineInviteButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				applicationServices.getEventService().removeGuest(selectedEvent.getId(), userAccount.getUserEmail(), new AsyncCallback<Void>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured.");
						caught.printStackTrace();
						
					}

					@Override
					public void onSuccess(Void result)
					{
						fetchCurrentEvents();
					}
					
				});
				eventsPageView.hideEventActionButtons();
			}
			
		}));
		
		eventActionListeners.add(this.eventsPageView.getSubmitTimesButton().addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event)
			{
				applicationServices.getEventService().retrieveAvailabilities(selectedEvent.getSchedulerId(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						new AvailabilitySubmitterDialogBox(selectedEvent.getSchedulerId(), result, eventBus);
					}

				});
				eventsPageView.hideEventActionButtons();
			}
			
		}));
		
		eventActionListeners.add(this.eventsPageView.getSelectTimeButton().addClickHandler(new ClickHandler() 
		{

			@Override
			public void onClick(ClickEvent event)
			{
				applicationServices.getEventService().retrieveAvailabilities(selectedEvent.getSchedulerId(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						new EventDateFinalizerDialogBox(result, selectedEvent, applicationServices, eventBus);
					}

				});
				eventsPageView.hideEventActionButtons();
			}
			
		}));
		
		eventActionListeners.add(this.eventsPageView.getRateEventButton().addClickHandler(new ClickHandler()
		{

			@Override
			public void onClick(ClickEvent event)
			{
				Window.alert("You liked this event.");
				
				applicationServices.getEventService().rateEvent(selectedEvent.getId(), userAccount.getUserEmail(), new AsyncCallback<Void>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result)
					{
						// TODO Auto-generated method stub
						
					}
					
				});
				
				applicationServices.getStatsService().getStats(selectedEvent.getOwnerID(), new AsyncCallback<AccountStatistic>() 
				{

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("An unexpected error has occured.");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(AccountStatistic result)
					{
						result.accept(new LevelUpdater().rateEvent());
						result.accept(new AchievementChecker());
					}
					
				});
				userStatistic.accept(new LevelUpdater().rateEvent());
				userStatistic.accept(new AchievementChecker(), selectedEvent);
				eventsPageView.hideEventActionButtons();
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
		container.add(this.eventsPageView.asWidget());
	}
	
	/**
	 * Retrieves and displays the list of current events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getCurrentEvents());
	 */
	private void fetchCurrentEvents()
	{
		applicationServices.getAccountService().getAccount(userAccount.getUserEmail(), new AsyncCallback<Account>() 
		{

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("An unexpected error has occured");
				caught.printStackTrace();	
			}

			@Override
			public void onSuccess(Account result)
			{
				userAccount = result;
				applicationServices.getEventService().retrieveListOfEvents(userAccount, userAccount.getCurrentEvents(), new AsyncCallback<List<Event>>()
				{
					@Override
					public void onFailure(Throwable caught)
					{
						System.out.println("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Event> result)
					{
						currentEvents = result;
						eventsPageView.clearEventViewContainer();
						eventsPageView.hideEventActionButtons();
						
						if(eventsPageView.initialize(userAccount, currentEvents) > 0)
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
			
		});
	}
	
	/**
	 * Retrieves and displays the list of past events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getPastEvents());
	 */
	private void fetchPastEvents()
	{
		applicationServices.getAccountService().getAccount(userAccount.getUserEmail(), new AsyncCallback<Account>() 
		{

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("An unexpected error has occured");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Account result)
			{
				userAccount = result;
				applicationServices.getEventService().retrieveListOfEvents(userAccount, userAccount.getPastEvents(), new AsyncCallback<List<Event>>()
				{
					@Override
					public void onFailure(Throwable caught)
					{
						System.out.println("An unexpected error has occured");
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(List<Event> result)
					{
						currentEvents = result;
						eventsPageView.clearEventViewContainer();
						eventsPageView.hideEventActionButtons();
						
						if(eventsPageView.initialize(userAccount, currentEvents) > 0)
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
		this.eventsPageView.setFocus(index);
		
		//Present the event
		Event selectedEvent = currentEvents.get(index);
		Presenter presenter = new EventPresenter(applicationServices,
													 eventBus,
													 new EventView(),
													 userAccount,
													 selectedEvent);
		presenter.go(eventsPageView.getEventViewContainer());
	
		//Present the event specific action buttons
		this.eventsPageView.hideEventActionButtons();
		this.bindEventButtons(index);
		if(selectedEvent.getInvitees().contains(userAccount.getUserEmail()))
		{
			eventsPageView.showInvitationButtons();
		}
		else 
		{
			switch(selectedEvent.getStatus())
			{
				case PROPOSED:
					if(selectedEvent.getOwnerID() == userAccount.getUserEmail())
					{
						eventsPageView.showSelectTimeButton();
					}
					else
					{
						applicationServices.getEventService().retrieveScheduler(selectedEvent.getSchedulerId(), new AsyncCallback<Scheduler>() 
						{

							@Override
							public void onFailure(Throwable caught)
							{
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Scheduler result)
							{
								if (!result.getSubmitters().contains(userAccount.getUserEmail()))
								{
									eventsPageView.showSubmitTimesButton();
								}
								
							}
							
						});
					}
					break;
				case SCHEDULED:
					break;
				case FINISHED:
					if(!selectedEvent.getEventRaters().contains(userAccount.getUserEmail()))
					{
						eventsPageView.showRateEventButton();
					}
					break;
			}
		}
	}
}
