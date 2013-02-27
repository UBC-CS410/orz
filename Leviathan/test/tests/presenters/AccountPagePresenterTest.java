/**
 * 
 */
package tests.presenters;

import static org.easymock.EasyMock.createStrictMock;
//import static org.easymock.EasyMock.*;
import junit.framework.TestCase;
import org.junit.Test;
import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AccountPagePresenter;
import stuffplotter.presenters.AccountPagePresenter.AccountView;
import stuffplotter.shared.Account;
import com.google.gwt.event.shared.HandlerManager;

/**
 * Junit test class for AccountPagePresenter.java
 *
 */
public class AccountPagePresenterTest extends TestCase
{
//	private Account appUser;
//	private ServiceRepository appServices;
//	private HandlerManager eventBus;
//	private AccountView accountView;

	@Test
	public void testAPPCtor()
	{	
		Account appUser = new Account();
		ServiceRepository appServices = createStrictMock(ServiceRepository.class);
		HandlerManager eventBus = new HandlerManager(null);
		AccountView accountView = createStrictMock(AccountView.class);
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
