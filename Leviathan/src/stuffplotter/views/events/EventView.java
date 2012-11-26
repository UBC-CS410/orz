package stuffplotter.views.events;

import java.util.Date;
import java.util.List;

import stuffplotter.presenters.EventPresenter.EventViewer;
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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventView extends VerticalPanel implements EventViewer
{
	private final Anchor commentButton;
	private final TextBox commentTextBox;
	private final VerticalPanel commentLabels;
	
	/**
	 * Constructor for EventView.
	 * @pre true;
	 * @post true;
	 */
	public EventView() 
	{
		this.commentButton = new Anchor("Make a comment");
		this.commentTextBox = new TextBox();
		this.commentLabels = new VerticalPanel();
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
		this.commentLabels.add(hp);
		this.commentLabels.add(new Label(comment.getContent()));
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
		this.commentLabels.clear();
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
		Label nameLabel = new Label(event.getName());
		nameLabel.setStyleName("eventListingLabel");
		
		Label scheduleLabel = new Label();
		if(event.getDate() == null)
		{
			scheduleLabel.setText("Unscheduled");
			scheduleLabel.setStyleName("eventUnscheduledLabel");
		}
		else
		{
			scheduleLabel.setText(event.getDate().toString());
			scheduleLabel.setStyleName("eventScheduledLabel");
		}

		Label ownerLabel = new Label("Host: " + event.getOwner());
		Label costLabel = new Label("Cost: " + event.getCost());
		Label locationLabel = new Label("Location: " + event.getLocation());
		Label descriptionLabel = new Label("Description: " + event.getDescription());
		
		this.commentTextBox.setVisible(false);
		this.add(this.commentTextBox);
		
		ScrollPanel commentPanel = new ScrollPanel();
		commentPanel.setHeight("480px");
		commentPanel.add(this.commentLabels);
		
		this.add(nameLabel);
		this.add(scheduleLabel);
		this.add(ownerLabel);
		this.add(costLabel);
		this.add(locationLabel);
		this.add(descriptionLabel);
		
		this.add(this.commentButton);
	
		this.add(commentPanel);
		
		this.setStyleName("eventPage");
	}
	
}
