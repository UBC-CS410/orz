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
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView.View;
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
	private boolean listCurrent = true;
	
	public interface EventsPageViewer
	{
		public HasClickHandlers getCreateButton();
		public HasClickHandlers getListCurrentButton();
		public HasClickHandlers getListPastButton();
		public List<HasClickHandlers> getListedLinks();
		
		public void populateListPanel(List<Event> toDisplay);
		public void hideListPanel();

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
	 * Bind eventsView HasClickHandlers to handlers
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
				eventsView.populateListPanel(currentEvents);
			}
		});
		
		eventsView.getListPastButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventsView.populateListPanel(currentEvents);
			}
		});
		
		for (int i = 0; i < eventsView.getListedLinks().size(); i++)
		{
			final int position = i;
			eventsView.getListedLinks().get(i).addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event)
				{
					Event clicked;
					if(listCurrent)
						clicked = currentEvents.get(position);
					else
						clicked = pastEvents.get(position);
					
					eventsView.hideListPanel();
					
					Presenter presenter = new EventViewPresenter(
							appServices,
							eventBus,
							new EventView(clicked));
					presenter.go((HasWidgets) eventsView);
				}
				
			});
		}
	}
	
	/**
	 * Present the events view
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
	 * Assigns currentEvents to the list of events associated with the user
	 * @pre true;
	 * @post this.currentEvents == eventService.retrieveEvents(appUser.getUserEvents);
	 */
	private void fetchCurrentEventsFromUser()
	{
		EventServiceAsync eventService = appServices.getEventService();
		eventService.retrieveEvents(appUser.getUserEvents(), new AsyncCallback<List<Event>>() {
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result;
				eventsView.populateListPanel(currentEvents);
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
