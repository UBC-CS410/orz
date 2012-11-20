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

/**
 * Class for the Events Page presenter.
 */
public class EventsPagePresenter implements Presenter
{
	private List<Event> currentEvents;
	private List<Event> pastEvents;
	
	public interface EventsView
	{
		public HasClickHandlers getCreateEventBtn();
		public HasClickHandlers getCurrentEventsBtn();
		public HasClickHandlers getPastEventsBtn();
		public void setDisplay(List<Event> toDisplay);
		public Long getClickedRowIndex(ClickEvent event);
		public Widget asWidget();
		//public EventList getEventList(); // create presenter for this 
	}
	
	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventsView eventsView;
	
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
	public EventsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, EventsView display, Account user)
	{
		this.appServices = appServices;
		this.appUser = user;
		
		this.eventBus = eventBus;
		this.eventsView = display;
				
		fetchCurrentEventsFromUser();
	}
	
	/**
	 * Bind events view components to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		eventsView.getCreateEventBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				Presenter presenter = new EventCreationPresenter(appServices,
																 eventBus,
																 new EventCreationView(appUser.getUserEmail()));
				presenter.go(null);
			}
			
		});
		
		eventsView.getCurrentEventsBtn().addClickHandler(new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				eventsView.setDisplay(currentEvents);
			}
		});

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
	 * 
	 * @pre
	 * @post
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
				eventsView.setDisplay(currentEvents);
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
