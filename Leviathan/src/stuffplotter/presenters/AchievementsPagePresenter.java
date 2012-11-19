package stuffplotter.presenters;

import stuffplotter.client.services.ServiceRepository;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class for the Achievements Page presenter.
 */
public class AchievementsPagePresenter implements Presenter
{
	public interface AchievementsView
	{
		public Widget asWidget();
		//public AchievementsPanel getAchievementsPanel(); // create presenter for this 
	}
	
	private final ServiceRepository appServices;
	private final HandlerManager eventBus;
	private final AchievementsView achievementsView;
	
	/**
	 * Constructor for the AchievementsPagePresenter.
	 * @pre @pre appServices != null && eventBus != null && display != null && user != null;
	 * @post true;
	 * @param appServices - the mapped services
	 * @param eventBus - the global event bus
	 * @param display - the view to present
	 * @param user - the current user
	 */
	public AchievementsPagePresenter(ServiceRepository appServices, HandlerManager eventBus, AchievementsView display)
	{
		this.appServices = appServices;
		this.eventBus = eventBus;
		this.achievementsView = display;
	}

	/**
	 * Bind view components to handlers
	 * @pre true
	 * @post true
	 */
	private void bind()
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Present the view
	 * @pre true;
	 * @post this.achievementsView.isVisible() == true;
	 */
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(this.achievementsView.asWidget());
	}
}
