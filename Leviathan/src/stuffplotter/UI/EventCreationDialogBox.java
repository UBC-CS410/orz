package stuffplotter.UI;

import stuffplotter.misc.CloseClickHandler;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the window for creating events.
 */
public class EventCreationDialogBox extends DialogBox
{
	/**
	 * Constructor for the EventCreationDialogBox class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationDialogBox()
	{
		super();
		HorizontalPanel horPanel = new HorizontalPanel();
		// temporary event info panel
		SimplePanel tempPanel = new SimplePanel();
		tempPanel.add(new EventInfoInput());
		horPanel.add(tempPanel);
		
		TimeSheetPanel timeSheetPanel = new TimeSheetPanel();
		
		VerticalPanel vertPanel = new VerticalPanel();
		vertPanel.add(new MapCalendarView(timeSheetPanel));
		vertPanel.add(timeSheetPanel);
		intializeCancelBtn(vertPanel);
		horPanel.add(vertPanel);
		
		this.center();
		this.setGlassEnabled(true);
		this.add(horPanel);
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
