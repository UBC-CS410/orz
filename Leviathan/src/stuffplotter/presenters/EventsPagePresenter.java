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
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventCreationDialogBox;

/**
 * Class for the Events Page presenter.
 */
public class EventsPagePresenter implements Presenter
{
	private Account currentAccount;
	private List<Event> currentEvents;
	private List<Event> pastEvents;
	
	public interface EventsView
	{
		public HasClickHandlers getCreateEventBtn();
		public HasClickHandlers getCurrentEventsBtn();
		public HasClickHandlers getPastEventsBtn();
		public void setDisplay(List<Event> toDisplay);
		public Long getClickedId(ClickEvent event);
		public Widget asWidget();
		//public EventList getEventList(); // create presenter for this 
	}
	
	private final EventServiceAsync eventService;
	private final HandlerManager eventBus;
	private final EventsView eventsView;
	
	private EventCreationDialogBox eventCreator;
	
	/**
	 * Constructor for an EventsPagePresenter.
	 * @pre eventService != null && eventBus != null && eventsView != null && eventsUser != null;
	 * @post
	 * @param eventService
	 * @param eventBus
	 * @param eventsView
	 * @param eventsUser
	 */
	public EventsPagePresenter(EventServiceAsync eventService, HandlerManager eventBus, EventsView eventsView, Account eventsUser)
	{
		this.eventService = eventService;
		this.eventBus = eventBus;
		this.eventsView = eventsView;
		
		this.currentAccount = eventsUser;
		
		fetchCurrentEvents();
		this.eventsView.setDisplay(currentEvents);
	}
	
	private void bind()
	{
		eventsView.getCreateEventBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				eventCreator = new EventCreationDialogBox();
			}
			
		});
		
		eventsView.getCurrentEventsBtn().addClickHandler(new ClickHandler() {
	
			@Override
			public void onClick(ClickEvent event) {
				eventsView.setDisplay(currentEvents);
			}
		});

	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(eventsView.asWidget());
	}
	
	private void fetchCurrentEvents()
	{
		eventService.retrieveEvents(currentAccount.getUserEvents(), new AsyncCallback<List<Event>>() {
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Event> result)
			{
				currentEvents = result;
			}
		});
	}
	
	private void fetchPastEvents()
	{
		
	}

}
