package tests.shared;

import java.util.ArrayList;
import java.util.List;
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
		
		as.incrementLogin();
		
		assertEquals(logininc, as.getNumberOfLogins());
	}
	
	@Test
	public void testIncreamentHostedEvents()
	{
		int hostedEvents = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.incrementHostedEvents();
		
		assertEquals(hostedEvents, as.getNumberOfHostedEvents());
	}
	
	@Test
	public void testIncreamentParticipatedEvents()
	{
		int participatedEvents = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.incrementParticipatedEvents();
		
		assertEquals(participatedEvents, as.getNumberOfParticipatedEvents());
	}
	
	@Test
	public void testIncreamentFriends()
	{
		int friends = 1;
		
		AccountStatistic as = new AccountStatistic();
		
		as.incrementFriends();
		
		assertEquals(friends, as.getNumberOfFriends());
	}
	
	@Test
	public void testDecrementFriends()
	{
		int friends = -1; //because numberofFriends is set as 0 when an AccountStatistic is created.
		
		AccountStatistic as = new AccountStatistic();
		
		as.decrementFriends();
		
		assertEquals(friends, as.getNumberOfFriends());
	}
	
	@Test
	public void testSetters()
	{
		AccountStatistic as = new AccountStatistic();
		List<Achievement> userAchievements = new ArrayList<Achievement>();
		
		
		as.setAccountEmail("email");
		as.setNumberOfFriends(5);
		as.setNumberOfHostedEvents(5);
		as.setNumberOfLogins(5);
		as.setNumberOfParticipatedEvents(5);
		as.setUserAchievements(userAchievements);
		as.setUserExperience(500);
		as.setUserLevel(5);
		
		assertEquals("email", as.getAccountEmail());
		assertEquals(5, as.getNumberOfFriends());
		assertEquals(5, as.getNumberOfLogins());
		assertEquals(5, as.getNumberOfHostedEvents());
		assertEquals(5, as.getNumberOfParticipatedEvents());
		
		assertEquals(userAchievements, as.getUserAchievements());
		assertEquals(500, as.getUserExperience());
		assertEquals(5, as.getUserLevel());
	}

	@Test
	public void testAddUserAch()
	{
		List<Achievement> achievements = new ArrayList<Achievement>();
		achievements.add(Achievement.ADD_10_FRIENDS);
		achievements.add(Achievement.FIRST_LOG_IN);
		achievements.add(Achievement.ADD_FIRST_FRIEND);
		
		AccountStatistic as = new AccountStatistic();
		
		boolean bool = as.addUserAchievements(achievements);
		
		assertTrue(bool);
		assertEquals(3,as.getUserAchievements().size());
		
		List<Achievement> achievements2 = new ArrayList<Achievement>();
		achievements2.add(Achievement.ADD_10_FRIENDS);
		achievements2.add(Achievement.FIRST_LOG_IN);
		
		boolean bool2 = as.addUserAchievements(achievements2);
		assertTrue(bool2);
	}
}
	


