/**
 * 
 */
package tests.presenters;

import static org.junit.Assert.*;
//import static org.easymock.EasyMock.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountPagePresenter;
import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.shared.Account;
import stuffplotter.views.AccountPageView;

import com.google.gwt.event.shared.HandlerManager;

/**
 * Junit test class for AccountPagePresenter.java
 *
 */
public class AccountPagePresenterTest extends TestCase
{
	private Account appUser;
	private ServiceRepository appServices;
	private HandlerManager eventBus;
	private AccountView accountView;
	
	public void setup() 
	{
		//appServices = createStrictMock(ServiceRepository.class);
		eventBus = new HandlerManager(null);
		accountView = new AccountPageView();
		appUser = new Account();
	}
	
	@Test
	public void testAPPCtor()
	{
		AccountPagePresenter app = new AccountPagePresenter(appServices,eventBus,accountView,appUser);
		
		assertNotNull(app);
		
	}
	
	@Test
	public void testGo()
	{ /*
		AccountPagePresenter app = new AccountPagePresenter(appServices,eventBus,accountView,appUser);
		
		
		assertTrue(app.accountView)*/
	}
	

}
