package stuffplotter.shared;

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
		String display = "You unlocked the achievement, "+ach.getName();
		this.notificationDisplay = display;
	}

	/**
	 * Serial version for AchievementNotification. 
	 */
	private static final long serialVersionUID = -2958628524619994717L;
}