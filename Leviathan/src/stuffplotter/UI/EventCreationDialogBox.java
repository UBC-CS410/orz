package stuffplotter.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	 */
	public EventCreationDialogBox()
	{
		super();
		HorizontalPanel horPanel = new HorizontalPanel();
		// temporary event info panel
		SimplePanel tempPanel = new SimplePanel();
		tempPanel.add(new EventInfoInput());
		horPanel.add(tempPanel);
		
		VerticalPanel vertPanel = new VerticalPanel();
		vertPanel.add(new MapCalendarView());
		vertPanel.add(new TimeSheetPanel());
		intializeCancelBtn(vertPanel);
		horPanel.add(vertPanel);
		
		this.add(horPanel);
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
