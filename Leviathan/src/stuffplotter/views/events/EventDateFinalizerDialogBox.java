package stuffplotter.views.events;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import stuffplotter.shared.Availability;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;

/*
 * UI class that contains the logic for event owner to finalize the date for their event.
 * TODO: Refractor into view and presenter.
 */
public class EventDateFinalizerDialogBox extends DialogBox
{
	
	private List<Availability> sortedTimeSlots;
	private Date selectedTimeSlot;
	
	/**
	 * Constructor for EventDateSubmitterDialogBox.
	 * @pre true;
	 * @post this.sortedTimeSlots != null;
	 */
	public EventDateFinalizerDialogBox(List<Availability> timeSlots)
	{
		super();
		this.sortedTimeSlots = timeSlots;
		Collections.sort(this.sortedTimeSlots, new Comparator<Availability>(){

			@Override
			public int compare(Availability avl1, Availability avl2)
			{
				return avl1.getTime().compareTo(avl2.getTime());
			}
			
		});
		initializeDialogBox();
	}
	
	/**
	 * Initializes dialog box with radio buttons for each time slot
	 * @pre true;
	 * @post true;
	 */
	private void initializeDialogBox()
	{
		this.setTitle("Scheduler");
		this.setText("Finalize the time for your event");
		
		FlowPanel panel = new FlowPanel();
		for(Availability timeSlot: this.sortedTimeSlots)
		{
			final Date time = timeSlot.getTime();
			
			String label = timeSlot.getTime().toString();
			label += " (" + timeSlot.getVotes() + ")";
			final RadioButton rb = new RadioButton("eventTime", label);
			
			rb.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event)
				{
					if (rb.getValue())
					{
						selectedTimeSlot = time;
					}
					
				}
				
			});
			panel.add(rb);
		}
		
		initializeSubmitButton(panel);
		this.add(panel);
	}
	
	/**
	 * Initializes the submit button for this dialog box
	 * TODO: separate this logic into a presenter
	 * @pre true;
	 * @post true;
	 */
	private void initializeSubmitButton(Panel panel)
	{
		Button button = new Button("Submit");
		button.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				hide();
			};
		});
		panel.add(button);
	}

}
