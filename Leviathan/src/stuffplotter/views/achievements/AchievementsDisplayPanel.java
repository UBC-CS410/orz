package stuffplotter.views.achievements;

import java.util.List;

import com.google.gwt.user.client.ui.VerticalPanel;

import stuffplotter.bindingcontracts.AchievementModel;
import stuffplotter.views.util.ScrollDisplayPanel;

/**
 * Class to display achievements.
 */
public class AchievementsDisplayPanel extends ScrollDisplayPanel
{
	/**
	 * Constructor for the AchievementDisplayPanel.
	 * @pre numOfColumns > 0;
	 * @post true;
	 * @param numOfColumns - the number of columns to display the achievements as.
	 */
	public AchievementsDisplayPanel(int numOfColumns)
	{
		super(numOfColumns);
	}
	
	/**
	 * Display the given list of AchievementModel.
	 * @pre achievements != null;
	 * @post true;
	 * @param achievements - the list of AchievementModel to display.
	 */
	public void setAchievementData(List<AchievementModel> achievements)
	{
		for(AchievementModel model : achievements)
		{
			this.addElement(new AchievementPanel(model));
		}
	}
	
	/**
	 * Inner class to display Achievements using data binding from the AchievementModel.
	 */
	public class AchievementPanel extends VerticalPanel
	{
		/**
		 * Constructor for the AchievementPanel.
		 * @pre model != null;
		 * @post truel;
		 * @param model - the Achievement to data bind with and display.
		 */
		public AchievementPanel(AchievementModel model)
		{
			// TO DO
		}
	}
}
