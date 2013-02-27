package stuffplotter.views;

import java.util.List;

import stuffplotter.presenters.AchievementsPagePresenter.AchievementsView;
import stuffplotter.shared.Achievement;
import stuffplotter.views.achievements.AchievementsDisplayPanel;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class to display the achievements page view.
 */
public class AchievementsPageView extends HorizontalPanel implements AchievementsView
{
	private static final int NUMBEROFCOLUMNS = 4;
	private AchievementsDisplayPanel achievementDisplayPanel;
	/**
	 * Constructor for the AchievementsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AchievementsPageView()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		achievementDisplayPanel = new AchievementsDisplayPanel(NUMBEROFCOLUMNS);
		this.add(achievementDisplayPanel);
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}

	@Override
	public void setAchievementData(List<Achievement> achievements)
	{
		this.achievementDisplayPanel.setAchievementData(achievements);
	}

	@Override
	public List<Widget> getAchievementButtons()
	{
		return this.achievementDisplayPanel.getElements();
	}
}
