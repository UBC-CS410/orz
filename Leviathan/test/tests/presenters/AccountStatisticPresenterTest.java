/**
 * 
 */
package tests.presenters;

//import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountStatisticPresenter;
import stuffplotter.presenters.AccountStatisticPresenter.AccountStatisticView;
import stuffplotter.shared.Account;
import stuffplotter.views.AccountPageView;

import com.google.gwt.event.shared.HandlerManager;

/**
 * Junit tests for AccountStatisticPresenter.java
 *
 */
public class AccountStatisticPresenterTest extends TestCase
{

	private Account appUser;
	private ServiceRepository appServices;
	private HandlerManager eventBus;
	private AccountStatisticView statisticView;
	
	public void setup() 
	{
		//appServices = createStrictMock(ServiceRepository.class);
		eventBus = new HandlerManager(null);
		appUser = new Account();
	}
	
	@Test
	public void testASPCtor()
	{
		AccountStatisticPresenter asp = new AccountStatisticPresenter(appServices,eventBus,statisticView,appUser);
		
		assertNotNull(asp);
		
	}
}
