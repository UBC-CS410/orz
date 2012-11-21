package stuffplotter.shared;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class AchievementNotification extends Notification
{
	
	private Achievement achievement;
	
	public AchievementNotification()
	{
		
	}
	public AchievementNotification(Achievement ach, String user)
	{
		super(NotificationType.ACHIEVEMENTGET, user, user);
		this.achievement = ach;
		this.setNotificationDisplay(ach);
		
		
	}
	
	
	public void setNotificationDisplay(Achievement ach){
		String display = "You have unlocked the achievement, "+ach.getName();
		this.notificationDisplay = display;
	}



}