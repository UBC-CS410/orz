package stuffplotter.views.friends;

import java.util.List;

import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Achievement;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class FriendAchievementPopupPanel extends PopupPanel
{
	private final static int NUMBEROFCOLUMNS = 4;
	private Label achievementLabel;
	private ScrollDisplayPanel achievementPanel;
	private FriendAchievementImage firstLog;
	private FriendAchievementImage reach5;
	private FriendAchievementImage reach10;
	private FriendAchievementImage firstFriend;
	private FriendAchievementImage tenFriends;
	private FriendAchievementImage createFirst;
	private FriendAchievementImage create5;
	private FriendAchievementImage firstPerfect;
	private FriendAchievementImage fullAttendance;
	private FriendAchievementImage firstComplete;
	private FriendAchievementImage firstMulti;
	private FriendAchievementImage rateEvent;
	private FriendAchievementImage commentEvent;
	private FriendAchievementImage comment50;
	private FriendAchievementImage complete3SameDay;
	
	private Button backBtn;
	private Button closeBtn;
	private HorizontalPanel buttonHolder;
	
	private VerticalPanel mainPanel;
	
	public FriendAchievementPopupPanel()
	{
		super(false);
		initializeUI();
	}

	private void initializeUI()
	{
		
		this.achievementPanel = new ScrollDisplayPanel(NUMBEROFCOLUMNS);
		this.achievementLabel = new Label("Unlocked Achievements");
		this.achievementLabel.setSize("85px", "85px");
		this.firstLog = new FriendAchievementImage(Achievement.FIRST_LOG_IN);
		this.reach5 = new FriendAchievementImage(Achievement.REACH_LVL_5);
		this.reach10 = new FriendAchievementImage(Achievement.REACH_LVL10);
		this.firstFriend = new FriendAchievementImage(Achievement.ADD_FIRST_FRIEND);
		this.tenFriends = new FriendAchievementImage(Achievement.ADD_10_FRIENDS);
		this.createFirst = new FriendAchievementImage(Achievement.CREATE_FIRST_EVENT);
		this.create5 = new FriendAchievementImage(Achievement.CREATE_5_EVENTS);
		this.firstPerfect = new FriendAchievementImage(Achievement.FIRST_PERFECT_EVENT);
		this.fullAttendance = new FriendAchievementImage(Achievement.FIRST_PERFECT_EVENT);
		this.firstComplete = new FriendAchievementImage(Achievement.COMPLETE_FIRST_EVENT);
		this.firstMulti = new FriendAchievementImage(Achievement.COMPLETE_MULTI_DAY_EVENT);
		this.rateEvent = new FriendAchievementImage(Achievement.RATE_AN_EVENT);
		this.commentEvent = new FriendAchievementImage(Achievement.COMMENT_AN_EVENT);
		this.comment50 = new FriendAchievementImage(Achievement.WRITE_50_COMMENTS);
		this.complete3SameDay = new FriendAchievementImage(Achievement.COMPLETE_3_EVENTS_SAMEDAY);
		
		this.achievementPanel.addElement(achievementLabel);
		this.achievementPanel.addElement(firstLog);
		this.achievementPanel.addElement(reach5);
		this.achievementPanel.addElement(reach10);
		this.achievementPanel.addElement(firstFriend);
		this.achievementPanel.addElement(tenFriends);
		this.achievementPanel.addElement(createFirst);
		this.achievementPanel.addElement(create5);
		this.achievementPanel.addElement(firstPerfect);
		this.achievementPanel.addElement(fullAttendance);
		this.achievementPanel.addElement(firstComplete);
		this.achievementPanel.addElement(firstMulti);
		this.achievementPanel.addElement(rateEvent);
		this.achievementPanel.addElement(commentEvent);
		this.achievementPanel.addElement(comment50);
		this.achievementPanel.addElement(complete3SameDay);
		
		this.backBtn = new Button("Back");
		this.closeBtn = new Button("Close");
		this.buttonHolder = new HorizontalPanel();
		this.buttonHolder.add(this.backBtn);
		this.buttonHolder.add(this.closeBtn);
		
		this.mainPanel = new VerticalPanel();
		this.mainPanel.add(achievementPanel);
		this.mainPanel.add(buttonHolder);
		
		this.add(mainPanel);
	}
	
	public void setStatsData(AccountStatistic stats)
	{
		for(Achievement achievement: stats.getUserAchievements())
		{
			Image achievementImage = this.findAchievementImage(achievement);
			achievementImage.setUrl(achievement.getImg());
		}
	}
	


	public HasClickHandlers getBackButton()
	{
		return this.backBtn;
	}
	
	public HasClickHandlers getCloseButton()
	{
		return this.closeBtn;
	}
	
	/**
	 * Change the visibility of the notification window.
	 * @pre true;
	 * @post true;
	 */
	public void toggleVisibility()
	{
		if(this.isShowing())
		{
			this.hide();
		}
		else
		{
			this.show();
		}
	}
	
	/**
	 * Helper function to get the right AchievementImage
	 * @pre true;
	 * @post true;
	 * @param achievement
	 * @return returns Achievemetn Image
	 */
	private Image findAchievementImage(Achievement achievement)
	{
		FriendAchievementImage achievementImage = new FriendAchievementImage(Achievement.BLANK);
		List<Widget> panels = this.achievementPanel.getElements();
		for(Widget widget:panels)
		{
			if(widget.getClass().equals(achievementImage.getClass()))
			{
				achievementImage = (FriendAchievementImage) widget;
				if(achievementImage.getAch().equals(achievement))
					return achievementImage;
			}
				
		}
		return achievementImage;
	}
	
	
	/**
	 * Inner Class for AchievementImage
	 * @author Matt
	 *
	 */
	public class FriendAchievementImage extends Image
	{
		private Achievement ach;
		
		public FriendAchievementImage(Achievement ach)
		{
			this.ach = ach;
			this.setUrl("/images/lock.jpg");
		}
		
		public Achievement getAch()
		{
			return this.ach;
		}
	}

}
