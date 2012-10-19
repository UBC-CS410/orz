package stuffplotter.UI;

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
		this.setTitle("Submit Your Availabilities");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setModal(true);
		this.center();	
	}
	
}
