package stuffplotter.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the achievements page view.
 */
public class AchievementsPagePanel extends SimplePanel
{
	/**
	 * Constructor for the AchievementsPagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public AchievementsPagePanel()
	{
		super();
		this.add(new Label("Achievements"));
	}
}
