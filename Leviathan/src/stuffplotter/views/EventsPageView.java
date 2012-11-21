package stuffplotter.views;

import java.util.List;

import stuffplotter.presenters.EventsPagePresenter.EventsPageViewer;
import stuffplotter.shared.Event;
import stuffplotter.views.events.EventsListView;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the events page view.
 */
public class EventsPageView extends HorizontalPanel implements EventsPageViewer
{
	private final Button createButton;
	private final Button listCurrentButton;
	private final Button listPastButton;
	private ScrollDisplayPanel listPanel;
	
	/**
	 * Constructor for the EventsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventsPageView()
	{
		super();
		
		createButton = new Button("Create Event");
		listCurrentButton = new Button("View Current Events");
		listPastButton = new Button("View Past Events");

		listPanel = new ScrollDisplayPanel();
		listPanel.getDisplayer().getElement().getStyle().setCursor(Cursor.DEFAULT);
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.clear();
		VerticalPanel actionBar = new VerticalPanel();
		actionBar.add(createButton);
		actionBar.add(listCurrentButton);
		actionBar.add(listPastButton);
		
		this.add(actionBar);
		this.add(listPanel);
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getCreateButton()
	{
		return this.createButton;
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getListCurrentButton()
	{
		return this.listCurrentButton;
	}

	/**
	 * 
	 */
	@Override
	public HasClickHandlers getListPastButton()
	{
		return this.listPastButton;
	}
	
	@Override
	public HasClickHandlers getEventsList()
	{
		return this.listPanel.getDisplayer();
	}
	
	@Override
	public void hideEventsList()
	{
		this.remove(this.listPanel);
	}

	/**
	 * 
	 */
	@Override
	public void initializeView(List<Event> toDisplay)
	{
		this.initializeUI();
		this.listPanel.clearDisplay();
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
			
			EventsListView rowPanel = new EventsListView(name, time, desc);
			this.listPanel.addElement(rowPanel);
		}
	}

	/**
	 * 
	 */
	@Override
	public int getClickedEvent(ClickEvent event)
	{
		int rowIndex = -1;
		HTMLTable.Cell cell = this.listPanel.getDisplayer().getCellForEvent(event);
		
		if (cell != null) 
		{
			rowIndex = cell.getRowIndex();
		}
		
		return rowIndex;
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
