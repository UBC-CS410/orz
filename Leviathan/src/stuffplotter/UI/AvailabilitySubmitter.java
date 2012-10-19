package stuffplotter.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

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
		intializeCancelBtn();
		this.setText("Submit Your Availabilities");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setModal(true);
		this.center();	
	}
	
	/**
	 * Helper method to initialize the cancel button for the window.
	 */
	private void intializeCancelBtn()
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
		this.add(cancelBtn);
	}
}
