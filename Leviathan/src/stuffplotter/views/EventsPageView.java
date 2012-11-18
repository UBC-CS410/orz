package stuffplotter.views;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsView;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventCreationDialogBox;
import stuffplotter.views.events.EventPreviewPanel;
import stuffplotter.views.events.EventsBrowserPanel;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the events page view.
 */
public class EventsPageView extends HorizontalPanel implements EventsView
{
	private final Button createButton;
	private final Button browseButton;
	private final Button historyButton;
	
	private ScrollDisplayPanel displayPanel;
	private List<Long> displayIds;
	
	
	private EventCreationDialogBox eventCreation;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
		
		browseButton = new Button("View Current Events");
		historyButton = new Button("View Past Events");
		createButton = new Button("Create Event");
		displayPanel = new ScrollDisplayPanel();
		displayIds = new ArrayList<Long>();
		
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		VerticalPanel actionBar = new VerticalPanel();
		actionBar.add(createButton);
		actionBar.add(browseButton);
		actionBar.add(historyButton);
		
		this.add(actionBar);
		this.add(displayPanel);
		
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getCreateEventBtn()
	{
		return this.createButton;
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getCurrentEventsBtn()
	{
		return this.browseButton;
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getPastEventsBtn()
	{
		return this.historyButton;
	}

	/**
	 * 
	 */
	@Override
	public void setDisplay(List<Event> toDisplay)
	{
		this.displayIds.clear();
		
		this.displayIds.add(new Long(0));
		for (int i = 0; i < toDisplay.size(); i++)
		{
			String name, time, desc; 
			name = toDisplay.get(i).getName();
			if(toDisplay.get(i).getDate() == null)
			{
				time = "Time: TBD";
			}
			else
			{
				time = toDisplay.get(i).getDate().toString();
			}
			desc = toDisplay.get(i).getDescription();
			
			EventPreviewPanel rowPanel = new EventPreviewPanel(name, time, desc);
			this.displayPanel.addElement(rowPanel);
		}
	}

	/**
	 * 
	 */
	@Override
	public Long getClickedId(ClickEvent event)
	{
		long clickedId = -1;
		HTMLTable.Cell cell = this.displayPanel.getDisplayer().getCellForEvent(event);
		
		if (cell != null) 
		{
			if (cell.getCellIndex() > 0) 
			{
				clickedId = this.displayIds.get(cell.getRowIndex());
			}
		}
		
		return clickedId;
	}
	
	
	

}
