package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
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
		public Widget asWidget();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final EventViewer eventView;
	
	public EventViewPresenter(ServiceRepository appServices, HandlerManager eventBus, EventViewer display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.eventView = display;
	}
	
	public void bind()
	{
		this.eventView.getVoteButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event)
			{
				AvailabilitySubmitterDialogBox submitter = new AvailabilitySubmitterDialogBox(null);
			}
			
		});
	}

	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(eventView.asWidget());
	}

}
