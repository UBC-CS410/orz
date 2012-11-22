package stuffplotter.presenters;

import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.Event;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EventViewPresenter implements Presenter
{
	public interface EventViewer
	{
		public HasClickHandlers getVoteButton();
		public HasClickHandlers getCommentButton();
		public void openCommentBox();
		public void initializeView(Event event);
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventViewer eventView;
	private final Event eventModel;
	
	public EventViewPresenter(ServiceRepository appServices, HandlerManager eventBus, EventViewer display, Event data)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.eventView = display;
		this.eventModel = data;
	}
	
	/**
	 * Bind events page view HasClickHandlers to handlers
	 * @pre true
	 * @post true
	 */
	public void bind()
	{
		this.eventView.getVoteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event)
			{
				EventServiceAsync eventService = appServices.getEventService();
				
				
				AvailabilitySubmitterDialogBox submitter = new AvailabilitySubmitterDialogBox(null);
			}
		});
		
		this.eventView.getCommentButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				eventView.openCommentBox();
			}
		});
	}

	/**
	 * Present the event page view
	 * @pre true;
	 * @post this.eventsView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		eventView.initializeView(this.eventModel);
		container.add(eventView.asWidget());
	}

}
