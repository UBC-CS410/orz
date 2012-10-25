package stuffplotter.UI;

import stuffplotter.UI.MonthPanel.Month;

import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the view of the proposed times for an event.
 */
public class TimeSheetPanel extends SimplePanel
{
	private FlexTable timeSheet = new FlexTable();
	
	/**
	 * Constructor for TimeSheetPanel.
	 */
	public TimeSheetPanel()
	{
		super();
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
		ScrollPanel timeSheetWindow = new ScrollPanel();
		HorizontalPanel horPanel = new HorizontalPanel();
		
		// create test panels
		int[] days = {4, 9, 10};
		int[] days2 = {7, 9, 20};
		MonthPanel monthOct = new MonthPanel(Month.OCTOBER, days);
		MonthPanel monthNov = new MonthPanel(Month.NOVEMBER, days2);
		horPanel.add(monthOct);
		horPanel.add(monthNov);
		timeSheetWindow.add(horPanel);
		this.add(timeSheetWindow);
	}
}
