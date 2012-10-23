package stuffplotter.UI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the month and any days within the month for a time sheet.
 */
public class MonthPanel extends VerticalPanel
{
	private HorizontalPanel daysHolder;
	
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
		daysHolder = new HorizontalPanel();
		for(int day : days)
		{
			daysHolder.add(new DaySelections(String.valueOf(day)));
		}
		this.add(daysHolder);
	}
	
	/**
	 * Method to retrieve the submission submitted by the user.
	 * @return the submissions of the user.
	 */
	public List<Integer> retrieveSubmission()
	{
		List<Integer> selectedValues = new ArrayList<Integer>();
		
		for (int i = 0; i < this.daysHolder.getWidgetCount(); i++)
		{
			Widget childWidget = this.daysHolder.getWidget(i); 
			if(childWidget instanceof DaySelections)
			{
				selectedValues.addAll(((DaySelections) childWidget).retrieveSelectedValues());
			}
		}
		
		return selectedValues;
	}
}
