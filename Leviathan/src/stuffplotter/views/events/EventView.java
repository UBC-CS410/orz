package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.presenters.EventViewPresenter.EventViewer;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventView extends VerticalPanel implements EventViewer
{
	private final Button voteButton;
	private final Button commentButton;
	private final TextBox commentTextBox;
	private final VerticalPanel commentPanel;
	
	/**
	 * Constructor for EventView.
	 * @pre true;
	 * @post true;
	 */
	public EventView() 
	{
		this.voteButton = new Button("Choose availabilities");
		this.commentButton = new Button("Submit comment");
		this.commentTextBox = new TextBox();
		this.commentPanel = new VerticalPanel();
	}

	/**
	 * Gets the vote button for submitting availabilities
	 * @pre true;
	 * @post true;
	 */
	@Override
	public HasClickHandlers getVoteButton()
	{
		return this.voteButton;
	}
	
	/**
	 * Gets the comment button for opening the comment box
	 * @pre true;
	 * @post true;
	 */
	@Override
	public HasClickHandlers getCommentButton()
	{
		return this.commentButton;
	}
	
	/**
	 * Gets the comment text box for submitting comments
	 * @pre true;
	 * @post true;
	 */
	@Override
	public HasKeyDownHandlers getCommentTextBox()
	{
		return this.commentTextBox;
	}
	
	/**
	 * Adds the comment text box to the view and hides the comment button
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void openCommentTextBox()
	{
		this.remove(this.commentButton);
		this.commentTextBox.setVisible(true);
	}
	
	/**
	 * Clears the comment text box
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void clearCommentTextBox()
	{
		this.commentTextBox.setValue("");
	}
	
	/**
	 * Gets the text entered in the comment text box
	 * @pre true;
	 * @post true;
	 * @return string containing the text
	 */
	@Override
	public String getCommentText()
	{
		return this.commentTextBox.getValue();
	}
	
	/**
	 * Adds a new comment to the comment panel display
	 * @pre true;
	 * @post true;
	 * @param comment - a new Comment
	 */
	@Override
	public void updateComments(Comment comment)
	{
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label(comment.getUsername()));
		hp.add(new Label(" @ "));
		hp.add(new Label(comment.getTime().toString()));
		this.commentPanel.add(hp);
		this.commentPanel.add(new Label(comment.getContent()));
	}
	
	/**
	 * Adds a list of comments to the comment panel display
	 * @pre true;
	 * @post true;
	 * @return comments - a list of Comment
	 */
	@Override
	public void displayComments(List<Comment> comments)
	{
		this.commentPanel.clear();
		for (int i = 0; i < comments.size(); i++)
		{
			updateComments(comments.get(i));
		}
	}

	/**
	 * Initializes the view with an event's details
	 * @pre true;
	 * @post true;
	 */
	@Override
	public void initialize(Event event)
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
		
		this.commentTextBox.setVisible(false);
		this.add(this.commentTextBox);
		
		this.add(this.commentPanel);
	}
	
}
