package stuffplotter.views;

import stuffplotter.presenters.AchievementsPagePresenter.AchievementsView;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the achievements page view.
 */
public class AchievementsPageView extends SimplePanel implements AchievementsView
{
	/**
	 * Constructor for the AchievementsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AchievementsPageView()
	{
		super();
		this.add(new Label("Achievements"));
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
