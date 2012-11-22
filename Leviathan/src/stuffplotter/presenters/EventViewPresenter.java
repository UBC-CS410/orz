package stuffplotter.presenters;

import java.util.Date;
import java.util.List;

import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.client.services.EventServiceAsync;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Account;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EventViewPresenter implements Presenter
{
	public interface EventViewer
	{
		public HasClickHandlers getVoteButton();
		public HasClickHandlers getCommentButton();
		public HasKeyDownHandlers getCommentTextBox();
		public String getCommentText();
		public void openCommentTextBox();
		public void clearCommentTextBox();
		public void showComments(List<Comment> comments);
		public void initializeView(Event event);
		public Widget asWidget();
	}
	
	private final Account appUser;
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventViewer eventView;
	private final Event eventData;
	
	private List<Comment> eventComments;
	
	public EventViewPresenter(ServiceRepository appServices, HandlerManager eventBus, EventViewer display, Account user, Event data)
	{
		this.appUser = user;
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.eventView = display;
		this.eventData = data;
		
		populateView();
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
				eventService.retrieveAvailabilities(eventData.getEventScheduler(), new AsyncCallback<List<Availability>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Availability> result)
					{
						AvailabilitySubmitterDialogBox submitter = new AvailabilitySubmitterDialogBox(null);
					}

				});
				
			}
		});
		
		this.eventView.getCommentButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event)
			{
				eventView.openCommentTextBox();
			}
		});
		
		this.eventView.getCommentTextBox().addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event)
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					String username = appUser.getUserFullName();
					Date timenow = new Date();
					String content = eventView.getCommentText();
					
					EventServiceAsync eventService = appServices.getEventService();
					eventService.addComment(eventData.getId(), username, timenow, content, new AsyncCallback<Void>()
					{

						@Override
						public void onFailure(Throwable caught)
						{
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result)
						{
							populateView();
						}
						
					});
				}
				
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
		container.add(eventView.asWidget());
	}
	
	/**
	 * Populates event view with event data and event comments
	 * @pre true;
	 * @post true;
	 */
	private void populateView()
	{
		EventServiceAsync eventService = appServices.getEventService();
		eventService.getComments(eventData.getId(), new AsyncCallback<List<Comment>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Comment> result)
			{
				eventView.initializeView(eventData);
				eventView.clearCommentTextBox();
				eventView.showComments(result);
			}

		});
	}

}
