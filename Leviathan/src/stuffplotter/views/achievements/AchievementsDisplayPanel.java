package stuffplotter.views.achievements;

import java.util.List;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import stuffplotter.shared.Achievement;
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
	public void setAchievementData(List<Achievement> achievements)
	{
		this.clearDisplay();
		
		for(Achievement model : achievements)
		{
			this.addElement(new AchievementPanel(model));
		}
	}
	
	/**
	 * Inner class to display Achievements using data binding from the AchievementModel.
	 */
	public class AchievementPanel extends VerticalPanel
	{
		private Image achievementPicture;
		private Label name;
		private Label description;
		
		/**
		 * Constructor for the AchievementPanel.
		 * @pre model != null;
		 * @post truel;
		 * @param model - the Achievement to data bind with and display.
		 */
		public AchievementPanel(Achievement model)
		{
			this.initializeUI(model);
		}
		
		/**
		 * Helper method to display the Achievement panel information.
		 * @pre model != null;
		 * @post true;
		 * @param model - the achievement to display.
		 */
		private void initializeUI(Achievement model)
		{
			this.achievementPicture = new Image();
			this.name = new Label(model.getName());
			this.description = new Label(model.getDesc());
			
			this.add(this.achievementPicture);
			this.add(this.name);
			this.add(this.description);
		}	
	}
}
