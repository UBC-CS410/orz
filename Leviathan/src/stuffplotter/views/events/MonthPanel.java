package stuffplotter.views.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import stuffplotter.views.util.DateSplitter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
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
	private int monthValue;
	private int yearValue;
	private String month;
	private String year;
	
	/**
	 * Constructor for MonthPanel for when given a day to add.
	 * @param conflictDates 
	 * @pre dates != null;
	 * @post this.isVisible() == true;
	 * @param dates - the day to add to the MonthPanel.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	public MonthPanel(Date date, List<Date> conflictDates)
	{
		super();
		this.initializeUI(date, conflictDates);
	}
	
	/**
	 * Constructor for MonthPanel for when given a list of days to add.
	 * @pre dates != null && !dates.isEmpty();
	 * @post this.isVisible() == true;
	 * @param dates - list of Dates containing the days to add.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	public MonthPanel(List<Date> dates, List<Date> conflictDates)
	{
		super();
		this.initializeUI(dates, conflictDates);
	}
	
	/**
	 * Helper method to initialize the UI when given a Date.
	 * @pre date != null;
	 * @post true;
	 * @param date - the Date containing the day to add.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	private void initializeUI(Date date, List<Date> conflictDates)
	{
		DateSplitter splitter = new DateSplitter(date);
		this.month = splitter.getMonthAsString();
		this.year = splitter.getYearAsString();
		this.monthValue = splitter.getMonth();
		this.yearValue = splitter.getYear();
		HorizontalPanel topHolder = new HorizontalPanel();
		topHolder.add(new Label(splitter.getDayAsString()));
		Anchor close = new Anchor("X");
		close.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				removeFromParent();
			}
		});
		topHolder.add(close);
		this.add(topHolder);
		
		this.daysHolder = new HorizontalPanel();
		this.daysHolder.add(new DaySelections(date, conflictDates));
		
		this.add(this.daysHolder);		
	}
	
	/**
	 * Helper method to initialize the UI when given a list of Dates.
	 * @pre dates != null && !dates.isEmpty();
	 * @post true;
	 * @param dates - list of Dates containing the days to add.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	private void initializeUI(List<Date> dates, List<Date> conflictDates)
	{	
		if(!dates.isEmpty())
		{
			DateSplitter splitter = new DateSplitter(dates.get(0));
			this.add(new Label(splitter.getMonthAsString() + " " + splitter.getYearAsString()));
		}
		
		HashMap<Integer, List<Date>> hashMap = new HashMap<Integer, List<Date>>();
		for (Date date : dates)
		{
			DateSplitter splitter = new DateSplitter(date);
			if (hashMap.get(splitter.getDay()) == null)
			{
				List<Date> startHours = new ArrayList<Date>();
				startHours.add(date);
				hashMap.put(splitter.getDay(), startHours);
			}
			else
			{
				List<Date> startHours = hashMap.get(splitter.getDay());
				startHours.add(date);
				hashMap.put(splitter.getDay(), startHours);
			}
		}
		
		this.daysHolder = new HorizontalPanel();
		for (List<Date> startHours : hashMap.values())
		{
			this.daysHolder.add(new DaySelections(startHours, conflictDates));
		}
		this.add(this.daysHolder);
	}
	
	/**
	 * Method to retrieve the submission submitted by the user.
	 * @pre true;
	 * @post true;
	 * @return the submissions of the user.
	 */
	public List<Date> retrieveSubmission()
	{
		List<Date> selectedValues = new ArrayList<Date>();
		
		// for loop to retrieve the submissions from each DaySelections
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
	 * Method to add a new day with all time slots to the month panel,
	 * if the day is already in the month panel, it will not be added.
	 * @pre dayOfMonth != null;
	 * @post true;
	 * @param date - the Date containing the day to add.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	public void addDay(Date date, List<Date> conflictDates)
	{
		DateSplitter splitter = new DateSplitter(date);
		int dayOfMonth = splitter.getDay();
		
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
				if(dayPanel.getDay() == dayOfMonth)
				{
					dayFound = true;
				}
			}
			
			i++;
		}
		
		if(!dayFound)
		{
			boolean locationFound = false;
			int j = 0;
			
			// while loop to find the correct place to add the day panel
			while(!locationFound && j < numOfDayPanels)
			{
				Widget childWidget = this.daysHolder.getWidget(j); 
				if(childWidget instanceof DaySelections)
				{
					DaySelections dayPanel = (DaySelections) childWidget;
					if(dayOfMonth < dayPanel.getDay())
					{
						locationFound = true;
						this.daysHolder.insert(new DaySelections(date, conflictDates), j);
					}
				}
				
				j++;
			}
			
			if(!locationFound)
			{
				this.daysHolder.add(new DaySelections(date, conflictDates));
			}
		}
	}
	
	/**
	 * Method to add a new day with specified time slots.
	 * @pre dates != null && !dates.isEmtpy();
	 * @post true;
	 * @param dates - the list of Dates containing the time slots to add.
	 * @param conflictDates - the list of time slots that are already taken.
	 */
	public void addDay(List<Date> dates, List<Date> conflictDates)
	{
		this.daysHolder.add(new DaySelections(dates, conflictDates));
	}
	
	/**
	 * Method to retrieve the month represented by the panel.
	 * @pre true;
	 * @post true;
	 * @return the month of the panel.
	 */
	public String getMonth()
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
	
	/**
	 * Method to retrieve the month value.
	 * @pre true;
	 * @post true;
	 * @return the value of the month.
	 */
	public int getMonthValue()
	{
		return this.monthValue;
	}
	
	/**
	 * Method to retrieve the year value.
	 * @pre true;
	 * @post true;
	 * @return the value of the year.
	 */
	public int getYearValue()
	{
		return this.yearValue;
	}
}
