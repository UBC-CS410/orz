package stuffplotter.views;

import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsView;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventPreviewPanel;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the events page view.
 */
public class EventsPageView extends HorizontalPanel implements EventsView
{
	private final Button createButton;
	private final Button browseButton;
	private final Button historyButton;
	
	private ScrollDisplayPanel displayPanel;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
		
		createButton = new Button("Create Event");
		browseButton = new Button("View Current Events");
		historyButton = new Button("View Past Events");

		displayPanel = new ScrollDisplayPanel();
		
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
		this.displayPanel.clearDisplay();
		for (int i = 0; i < toDisplay.size(); i++)
		{
			String name, time, desc; 
			name = toDisplay.get(i).getName();
			if(toDisplay.get(i).getDate() == null)
			{
				time = "TBD";
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
	public Long getClickedRowIndex(ClickEvent event)
	{
		long rowIndex = -1;
		HTMLTable.Cell cell = this.displayPanel.getDisplayer().getCellForEvent(event);
		
		if (cell != null) 
		{
			if (cell.getCellIndex() > 0) 
			{
				rowIndex = cell.getRowIndex();
			}
		}
		return rowIndex;
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
