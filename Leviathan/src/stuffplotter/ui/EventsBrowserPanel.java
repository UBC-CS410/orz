package stuffplotter.ui;

import java.util.Arrays;
import java.util.List;

import stuffplotter.shared.Event;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the list of events a user has either:
 * (1) created
 * (2) been invited to
 * (3) accepted invitations to
 */
public class EventsBrowserPanel extends SimplePanel
{
	private List<Event> events;
	private List<String> demoEvents = Arrays.asList("Event 0", "Event 1", "Event 2", "Event 3", "Event 4");
	
	/**
	 * Constructor for EventsBrowser
	 * @pre	true
	 * @post this.isVisible() == true
	 * 
	 */
	public EventsBrowserPanel()
	{
		this.initializeUI();
	}
	
	/**
	 * A helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		EventCell cell = new EventCell();
		
		
		CellList<String> cellList = new CellList<String>(cell);
		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	    
	    cellList.setRowCount(5, true);
	    cellList.setRowData(0, demoEvents);
	    this.add(cellList);
	}
	
	private static class EventCell extends AbstractCell<String>
	{
		/**
		 * Constructor for EventCell.
		 * @pre true
		 * @post true
		 */
		public EventCell() 
		{
			/*
			 * Let the parent class know which browser events our cell
			 * responds to.
			 */
			super("mouseover", "mouseout", "click");
		}
		
		@Override
		public void onBrowserEvent(Context context, Element parent, String value,
				NativeEvent event, ValueUpdater<String> valueUpdater)
		{
			if (value == null)
			{
				return;
			}
			
			super.onBrowserEvent(context, parent, value, event, valueUpdater);
			
			if("mouseover".equals(event.getType()))
			{
				parent.getStyle().setBackgroundColor("blue");
			}
			
			if("mouseout".equals(event.getType()))
			{
				parent.getStyle().setBackgroundColor("white");
			}
			
			if("click".equals(event.getType()))
			{
				Window.alert("You selected " + value);
			}
		}

		@Override
		public void render(Context context,
				String value, SafeHtmlBuilder sb)
		{
			if (value == null) 
			{
		        return;
			}
			// Display the name in big letters.
			sb.appendHtmlConstant("<div style=\"size:200%;font-weight:bold;\">");
			sb.appendEscaped(value);
			sb.appendHtmlConstant("</div>");
			
			// Display the address in normal text.
			sb.appendHtmlConstant("<div style=\"padding-left:10px;color:#aaa;\">");
			sb.appendEscaped(value + "address");
			sb.appendHtmlConstant("</div>");			
		}
		
	}
	
}
