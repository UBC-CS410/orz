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
	}
	
	/**
	 * Method to add a new day with all time slots to the time sheet, a month panel will be
	 * generated where appropriate.
	 * @param month - month of the day(s) to add.
	 */
	public void addDay(Month month, String year, int[] days)
	{
		boolean monthYearFound = false;
		int numOfMonthPanels = this.horPanel.getWidgetCount();
		int i = 0;
		
		// while loop to determine if the month is already in the time sheet panel
		while(!monthYearFound && i < numOfMonthPanels)
		{
			Widget childWidget = this.horPanel.getWidget(i); 
			if(childWidget instanceof MonthPanel)
			{
				MonthPanel monthPanel = (MonthPanel) childWidget;
				if(monthPanel.getMonth().equals(month) && monthPanel.getYear().equals(year))
				{
					// for loop to add the DaySelections to the month panel
					for(Integer day : days)
					{
						((MonthPanel) childWidget).addDay(String.valueOf(day));
					}
					monthYearFound = true;
				}
			}
			i++;
		}
		
		if(!monthYearFound)
		{
			this.horPanel.add(new MonthPanel(month, year, days));
		}
	}
}
