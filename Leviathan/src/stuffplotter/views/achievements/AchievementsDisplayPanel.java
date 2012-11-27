package stuffplotter.views.achievements;

import java.util.List;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.shared.Achievement;
import stuffplotter.views.util.InfoPanel;
import stuffplotter.views.util.ScrollDisplayPanel;
import stuffplotter.views.util.VerticalInfoPanel;

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
		AchievementPanel blank = new AchievementPanel(Achievement.BLANK);
		AchievementPanel firstLog = new AchievementPanel(Achievement.FIRST_LOG_IN);
		AchievementPanel reach5 = new AchievementPanel(Achievement.REACH_LVL_5);
		AchievementPanel reach10 = new AchievementPanel(Achievement.REACH_LVL10);
		AchievementPanel firstFriend = new AchievementPanel(Achievement.ADD_FIRST_FRIEND);
		AchievementPanel tenFriends = new AchievementPanel(Achievement.ADD_10_FRIENDS);
		AchievementPanel createFirst = new AchievementPanel(Achievement.CREATE_FIRST_EVENT);
		AchievementPanel create5 = new AchievementPanel(Achievement.CREATE_5_EVENTS);
		AchievementPanel firstPerfect = new AchievementPanel(Achievement.FIRST_PERFECT_EVENT);
		AchievementPanel fullAttendance = new AchievementPanel(Achievement.FULL_EVENT_ATTENDANCE);
		AchievementPanel firstComplete  = new AchievementPanel(Achievement.COMPLETE_FIRST_EVENT);
		AchievementPanel firstMulti = new AchievementPanel(Achievement.COMPLETE_MULTI_DAY_EVENT);
		AchievementPanel rateEvent = new AchievementPanel(Achievement.RATE_AN_EVENT);
		AchievementPanel commentEvent = new AchievementPanel(Achievement.COMMENT_AN_EVENT);
		AchievementPanel comment50 = new AchievementPanel(Achievement.WRITE_50_COMMENTS);
		AchievementPanel complete3SameDay = new AchievementPanel(Achievement.COMPLETE_3_EVENTS_SAMEDAY);
		
		this.addElement(blank);
		this.addElement(firstLog);
		this.addElement(reach5);
		this.addElement(reach10);
		this.addElement(firstFriend);
		this.addElement(tenFriends);
		this.addElement(createFirst);
		this.addElement(create5);
		this.addElement(firstPerfect);
		this.addElement(fullAttendance);
		this.addElement(firstComplete);
		this.addElement(firstMulti);
		this.addElement(rateEvent);
		this.addElement(commentEvent);
		this.addElement(comment50);
		this.addElement(complete3SameDay);	
	}
	
	public AchievementPanel getAchievementPanel(Achievement achievement){
		
		AchievementPanel achievemeantPanel = new AchievementPanel(Achievement.BLANK);
		
		List<Widget> panels = this.getElements();
		for(Widget widget : panels)
		{
			if(widget.getClass().equals(achievemeantPanel.getClass()))
			{
				achievemeantPanel = (AchievementPanel) widget;
				if(achievemeantPanel.getAch().equals(achievement))
					return achievemeantPanel;
			}
		}
		return achievemeantPanel;
	}
	
	/**
	 * Display the given list of AchievementModel.
	 * @pre achievements != null;
	 * @post true;
	 * @param achievements - the list of AchievementModel to display.
	 */
	public void setAchievementData(List<Achievement> achievements)
	{
	
		for(Achievement model : achievements)
		{
			AchievementPanel modelPanel = this.getAchievementPanel(model);
			modelPanel.setValues(model);
		}
	}
	
	/**
	 * Inner class to display Achievements using data binding from the AchievementModel.
	 */
	public class AchievementPanel extends VerticalPanel
	{
		private Achievement ach;
		private PushButton achIcon;
		private VerticalInfoPanel achName;
		private VerticalInfoPanel achDesc;
		private VerticalInfoPanel achMsg;
		
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
			if(!model.equals(Achievement.BLANK))
			{
				this.ach = model;
				this.achIcon = new PushButton(new Image("images/lock.jpg"));
				this.achName = new VerticalInfoPanel("Achievement", "Locked");
				this.achDesc = new VerticalInfoPanel("Description", model.getDesc());
				this.achMsg = new VerticalInfoPanel("Message", "Locked");
			}
			else
			{
				this.ach = Achievement.BLANK;
				this.achIcon = new PushButton(new Image("images/blank.jpg"));
				this.achName = new VerticalInfoPanel("Achievement", model.getName());
				this.achDesc = new VerticalInfoPanel("Description", model.getDesc());
				this.achMsg = new VerticalInfoPanel("Message", model.getMsg());
			}
			this.achIcon.setSize("85px", "85px");
			this.add(achIcon);
			this.add(achName);
			this.add(achDesc);
			this.add(achMsg);
			this.setWidth("200px");
		}
		
		/**
		 * Gets the Achievement associated with this Panel
		 * @pre true;
		 * @post true;
		 * @return Achievement
		 */
		public Achievement getAch()
		{
			return this.ach;
		}
		
		/**
		 * Sets the values of the VeritcalInfoPanel
		 * @pre true;
		 * @post true;
		 * @param model
		 */
		public void setValues(Achievement model)
		{
			this.achIcon.getUpFace().setImage(new Image(model.getImg()));
			this.achIcon.getDownFace().setImage(new Image(model.getImg()));
			this.achName.setValue(model.getName());
			this.achMsg.setValue(model.getMsg());
			
		}
	}
}
