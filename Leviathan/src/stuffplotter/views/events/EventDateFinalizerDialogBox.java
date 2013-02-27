package stuffplotter.views.events;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.shared.Availability;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Status;
import stuffplotter.signals.EventSchedulerEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/*
 * UI class that contains the logic for event owner to finalize the date for their event.
 * TODO: Refractor into view and presenter.
 */
public class EventDateFinalizerDialogBox extends DialogBox
{
	private Date selectedTimeSlot;
	
	private final List<Availability> sortedTimeSlots;
	private final Event unscheduledEvent;
	private final ServiceRepository serviceRepository;
	private final HandlerManager eventBus;
	
	/**
	 * Constructor for EventDateSubmitterDialogBox.
	 * @pre true;
	 * @post this.sortedTimeSlots != null;
	 */
	public EventDateFinalizerDialogBox(List<Availability> timeslots, Event event, ServiceRepository services, HandlerManager eventbus)
	{
		super();
		
		this.sortedTimeSlots = timeslots;
		this.unscheduledEvent = event;
		this.serviceRepository = services;
		this.eventBus = eventbus;
		
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
		VerticalPanel panel = new VerticalPanel();
		for(Availability timeSlot: this.sortedTimeSlots)
		{
			final Date time = timeSlot.getTime();
			
			String label = timeSlot.getTime().toString();
			label += " (" + timeSlot.getVotes() + " votes)";
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
		
		initializeButtons(panel);
		this.add(panel);
		
		this.setText("Schedule the final time for your event.");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setModal(true);
		this.center();	
		this.show();
	}
	
	/**
	 * Initializes the submit button for this dialog box
	 * TODO: separate this logic into a presenter
	 * @pre true;
	 * @post true;
	 */
	private void initializeButtons(Panel panel)
	{
		HorizontalPanel buttons = new HorizontalPanel();
		
		Button submit = new Button("Submit");
		submit.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				unscheduledEvent.setDate(selectedTimeSlot);
				unscheduledEvent.setStatus(Status.SCHEDULED);
				serviceRepository.getEventService().updateEvent(unscheduledEvent, new AsyncCallback<Void>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Failed to update event...");
						
					}

					@Override
					public void onSuccess(Void result)
					{
						eventBus.fireEvent(new EventSchedulerEvent(unscheduledEvent));
					}
					
				});
				hide();
			};
		});
		
		Button cancel = new Button("Cancel");
		cancel.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				hide();
			};
		});
		
		buttons.add(submit);
		buttons.add(cancel);
		panel.add(buttons);
		
	}

}
