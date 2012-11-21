package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.presenters.EventViewPresenter.EventViewer;
import stuffplotter.shared.Event;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventView extends VerticalPanel implements EventViewer
{
	private Event event;
	private final Button voteButton;
	
	/**
	 * Constructor for EventView.
	 * @pre true;
	 * @post true;
	 */
	public EventView(Event event) 
	{
		this.event = event;
		this.voteButton = new Button("Choose availabilities");
		initializeView();
	}

	@Override
	public HasClickHandlers getVoteButton()
	{
		return this.voteButton;
	}

	private void initializeView()
	{
		String what = event.getName();
		List<String> who = event.getAttendees();
		Date when = event.getDate();
		String where = event.getLocation();
		String why = event.getDescription();
		
		this.add(new Anchor(what));
		if (when == null)
		{
			this.add(this.voteButton);
		}
		else 
		{
			String date = when.toLocaleString();
			this.add(new Anchor(date));
		}
		this.add(new Anchor(where));
		this.add(new Anchor(why));
		
		VerticalPanel wall = new VerticalPanel();
		//TODO: add comments
		this.add(wall);
	}
	
}
