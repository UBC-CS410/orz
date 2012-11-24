package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stuffplotter.views.util.CloseClickHandler;
import stuffplotter.views.util.DateSplitter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to make the window for submitting availabilities for an event.
 */
public class AvailabilitySubmitterDialogBox extends DialogBox
{
	// vertical panel to hold all the components in top-down order
	final private VerticalPanel vertPanel = new VerticalPanel();
	// horizontal panel to display all the DaySelections objects
	final private HorizontalPanel horPanel = new HorizontalPanel();
	
	/**
	 * Constructor for AvailabilitySubmitter class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AvailabilitySubmitterDialogBox(List<Date> timeSlotsAvailable)
	{
		super();
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
	 * Helper method to initialize the AvailabilitySubmitter.
	 * @pre true;
	 * @post true;
	 */
	private void initializeWindow()
	{
		// TO DO: Remove hard coded values and feed values from database
		TimeSheetPanel timeSheet = new TimeSheetPanel();
		int[] days = {2};
		int[] days2 = {6, 8};
		/*
		timeSheet.addDay(Month.OCTOBER, "2012", days);
		timeSheet.addDay(Month.OCTOBER, "2012", days2);
		timeSheet.addDay(Month.NOVEMBER, "2012", days2);
		*/
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
							
				//temporary for each loop to help display selected intervals
				String result = "";
				for(Date value : selectedValues)
				{
					DateSplitter splitter = new DateSplitter(value);
					String month = splitter.getMonthAsString();
					String year = splitter.getYearAsString();
					result += "Selected: " + month + " " + year + " ";

					String dayValue = splitter.getDayAsString();
					result += "Day " + dayValue + "-> ";
					int hour = splitter.getHour();
					result += hour + " ";			
				}
				
				hide();
				Window.alert(result);
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
