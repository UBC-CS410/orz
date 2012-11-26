package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.presenters.EventCreationPresenter.CreateEventView;
import stuffplotter.views.util.CloseClickHandler;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the window for creating events.
 */
public class EventCreationView extends DialogBox implements CreateEventView
{
	private static final String TASKNAME = "Creating New Event"; 
	
	/**
	 * Depending on the view, the "Next" or "Submit" button will be placed into here.
	 */
	private SimplePanel secondBtnHolder;
	private EventCreationPagedPanel eventPages;
	private Button backBtn;
	private Button nextBtn;
	private Button submitBtn;
	private Button cancelBtn;
	
	/**
	 * Constructor for the EventCreationView class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationView()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		VerticalPanel vertPanel = new VerticalPanel();
		this.eventPages = new EventCreationPagedPanel();		
		vertPanel.add(this.eventPages);
		this.initializeButtons(vertPanel);
		this.add(vertPanel);
		
		this.center();
		this.setText(TASKNAME);
		this.setGlassEnabled(true);
	}
	
	/**
	 * Method to send the visitor to the pages of the event creation display.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to send to the pages of the event
	 * 						 creation display.
	 */
	public void acceptVisitor(EventCreationPageVisitor eventVisitor)
	{
		this.eventPages.accept(eventVisitor);
	}
	
	/**
	 * Helper method to initialize and add the buttons to the window.
	 * @pre vertPanel != null;
	 * @post true;
	 * @param vertPanel - the panel to add the buttons to.
	 */
	private void initializeButtons(VerticalPanel vertPanel)
	{
		this.backBtn = new Button("Back");
		this.backBtn.setEnabled(false);
		this.nextBtn = new Button("Next");
		this.submitBtn = new Button("Submit");
		this.cancelBtn = new Button("Cancel");
		this.cancelBtn.addClickHandler(new CloseClickHandler(this));
		
		HorizontalPanel btnHolder = new HorizontalPanel();
		this.secondBtnHolder = new SimplePanel();
		this.secondBtnHolder.add(this.nextBtn);
		
		btnHolder.add(this.backBtn);
		btnHolder.add(this.secondBtnHolder);
		btnHolder.add(this.cancelBtn);
		vertPanel.add(btnHolder);
	}
		
	/**
	 * Helper method to display the Next button in the display.
	 * @pre true;
	 * @post true;
	 */
	private void setAsNextButton()
	{
		this.secondBtnHolder.clear();
		this.secondBtnHolder.add(this.nextBtn);
	}
	
	/**
	 * Helper method to display the Submit button in the display.
	 * @pre true;
	 * @post true;
	 */
	private void setAsSubmitButton()
	{
		this.secondBtnHolder.clear();
		this.secondBtnHolder.add(this.submitBtn);
	}
	
	/**
	 * Close the window for event creation.
	 * @pre true;
	 * @post true;
	 */
	public void closeDisplay()
	{
		this.hide();
		this.removeFromParent();
	}
	
	@Override
	public EventSubmittable getCurrentPage()
	{
		return this.eventPages.getCurrentPage();
	}

	@Override
	public HasClickHandlers getCancelBtn()
	{
		return this.cancelBtn;
	}

	@Override
	public HasClickHandlers getBackBtn()
	{
		return this.backBtn;
	}
	
	@Override
	public HasClickHandlers getNextBtn()
	{
		return this.nextBtn;
	}

	@Override
	public HasClickHandlers getSubmitBtn()
	{
		return this.submitBtn;
	}
	
	@Override
	public void nextPage()
	{
		this.eventPages.nextPage();
		this.backBtn.setEnabled(true);
		if(!this.eventPages.hasNextPage())
		{
			this.setAsSubmitButton();
		}
	}

	@Override
	public void previousPage()
	{
		this.eventPages.previousPage();
		if(!this.eventPages.hasPreviousPage())
		{
			this.backBtn.setEnabled(false);
		}
		
		this.setAsNextButton();
	}

	@Override
	public HasValueChangeHandlers<Date> getCalendar()
	{
		return this.eventPages.getCalendar();
	}

	@Override
	public void populateTimeSheet(Date shownDate, List<Date> conflictDates)
	{
		this.eventPages.populateTimeSheet(shownDate, conflictDates);
	}
}
