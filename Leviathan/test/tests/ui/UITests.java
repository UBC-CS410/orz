package tests.ui;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import stuffplotter.views.account.AccountPanel;
import stuffplotter.views.events.AvailabilitySubmitterDialogBox;
import stuffplotter.shared.Account;
import stuffplotter.shared.MonthContainer;

public class UITests extends GWTTestCase
{
	//tests to ensure that method for UI work properly where applicable
	private static AccountPanel accountPanel;
	private static Account userAccount;
	private static AvailabilitySubmitterDialogBox userAvail;
	
	//setup all the necessary variables
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		userAccount = new Account("UserName", "Email");
		accountPanel = new AccountPanel(userAccount);
		userAvail = new AvailabilitySubmitterDialogBox(new ArrayList<MonthContainer>());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		userAccount = null;
		accountPanel = null;
		userAvail = null;
	}
	
	/**
	 * Test to ensure that the account gets stored in the AccountPanel and is retrievable.
	 */
	@Test
	public void testGetAccount()
	{
		assertNotNull(accountPanel.getAccount());
		assertEquals("UserName", accountPanel.getAccount().getUserName());
		assertEquals("UserName", accountPanel.getAccount().getUserEmail());
	}
	
	/**
	 * Test to ensure that the availabilities are retrieved properly from the 
	 * AvailabilitySubmitterDialogBox. 
	 */
	@Test
	public void testRetrieveSubmissionAvailDialogBox()
	{
		assertEquals(0, userAvail.retrieveSubmissions());
	}

	@Override
	public String getModuleName()
	{
		return "stuffplotter.Leviathan"; 
	}
	
	
}

