package stuffplotter.views.util;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Helper class to split a given Date into its Year, Month, Day, and Hour.
 */
public class DateSplitter implements Serializable
{	
	private Date date;
	private String yearAsString;
	private String monthAsString;
	private String dayAsString;
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
		DateTimeFormat dayFormat = DateTimeFormat.getFormat("MMMM,M,d,yyyy,H");
		String[] calendarValues = dayFormat.format(date).toString().split(",");
		this.monthAsString = calendarValues[0];
		this.month = Integer.valueOf(calendarValues[1]);
		this.dayAsString = calendarValues[2];
		this.day = Integer.valueOf(calendarValues[2]);
		this.yearAsString = calendarValues[3];
		this.year = Integer.valueOf(calendarValues[3]);
		this.hour = Integer.valueOf(calendarValues[4]);
	}
	
	/**
	 * Retrieve the original Date the DateSplitter split.
	 * @pre true;
	 * @post true;
	 * @return the original Date the DateSplitter split.
	 */
	public Date getDate()
	{
		return this.date;
	}

	/**
	 * Retrieve the year that was extracted from the given Date as its string value.
	 * @pre true;
	 * @post true;
	 * @return the year that was extracted from the given Date as its string value.
	 */
	public String getYearAsString()
	{
		return this.yearAsString;
	}
	
	/**
	 * Retrieve the month that was extracted from the given Date as its string value.
	 * @pre true;
	 * @post true;
	 * @return the month as a string.
	 */
	public String getMonthAsString()
	{
		return this.monthAsString;
	}

	/**
	 * Retrieve the day that was extracted from the given Date as its string value.
	 * @pre true;
	 * @post true;
	 * @return the day as a string.
	 */
	public String getDayAsString()
	{
		return this.dayAsString;
	}
	
	/**
	 * Retrieve the year that was extracted from the given Date.
	 * @pre true;
	 * @post true;
	 * @return the year that was extracted from the given Date.
	 */
	public int getYear()
	{
		return this.year;
	}

	/**
	 * Retrieve the month that was extracted from the given Date.
	 * @pre true;
	 * @post true;
	 * @return the month that was extracted from the given Date.
	 */
	public int getMonth()
	{
		return this.month;
	}

	/**
	 * Retrieve the day that was extracted from the given Date.
	 * @pre true;
	 * @post true;
	 * @return the day that was extracted from the given Date.
	 */
	public int getDay()
	{
		return this.day;
	}

	/**
	 * Retrieve the hour that was extracted from the given Date.
	 * @pre true;
	 * @post true;
	 * @return the hour that was extracted from the given Date.
	 */
	public int getHour()
	{
		return this.hour;
	}

	/**
	 * Serial version for the DateSplitter.
	 */
	private static final long serialVersionUID = 1151176561169648728L;
}
