package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import stuffplotter.shared.Availability;
import stuffplotter.signals.EventSchedulerEvent;
import stuffplotter.views.util.CloseClickHandler;

import stuffplotter.views.util.DateSplitter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to make the window for submitting availabilities for an event.
 * TODO: This class needs some major refactoring.
 */
public class AvailabilitySubmitterDialogBox extends DialogBox
{
	// vertical panel to hold all the components in top-down order
	final private VerticalPanel vertPanel = new VerticalPanel();
	// horizontal panel to display all the DaySelections objects
	final private HorizontalPanel horPanel = new HorizontalPanel();
	
	final private HandlerManager eventBus;
	
	private Long scheduler;
	private Map<Date, Long> availabilities;
	private List<Long> submissions;
	
	
	/**
	 * Constructor for AvailabilitySubmitter class.
	 * Maps the times of each time slot to its id.
	 * @pre true;
	 * @post this.availabilities != null && this.isVisible() == true;
	 */
	public AvailabilitySubmitterDialogBox(Long scheduler, List<Availability> timeSlots, HandlerManager eventBus)
	{
		super();
		
		this.eventBus = eventBus;
		
		this.scheduler = scheduler;
		this.availabilities = new HashMap<Date, Long>();
		for (Availability timeSlot : timeSlots)
		{
			this.availabilities.put(timeSlot.getTime(), timeSlot.getId());
			//System.out.println(timeSlot.getTime().hashCode());
		}

		initializeWindow();
	}
	
	/**
	 * Method to retrieve the submission submitted by the user.
	 * @pre true;
	 * @post true;
	 * @return the submissions of the user.
	 */
	public List<Date> retrieveSubmissions()
	{
		List<Date> selectedValues = new ArrayList<Date>();
		
		// for loop to get the submission information from the TimeSheetPanel
		for (int i = 0; i < this.horPanel.getWidgetCount(); i++)
		{
			Widget childWidget = this.horPanel.getWidget(i); 
			if(childWidget instanceof TimeSheetPanel)
			{
				selectedValues.addAll(((TimeSheetPanel) childWidget).retrieveSubmission());
			}
		}
		
		return selectedValues;
	}
	
	/**
	 * Method to retrieve the list of availability ids to update
	 * @pre true;
	 * @post true;
	 * @return submissions
	 */
	public List<Long> retrieveResults() 
	{
		return submissions;
		
	}
	
	/**
	 * Helper method to initialize the AvailabilitySubmitter.
	 * @pre true;
	 * @post true;
	 */
	private void initializeWindow()
	{
		List<Date> times = new ArrayList<Date>();
		for (Date time : this.availabilities.keySet())
		{
			times.add(time);
		}
		
		TimeSheetPanel timeSheet = new TimeSheetPanel();
		timeSheet.addDays(times, times /*change this side*/); // TO COMPLETE (FEED GOOGLE CALENDAR VALUES FROM PRESENTER)
		
		horPanel.add(timeSheet);
		vertPanel.add(horPanel);
		this.add(vertPanel);
		initializeSubmitBtn(vertPanel);
		intializeCancelBtn(vertPanel);
		this.setText("Submit Your Availabilities");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setModal(true);
		this.center();	
	}
	
	/**
	 * Helper method to initialize the submit button for the window.
	 * @pre panel != null;
	 * @post true;
	 * @param panel - the panel to add the submit button to.
	 */
	private void initializeSubmitBtn(Panel panel)
	{
		Button submitBtn = new Button("Submit");
		submitBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				final List<Date> selectedValues = retrieveSubmissions();
							
				submissions = new ArrayList<Long>();

				for(Date value : selectedValues)
				{
					submissions.add(availabilities.get(value));
				}
				eventBus.fireEvent(new EventSchedulerEvent(scheduler, submissions));
				hide();
			}
		});
		panel.add(submitBtn);
	}
	
	/**
	 * Helper method to initialize the cancel button for the window.
	 * @pre panel != null;
	 * @post true;
	 * @param panel - the panel to add the close button to.
	 */
	private void intializeCancelBtn(Panel panel)
	{
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new CloseClickHandler(this));
		panel.add(cancelBtn);
	}
}
