package stuffplotter.views.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Helper class to split a given Date into its Year, Month, Day, and Hour as ints.
 */
public class DateSplitter
{
	private Date date;
	private String monthAsString;
	private int year;
	private int month;
	private int day;
	private int hour;
	
	/**
	 * Constructor for the DateSplitter.
	 */
	public DateSplitter(Date date)
	{
		this.date = date;
		this.extractValues(date);
	}
	
	/**
	 * Extract the Year, Month, Day, and Hour from the given Date.
	 * @pre date != null;
	 * @post true;
	 */
	private void extractValues(Date date)
	{
		DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,d,yyyy,H");
		String[] calendarValues = dayFormat.format(date).toString().split(",");
		this.month = Integer.valueOf(calendarValues[0]); // will need to fix this
		this.monthAsString = calendarValues[0];
		this.day = Integer.valueOf(calendarValues[1]);
		this.year = Integer.valueOf(calendarValues[2]);
		this.hour = Integer.valueOf(calendarValues[3]);
	}
}
