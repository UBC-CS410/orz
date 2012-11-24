package stuffplotter.presenters;

import java.util.ArrayList;
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
	private List<Event> pastEvents;
	private boolean current = true;
	
	public interface EventsPageViewer
	{
		public HasClickHandlers getCreateButton();
		public HasClickHandlers getListCurrentButton();
		public HasClickHandlers getListPastButton();
		
		public HasWidgets getEventViewerContainer();
		public List<HasClickHandlers> getEventViewers();
		public void showEventViewers();
		public void hideEventViewers();
		
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
	 * @param appServices - the mapped services
	 * @param eventBus - the global event bus
	 * @param display - the view to present
	 * @param user - the current user
	 */
	public EventsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, EventsPageViewer display, Account user)
	{
		this.appServices = appServices;
		this.appUser = user;
		
		this.eventBus = eventBus;
		this.eventsView = display;
				
		fetchCurrentEventsFromUser();
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
																 new EventCreationView(appUser.getUserEmail()));
				presenter.go(null);
			}
			
		});
		
		eventsView.getListCurrentButton().addClickHandler(new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				eventsView.showEventViewers();
			}
		});
		
		eventsView.getListPastButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventsView.showEventViewers();
			}
		});
		
		for (int i = 0; i < eventsView.getEventViewers().size(); i++)
		{
			final int eventsIndex = i;
			eventsView.getEventViewers().get(i).addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event)
				{
					Event selectedEvent;
					System.out.println("clicked?");
					if(current)
						selectedEvent = currentEvents.get(eventsIndex);
					else
						selectedEvent = pastEvents.get(eventsIndex);
					
					eventsView.hideEventViewers();
					
					Presenter presenter = new EventViewPresenter(
							appServices,
							eventBus,
							new EventView(),
							appUser,
							selectedEvent);
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
		container.add(this.eventsView.asWidget());
	}
	
	/**
	 * Assigns currentEvents to the list of events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getUserEvents);
	 */
	private void fetchCurrentEventsFromUser()
	{
		EventServiceAsync eventService = appServices.getEventService();
		eventService.retrieveEvents(appUser.getCurrentEvents(), new AsyncCallback<List<Event>>() {
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				/**
				 * Keep track of current events for binding
				 */
				currentEvents = result;
				eventsView.initialize(currentEvents);
				bind();
			}
		});
	}
	
	/**
	 * 
	 * @pre
	 * @post
	 */
	private void fetchPastEventsFromUser()
	{
		
	}

}
