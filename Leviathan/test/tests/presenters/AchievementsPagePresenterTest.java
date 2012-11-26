/**
 * 
 */
package tests.presenters;

import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import stuffplotter.client.services.ServiceRepository;
import stuffplotter.presenters.AchievementsPagePresenter;
import stuffplotter.presenters.AchievementsPagePresenter.AchievementsView;


import com.google.gwt.event.shared.HandlerManager;

/**
 * Junit tests for AchievementsPagePresenter.java
 *
 */
public class AchievementsPagePresenterTest extends TestCase
{

	
			
			private ServiceRepository appServices;
			private HandlerManager eventBus;
			private AchievementsView achievementView;
			
			public void setup() 
			{
				appServices = createStrictMock(ServiceRepository.class);
				eventBus = new HandlerManager(null);
				
				
			}
			
			@Test
			public void testAPPCtor()
			{
				AchievementsPagePresenter app = new AchievementsPagePresenter(appServices,eventBus,achievementView);
				
				assertNotNull(app);
				
			}

	

}
