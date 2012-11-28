package stuffplotter.views.friends;

import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.views.util.InfoPanel;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FriendPopupPanel extends PopupPanel
{
	private VerticalPanel mainPanel;
	private VerticalPanel accountInfoPanel;
	private VerticalPanel statsInfoPanel;
	private HorizontalPanel infoHolder;
	private HorizontalPanel imageHolder;
	private HorizontalPanel buttonHolder;
	private Label friendInfo;
	private Image profilePic;
	private Image badgePic;
	private Button closeBtn;
	private Button achievementBtn;
	
	private InfoPanel nameField;
	private InfoPanel emailField;
	private InfoPanel titleField;
	private InfoPanel phoneField;
	private InfoPanel ageField;
	
	private InfoPanel userLevel;
	private InfoPanel userExperience;
	private InfoPanel numOfLogins;
	private InfoPanel numOfHostedEvents;
	private InfoPanel numOfParticipatedEvents;
	private InfoPanel numOfFriends;
	
	
	public FriendPopupPanel()
	{
		super(false);
		initializeUI();
	}

	private void initializeUI()
	{
		this.mainPanel = new VerticalPanel();
		this.friendInfo = new Label();
		this.mainPanel.add(this.friendInfo);
		
		
		this.accountInfoPanel = new VerticalPanel();
		this.profilePic = new Image();
		this.profilePic.setPixelSize(85, 85);
		this.badgePic = new Image();
		this.imageHolder = new HorizontalPanel();
		this.imageHolder.add(this.profilePic);
		this.imageHolder.add(this.badgePic);
		
		this.nameField = new InfoPanel("Name", "");
		this.emailField = new InfoPanel("Email", "");
		this.titleField = new InfoPanel("Title", "");
		this.phoneField = new InfoPanel("Phone", "");
		this.ageField = new InfoPanel("Age", "");
		this.accountInfoPanel.add(imageHolder);
		this.accountInfoPanel.add(nameField);
		this.accountInfoPanel.add(emailField);
		this.accountInfoPanel.add(titleField);
		this.accountInfoPanel.add(phoneField);
		this.accountInfoPanel.add(ageField);
		
		this.statsInfoPanel = new VerticalPanel();
		this.userLevel = new InfoPanel("Level", "");
		this.userExperience = new InfoPanel("Experience", "");
		this.numOfLogins  = new InfoPanel("Number of Page Views", "");
		this.numOfHostedEvents = new InfoPanel("Number of Hosted Events", "");
		this.numOfParticipatedEvents = new InfoPanel("Number of Participated Events", "");
		this.numOfFriends = new InfoPanel("Number of Friends", "");
		this.statsInfoPanel.add(userLevel);
		this.statsInfoPanel.add(userExperience);
		this.statsInfoPanel.add(numOfLogins);
		this.statsInfoPanel.add(numOfHostedEvents);
		this.statsInfoPanel.add(numOfParticipatedEvents);
		this.statsInfoPanel.add(numOfFriends);
		
		
		
		this.infoHolder = new HorizontalPanel();
		this.infoHolder.add(accountInfoPanel);
		this.infoHolder.add(statsInfoPanel);
		
		this.mainPanel.add(infoHolder);
		
		this.closeBtn = new Button("Close");
		this.achievementBtn = new Button("Achievements");
		this.buttonHolder = new HorizontalPanel();
		this.buttonHolder.add(achievementBtn);
		this.buttonHolder.add(closeBtn);
		
		
		this.mainPanel.add(buttonHolder);
		this.add(mainPanel);
	}
	
	public void setAccountAndStatsData(Account account, AccountStatistic stats)
	{
		this.friendInfo.setText(account.getUserFullName()+"'s Information and Stats");
		if(account.getUserProfilePicture()!=null)
			this.profilePic.setUrl(account.getUserProfilePicture());
		else
			this.profilePic.setUrl("http://i983.photobucket.com/albums/ae312/robzile/Mario-Box-question-mark.gif");
		this.badgePic.setUrl("/images/blank.jpg");
		
		this.nameField.setValue(account.getUserFullName()); 
		this.emailField.setValue(account.getUserEmail());
		this.titleField.setValue(account.getUserTitle());
		this.phoneField.setValue(account.getUserPhone());
		this.ageField.setValue(account.getUserAge()); 
		this.userLevel.setValue(String.valueOf(stats.getUserLevel())); 
		this.userExperience.setValue(String.valueOf(stats.getUserExperience()));
		this.numOfLogins.setValue(String.valueOf(stats.getNumberOfLogins())); 
		this.numOfHostedEvents.setValue(String.valueOf(stats.getNumberOfHostedEvents())); 
		this.numOfParticipatedEvents.setValue(String.valueOf(stats.getNumberOfParticipatedEvents()));
		this.numOfFriends.setValue(String.valueOf(stats.getNumberOfFriends()));
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
	
	public HasClickHandlers getCloseBtn()
	{
		return this.closeBtn;
	}
	
	public HasClickHandlers getAchievementBtn()
	{
		return this.achievementBtn;
	}
}
