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
	private Month month;
	
	public enum Month
	{
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER,
		OCTOBER, NOVEMBER, DECEMBER;
		
		/**
		 * Method to get the display name for the given enum.
		 * @return the name of the month with only the first letter capitalized.
		 */
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
		super();
		this.month = monthName;
		this.add(new Label(monthName.displayName()));
		this.daysHolder = new HorizontalPanel();
		for(int day : days)
		{
			this.daysHolder.add(new DaySelections(String.valueOf(day)));
		}
		this.add(this.daysHolder);
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
	
	/**
	 * Method to add a new day with all time slots to the month panel.
	 * @param dayOfMonth - the day of the month to display.
	 */
	public void addDay(String dayOfMonth)
	{
		this.daysHolder.add(new DaySelections(dayOfMonth));
	}
	
	/**
	 * Method to add a new day with specified time slots.
	 * @param dayOfMonth - the day of the month to display.
	 * @param timeSlots - the time slots to add to the display.
	 */
	public void addDay(String dayOfMonth, int[] timeSlots)
	{
		this.daysHolder.add(new DaySelections(dayOfMonth, timeSlots));
	}
	
	/**
	 * Method to retrieve the month represented by the panel.
	 * @return the month of the panel.
	 */
	public Month getMonth()
	{
		return this.month;
	}
}
