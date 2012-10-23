package stuffplotter.UI;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the month and any days within the month for a time sheet.
 */
public class MonthPanel extends VerticalPanel
{
	public enum Month
	{
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER,
		OCTOBER, NOVEMBER, DECEMBER;
		
		public String displayName()
		{
			String allLowerCase = this.toString().toLowerCase();
			Character firstLetter = Character.toUpperCase(allLowerCase.charAt(0));
			return firstLetter + allLowerCase.substring(1);
		}
	}
	
	/**
	 * Constructor for MonthPanel.
	 */
	public MonthPanel(Month monthName, int[] days)
	{
		this.add(new Label(monthName.displayName()));
		HorizontalPanel daysHolder = new HorizontalPanel();
		for(int day : days)
		{
			daysHolder.add(new DaySelections(String.valueOf(day)));
		}
		this.add(daysHolder);
	}
}
