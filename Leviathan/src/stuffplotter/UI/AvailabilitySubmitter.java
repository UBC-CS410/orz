package stuffplotter.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
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

	/**
	 * Constructor for AvailabilitySubmitter class.
	 */
	public AvailabilitySubmitter()
	{
		super();
		initializeWindow();
	}
	
	/**
	 * Helper method to initialize the AvailabilitySubmitter.
	 */
	private void initializeWindow()
	{
		int[] testTimes = {0, 3, 5, 23};
		
		VerticalPanel vertPanel = new VerticalPanel();
		HorizontalPanel horPanel = new HorizontalPanel();
		horPanel.add(new DaySelections("4"));
		horPanel.add(new DaySelections("5", testTimes));
		vertPanel.add(horPanel);
		this.add(vertPanel);
		intializeCancelBtn(vertPanel);
		this.setText("Submit Your Availabilities");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setModal(true);
		this.center();	
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
				// TODO Auto-generated method stub
				hide();
			}
		});
		panel.add(cancelBtn);
	}
	
}
