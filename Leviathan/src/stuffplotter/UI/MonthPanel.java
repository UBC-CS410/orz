package stuffplotter.UI;

import java.util.ArrayList;
import java.util.List;

import stuffplotter.shared.DayContainer;
import stuffplotter.shared.MonthContainer;

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
	private String year;
	
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
	 * @pre monthName != null && year != null && days != null;
	 * @post this.isVisible() == true;
	 */
	public MonthPanel(Month monthName, String year, int[] days)
	{
		super();
		this.month = monthName;
		this.year = year;
		this.add(new Label(monthName.displayName() + " " + year));
		this.daysHolder = new HorizontalPanel();
		
		// for loop to populate the DaySelections for the month panel
		for(int day : days)
		{
			this.daysHolder.add(new DaySelections(String.valueOf(day)));
		}
		
		this.add(this.daysHolder);
	}
	
	/**
	 * Method to retrieve the submission submitted by the user.
	 * @pre true;
	 * @post true;
	 * @return the submissions of the user.
	 */
	public MonthContainer retrieveSubmission()
	{
		List<DayContainer> selectedValues = new ArrayList<DayContainer>();
		
		// for loop to retrieve the submissions from each DaySelections
		for (int i = 0; i < this.daysHolder.getWidgetCount(); i++)
		{
			Widget childWidget = this.daysHolder.getWidget(i); 
			if(childWidget instanceof DaySelections)
			{
				selectedValues.add(((DaySelections) childWidget).retrieveSelectedValues());
			}
		}
		
		return new MonthContainer(this.month, this.year, selectedValues);
	}
	
	/**
	 * Method to add a new day with all time slots to the month panel,
	 * if the day is already in the month panel, it will not be added.
	 * @pre dayOfMonth != null;
	 * @post true;
	 * @param dayOfMonth - the day of the month to display.
	 */
	public void addDay(String dayOfMonth)
	{
		boolean dayFound = false;
		int numOfDayPanels = this.daysHolder.getWidgetCount();
		int i = 0;
		
		// while loop to determine if the day is already in the time sheet panel
		while(!dayFound && i < numOfDayPanels)
		{
			Widget childWidget = this.daysHolder.getWidget(i); 
			if(childWidget instanceof DaySelections)
			{
				DaySelections dayPanel = (DaySelections) childWidget;
				if(dayPanel.getDay().equals(dayOfMonth))
				{
					dayFound = true;
				}
			}	
			i++;
		}
		
		if(!dayFound)
		{
			this.daysHolder.add(new DaySelections(dayOfMonth));
		}
	}
	
	/**
	 * Method to add a new day with specified time slots.
	 * @pre dayOfMonth != null && timeSlots != null;
	 * @post true;
	 * @param dayOfMonth - the day of the month to display.
	 * @param timeSlots - the time slots to add to the display.
	 */
	public void addDay(String dayOfMonth, int[] timeSlots)
	{
		this.daysHolder.add(new DaySelections(dayOfMonth, timeSlots));
	}
	
	/**
	 * Method to retrieve the month represented by the panel.
	 * @pre true;
	 * @post true;
	 * @return the month of the panel.
	 */
	public Month getMonth()
	{
		return this.month;
	}
	
	/**
	 * Method to retrieve the year the month is in.
	 * @pre true;
	 * @post true;
	 * @return the year of the month panel.
	 */
	public String getYear()
	{
		return this.year;
	}
}
