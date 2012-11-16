package stuffplotter.shared;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class AchievementNotification extends Notification
{

	public AchievementNotification()
	{
		
	}
	public AchievementNotification(String achievement)
	{
		super(NotificationType.ACHIEVEMENTGET, achievement);
	}

	@Override
	public SimplePanel generateMessage()
	{
		SimplePanel notificationPanel = new SimplePanel();
		notificationPanel.add(new Label("You have unlocked a new achievment " + this.getFrom()));
		return notificationPanel;
	}

}