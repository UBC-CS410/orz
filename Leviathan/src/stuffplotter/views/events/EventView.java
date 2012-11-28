package stuffplotter.views.events;

import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.presenters.EventPresenter.EventViewer;
import stuffplotter.shared.Account;
import stuffplotter.shared.Comment;
import stuffplotter.shared.Event;
import stuffplotter.shared.Event.Status;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Anchor;
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
	public void initialize(AccountModel userData, Event eventData)
	{
		Label nameLabel = new Label(eventData.getName());
		nameLabel.setStyleName("eventNameLabel");
	
		Label ownerLabel = new Label("Hosted by: " + eventData.getOwnerID());
		
		Label dateLabel = new Label("");
		switch(eventData.getStatus())
		{
			case PROPOSED:
				dateLabel.setText("Created on: ");
				break;
			case SCHEDULED:
				dateLabel.setText("Scheduled for: " + eventData.getDate().toString());
				break;
			case FINISHED:
				dateLabel.setText("Finished on: ");
				break;
		}
		
		Label durationLabel = new Label("Duration: " + eventData.getDuration());
		Label costLabel = new Label("Cost: " + eventData.getCost());
		Label locationLabel = new Label("Location: " + eventData.getLocation());
		
		EventLocationMapPanel mapPanel = new EventLocationMapPanel();
		if(eventData.getCoordinates() != null)
		{
			LatLng mapPosition = LatLng.newInstance(eventData.getCoordinates()[0], eventData.getCoordinates()[1]);
			mapPanel.getLocation(mapPosition);
		}
		
		Label descriptionLabel = new Label("Description: " + eventData.getDescription());
		
		
		
		this.commentTextBox.setVisible(false);	
		ScrollPanel commentPanel = new ScrollPanel();
		commentPanel.setHeight("480px");
		commentPanel.add(this.commentLabels);
		
		this.add(nameLabel);
		this.add(ownerLabel);
		this.add(dateLabel);
		this.add(durationLabel);
		this.add(costLabel);
		this.add(locationLabel);
		if(eventData.getCoordinates() != null)
		{
			this.add(mapPanel);
		}
		
		this.add(descriptionLabel);
		
		this.add(this.commentButton);
		this.add(this.commentTextBox);
	
		this.add(commentPanel);
		
		this.setStyleName("eventPage");
	}	
}
