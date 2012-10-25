package stuffplotter.UI;

import stuffplotter.UI.MonthPanel.Month;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the view of the proposed times for an event.
 */
public class TimeSheetPanel extends SimplePanel
{
	private ScrollPanel timeSheetWindow;
	private HorizontalPanel horPanel;
	
	//private FlexTable timeSheet = new FlexTable();
	
	/**
	 * Constructor for TimeSheetPanel.
	 */
	public TimeSheetPanel()
	{
		super();
		timeSheetWindow = new ScrollPanel();
		horPanel = new HorizontalPanel();
		timeSheetWindow.add(horPanel);
		this.add(timeSheetWindow);
		
		/*timeSheet.insertRow(0);
		timeSheet.insertRow(1);
		timeSheet.insertRow(2);
		timeSheet.insertCell(0, 0);
		timeSheet.insertCell(1, 1);
		for(int i = 0; i < 24; i++)
		{
			timeSheet.setWidget(0, i, new CheckBox("asdf" + i));
		}
		for(int i = 0; i < 24; i++)
		{
			timeSheet.setWidget(1, i, new CheckBox("asdf" + i));
		}
		//timeSheet.setWidget(0, 0, new CheckBox("asdf"));
		//timeSheet.setWidget(1, 1, new CheckBox("asdf2"));
		this.add(timeSheet);*/
	}
	
	/**
	 * Method to add a new day with all time slots to the time sheet, a month panel will be
	 * generated where appropriate.
	 * @param month - month of the day(s) to add.
	 */
	public void addDay(Month month, int[] days)
	{
		boolean monthFound = false;
		int numOfMonthPanels = this.horPanel.getWidgetCount();
		int i = 0;
		
		// while loop to determine if the month is already in the time sheet panel
		while(!monthFound && i < numOfMonthPanels)
		{
			Widget childWidget = this.horPanel.getWidget(i); 
			if(childWidget instanceof MonthPanel)
			{
				if(((MonthPanel) childWidget).getMonth().equals(month))
				{
					((MonthPanel) childWidget).addDay(month.displayName(), days);
					monthFound = true;
				}
			}
			i++;
		}
		
		if(!monthFound)
		{
			this.horPanel.add(new MonthPanel(month, days));
		}
	}
}
