package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.misc.EventSubmittable;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class EventCreationPresenter implements Presenter
{
	public interface CreateEventView
	{
		/**
		 * Retrieve the current page of the event creation procedure being displayed.
		 * @pre true;
		 * @post true;
		 * @return the current page of the event creation procedure being displayed.
		 */
		public EventSubmittable getCurrentPage();
		
		/**
		 * Retrieve the Cancel "button".
		 * @pre true;
		 * @post true;
		 * @return the Cancel "button".
		 */
		public HasClickHandlers getCancelBtn();
		
		
		public HasClickHandlers getNextBtn();
		
		/**
		 * Go to the next page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void nextPage();
		
		/**
		 * Go to the previous page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void previousPage();
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final CreateEventView createEventDialogBox;
	
	/**
	 * Constructor for the EventCreationPresenter.
	 * @pre appServices != null && eventBus != null && display != null;
	 * @post true;
	 * @param appServices - the repository containing all the services available for the application.
	 * @param eventBus - the event bus for the application.
	 * @param display - the CreateEventView to associate with the EventCreationPresenter.
	 */
	public EventCreationPresenter(ServiceRepository appServices, HandlerManager eventBus, CreateEventView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.createEventDialogBox = display;
	}
	
	/**
	 * Helper method to attach all the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		
	}
	
	/**
	 * Initialize the CreateEventView.
	 * Note: Don't need to add panel to anything since it is a DialogBox.
	 * @pre true;
	 * @post true;
	 * @param container - just set this to null.
	 */
	@Override
	public void go(HasWidgets container)
	{
		this.bind();
	}
}
