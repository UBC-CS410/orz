package stuffplotter.presenters;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.rpc.AsyncCallback;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
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
		public void hideEventViewers();
		
		public HasClickHandlers getAcceptButton();
		public HasClickHandlers getDeclineButton();
		public HasClickHandlers getSubmitAvailabilitiesButton();
		public HasClickHandlers getFinalizeTimeButton();
		
		public void showInvitationButtons();
		public void showSubmitAvailabilitiesButton();
		public void showFinalizeTimeButton();
		
		public void initialize(List<Event> events);
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
					
					eventsView.hideEventViewers();
					presenter.go(eventsView.getEventViewerContainer());
				}	
			});
		}
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
