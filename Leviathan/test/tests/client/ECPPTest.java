/**
 * 
 */
package tests.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.client.EventCreationPagePopulator;
import stuffplotter.client.services.AccountServiceAsync;
import stuffplotter.shared.Account;

/**
 * JUnit test class for EventCreationPagePopulator.java
 *
 */
public class ECPPTest extends TestCase
{
	
	
	/**
	 * Verify that the instance fields in the class are set correctly
	 */
	@Test
	public void testECPPCtor()
	{
		//String eventCreator = "John";
		//List<String> userFriends = new ArrayList<String>();
		AccountServiceAsync accountServices = null;
		Account userAccount = new Account();
	
		EventCreationPagePopulator ecpp = new EventCreationPagePopulator(accountServices, userAccount); //this constructor can't be called here
		assertNotNull(ecpp);
		
	}

	
	
}
