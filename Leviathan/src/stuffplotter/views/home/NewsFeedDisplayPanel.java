package stuffplotter.views.home;

import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.views.util.ScrollDisplayPanel;

public class NewsFeedDisplayPanel extends ScrollDisplayPanel
{
	
	public NewsFeedDisplayPanel(int numOfColumns)
	{
		super(numOfColumns);
		
	}


	public void setNewsFeedData(List<NotificationModel> notifmodels,List<AccountModel> friends)
	{
		this.clearDisplay();
		for(NotificationModel notification:notifmodels)
		{
			AccountModel account = findAccount(notification.getFrom(),friends);
			NewsPanel newsPanel = new NewsPanel(notification,account);
			this.addElement(newsPanel);
		}
		
	}

	private AccountModel findAccount(String user, List<AccountModel> friends)
	{
		for(AccountModel account: friends)
		{
			if(account.getUserEmail().equals(user))
				return account;
		}
		return friends.get(0);
	}

	/**
	 * Inner class to display Newsfeed items using data binding from the NotificaionModel
	 *
	 */
	public class NewsPanel extends HorizontalPanel
	{
		private Image friendImage;
		private Image friendBadge;
		private Label newsDesc;
		private Label newsTime;
		
		public NewsPanel(NotificationModel model, AccountModel account)
		{
			this.initializeUI(model, account);

		}
		
		private void initializeUI(NotificationModel model, AccountModel account)
		{
			if(account.getUserProfilePicture()!=null)
				this.friendImage = new Image(account.getUserProfilePicture());
			else
				this.friendImage = new Image("images/profile.jpg");
			this.friendImage.setPixelSize(50, 50);
			this.friendBadge = new Image("images/blank.jpg");
			this.friendBadge.setPixelSize(50,50);
			String newsString = model.getNotificationDisplay();
			newsString = newsString.replace("You", account.getUserFullName());
			this.newsDesc = new Label(newsString);
			this.newsTime = new Label(model.getNotificationTime().toString());;
			VerticalPanel newsHolder = new VerticalPanel();
			newsHolder.add(this.newsDesc);
			newsHolder.add(this.newsTime);
			this.add(friendImage);
			this.add(friendBadge);
			this.add(newsHolder);
		}
	}
}
