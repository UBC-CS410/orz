package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.misc.EventSubmittable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		
		/**
		 * Retrieve the Back "button".
		 * @pre true;
		 * @post true;
		 * @return the Back "button".
		 */
		public HasClickHandlers getBackBtn();
		
		/**
		 * Retrieve the Next/Submit "button" (changes depending on if it is the last page).
		 * @pre true;
		 * @post true;
		 * @return the Next/Submit "button".
		 */
		public HasClickHandlers getNextBtn();
		
		/**
		 * Go to the next page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void nextPage();
		
		/**
		 * Determines if there is a next page for creating an event.
		 * @pre true;
		 * @post true;
		 * @return true if there is a next page, false otherwise.
		 */
		public boolean hasNextPage();
		
		/**
		 * Go to the previous page of the event creation procedure if it exists.
		 * @pre true;
		 * @post true;
		 */
		public void previousPage();
		
		/**
		 * Determines if there is a previous page for creating an event.
		 * @pre true;
		 * @post true;
		 * @return true if there is a previous page, false otherwise.
		 */
		public boolean hasPreviousPage();
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
		this.createEventDialogBox.getBackBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				createEventDialogBox.previousPage();
				if(!createEventDialogBox.hasPreviousPage())
				{
					//this.createEventDialogBox.getBackBtn().setEnabled(false);
				}
			/*	if(nextBtn.getText().equals(SUBMITBUTTONTEXT))
				{
					submitAction.removeHandler();
					setAsNextButton();
				}*/
			}	
		});
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
