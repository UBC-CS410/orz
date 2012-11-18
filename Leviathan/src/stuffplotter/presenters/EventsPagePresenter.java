package stuffplotter.presenters;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.shared.Event;

/**
 * Class for the Events Page presenter.
 */
public class EventsPagePresenter implements Presenter
{

	
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
	
	/**
	 * Constructor
	 * @pre
	 * @post
	 */
	public EventsPagePresenter(EventServiceAsync eventService, HandlerManager eventBus, EventsView eventsView)
	{
		this.eventService = eventService;
		this.eventBus = eventBus;
		this.eventsView = eventsView;
	}
	
	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.clear();
		container.add(eventsView.asWidget());
	}

}
