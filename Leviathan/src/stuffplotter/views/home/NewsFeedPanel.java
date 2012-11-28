package stuffplotter.views.home;


import java.util.List;

import stuffplotter.bindingcontracts.AccountModel;
import stuffplotter.bindingcontracts.NotificationModel;
import stuffplotter.presenters.NewsFeedPresenter.NewsFeedView;
import stuffplotter.views.util.ScrollDisplayPanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Place holder for displaying NewsFeed.
 */
public class NewsFeedPanel extends VerticalPanel implements NewsFeedView
{
	private static final int NUMOFCOLUMNS = 1;
	private Label titleLable;
	private NewsFeedDisplayPanel newsFeedDisaply;
	
	public NewsFeedPanel()
	{
		this.initializeUI();
	}

	private void initializeUI()
	{
		this.titleLable = new Label("News Feed of Your Friends");
		this.newsFeedDisaply = new NewsFeedDisplayPanel(NUMOFCOLUMNS);
		
		this.add(this.titleLable);
		this.add(this.newsFeedDisaply);
		
		
	}


	@Override
	public void setNewsFeedData(List<NotificationModel> notifmodels, List<AccountModel> friends)
	{
		this.newsFeedDisaply.setNewsFeedData(notifmodels, friends);
		
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}

}
