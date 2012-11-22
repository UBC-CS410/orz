package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.presenters.EventViewPresenter.EventViewer;
import stuffplotter.shared.Event;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventView extends VerticalPanel implements EventViewer
{
	private Event event;
	private final Button voteButton;
	private final Button commentButton;
	private final TextBox commentBox;
	
	/**
	 * Constructor for EventView.
	 * @pre true;
	 * @post true;
	 */
	public EventView(Event event) 
	{
		this.event = event;
		this.voteButton = new Button("Choose availabilities");
		this.commentButton = new Button("Submit comment");
		this.commentBox = new TextBox();
		initializeView();
	}

	@Override
	public HasClickHandlers getVoteButton()
	{
		return this.voteButton;
	}
	
	@Override
	public HasClickHandlers getCommentButton()
	{
		return this.commentButton;
	}
	
	@Override
	public void openCommentBox()
	{
		this.remove(this.commentButton);
		this.add(this.commentBox);
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
		
		this.add(this.commentButton);
		VerticalPanel wall = new VerticalPanel();
		//TODO: add comments
		this.add(wall);
	}
	
}
