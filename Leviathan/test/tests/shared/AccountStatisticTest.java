package tests.shared;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Achievement;

public class AccountStatisticTest extends TestCase
{

	@Test
	public void testAStatCtorNoArgs()
	{
		int numberOfLogins = 0;
		int numberOfHostedEvents = 0;
		int numberOfParticipatedEvents = 0;
		int numberOfFriends = 0;
		ArrayList<Achievement> userAchievements = new ArrayList<Achievement>();
		
		AccountStatistic as = new AccountStatistic();
		
		assertNotNull(as);
		assertEquals(numberOfLogins, as.getNumberOfLogins());
		assertEquals(numberOfHostedEvents, as.getNumberOfHostedEvents());
		assertEquals(numberOfParticipatedEvents, as.getNumberOfParticipatedEvents());
		assertEquals(numberOfFriends, as.getNumberOfFriends());
		assertEquals(userAchievements, as.getUserAchievements());
	}
	
	@Test
	public void testAStatCtor() {
		String accountEmail = "John Doe";
		int numberOfLogins = 0;
		int numberOfHostedEvents = 0;
		int numberOfParticipatedEvents = 0;
		int numberOfFriends = 0;
		ArrayList<Achievement> userAchievements = new ArrayList<Achievement>();
		
		AccountStatistic as = new AccountStatistic(accountEmail);
		
		assertNotNull(as);
		assertEquals(accountEmail, as.getAccountEmail());
		assertEquals(numberOfLogins, as.getNumberOfLogins());
		assertEquals(numberOfHostedEvents, as.getNumberOfHostedEvents());
		assertEquals(numberOfParticipatedEvents, as.getNumberOfParticipatedEvents());
		assertEquals(numberOfFriends, as.getNumberOfFriends());
		assertEquals(userAchievements, as.getUserAchievements());
	}
	
	@Test
	public void testIncreamentLogin()
	{	
		int logininc = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.increamentLogin();
		
		assertEquals(logininc, as.getNumberOfLogins());
	}
	
	@Test
	public void testIncreamentHostedEvents()
	{
		int hostedEvents = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.increamentHostedEvents();
		
		assertEquals(hostedEvents, as.getNumberOfHostedEvents());
	}
	
	@Test
	public void testIncreamentParticipatedEvents()
	{
		int participatedEvents = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.increamentParticipatedEvents();
		
		assertEquals(participatedEvents, as.getNumberOfParticipatedEvents());
	}

}
	


