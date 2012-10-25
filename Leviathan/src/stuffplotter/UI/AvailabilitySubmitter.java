package stuffplotter.UI;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.UI.MonthPanel.Month;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to make the window for submitting availabilities for an event.
 */
public class AvailabilitySubmitter extends DialogBox {

	// vertical panel to hold all the components in top-down order
	final private VerticalPanel vertPanel = new VerticalPanel();
	// horizontal panel to display all the DaySelections objects
	final private HorizontalPanel horPanel = new HorizontalPanel();
	
	/**
	 * Constructor for AvailabilitySubmitter class.
	 */
	public AvailabilitySubmitter()
	{
		super();
		initializeWindow();
	}
	
	/**
	 * Method to retrieve the submission submitted by the user.
	 * @return the submissions of the user.
	 */
	public List<Integer> retrieveSubmissions()
	{
		List<Integer> selectedValues = new ArrayList<Integer>();
		
		for (int i = 0; i < this.horPanel.getWidgetCount(); i++)
		{
			Widget childWidget = this.horPanel.getWidget(i); 
			if(childWidget instanceof MonthPanel)
			{
				selectedValues.addAll(((MonthPanel) childWidget).retrieveSubmission());
			}
		}
		
		return selectedValues;
	}
	
	/**
	 * Helper method to initialize the AvailabilitySubmitter.
	 */
	private void initializeWindow()
	{
		int[] testTimes = {0, 3, 5, 23};
		
		MonthPanel month = new MonthPanel(Month.OCTOBER, testTimes);
		//horPanel.add(new DaySelections("4"));
		//horPanel.add(new DaySelections("5", testTimes));
		//horPanel.add(month);
		horPanel.add(new TimeSheetPanel());
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
				List<Integer> selectedValues = retrieveSubmissions();
				String result = "";
				for(Integer value : selectedValues)
				{
					result += "Selected: " + value + " ";
				}
				hide();
				Window.alert(result);
			}
		});
		panel.add(submitBtn);
	}
	
	/**
	 * Helper method to initialize the cancel button for the window.
	 * @param panel - the panel to add the close button to.
	 */
	private void intializeCancelBtn(Panel panel)
	{
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new ClickHandler() 
		{
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		panel.add(cancelBtn);
	}
}
