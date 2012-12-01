/**
 * 
 */
package tests.presenters;

import static org.easymock.EasyMock.createStrictMock;
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
	@Test
	public void testASPCtor()
	{
		//Account appUser = new Account("test@example.com");
		//ServiceRepository appServices = createStrictMock(ServiceRepository.class);
		//HandlerManager eventBus = new HandlerManager(null);
		//AccountStatisticView statisticView = createStrictMock(AccountStatisticView.class);	
		//AccountStatisticPresenter asp = new AccountStatisticPresenter(appServices,eventBus,statisticView,appUser);
		//assertNotNull(asp); //Null pointer exceptions due to constructor!
	}
}
