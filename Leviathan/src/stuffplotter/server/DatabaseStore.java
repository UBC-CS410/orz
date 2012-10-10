package stuffplotter.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stuffplotter.shared.Event;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

/**
 * Class to manipulate the database.
 */
public class DatabaseStore {
	
	// register the objects to store in the database
	static
	{
		ObjectifyService.register(Event.class);
	}
	
	/**
	 * Empty constructor for the DatabaseStore class.
	 */
	public DatabaseStore()
	{
		// empty constructor
	}
	
	/**
	 * Temporary method for adding events. i.e. this is a test method.
	 * @pre input != null;
	 * @post true;
	 * @param input - string to append to the event name.
	 */
	public void addEvent(String input)
	{	
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			//String connectURL = "jdbc:oracle:thin:@localhost:1521:ug";
			//Connection con = DriverManager.getConnection(connectURL, "ora_e0e7", "a60311081");
			//System.out.println("Connected to Oracle!");
				
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Objectify obj = ObjectifyService.begin();
		List<String> testList = new ArrayList<String>();
		testList.add("Person 1");
		testList.add("Person 2");
		testList.add("Person 3");
		Event testEvent = new Event("Test Event 1" + input, testList);
		obj.put(testEvent);	
	}
}
