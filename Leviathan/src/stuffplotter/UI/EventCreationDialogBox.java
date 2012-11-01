package stuffplotter.UI;

import stuffplotter.misc.CloseClickHandler;

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
	EventCreationPagedPanel eventPages;
	
	/**
	 * Constructor for the EventCreationDialogBox class.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationDialogBox()
	{
		super();
		VerticalPanel vertPanel = new VerticalPanel();
		HorizontalPanel btnHolder = new HorizontalPanel();
		this.eventPages = new EventCreationPagedPanel();
		vertPanel.add(this.eventPages);
		Button backBtn = new Button("Back");
		Button nextBtn = new Button("Next");
		this.initializeButtons(backBtn, nextBtn);
		btnHolder.add(backBtn);
		btnHolder.add(nextBtn);
		vertPanel.add(btnHolder);
		this.initializeCancelBtn(btnHolder);
		this.add(vertPanel);
		this.center();
		this.setText("Creating New Event");
		this.setGlassEnabled(true);
	}
	
	/**
	 * Helper method to initialize the cancel button for the window, adds the
	 * button to the panel passed to it.
	 * @pre panel != null;
	 * @post true;
	 * @param panel - the panel to add the close button to.
	 */
	private void initializeCancelBtn(Panel panel)
	{
		Button cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new CloseClickHandler(this));
		panel.add(cancelBtn);
	}
	
	/**
	 * Helper method to initialize the next and back buttons for the window.
	 * @pre back != null && next != null;
	 * @post true;
	 * @param back - the back button to initialize.
	 * @param next - the next button to initialize.
	 */
	private void initializeButtons(Button back, Button next)
	{
		back.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventPages.previousPage();
			}	
		});
		
		next.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				eventPages.nextPage();
			}	
		});
	}
}
