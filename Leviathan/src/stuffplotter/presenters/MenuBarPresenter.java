package stuffplotter.presenters;

import stuffplotter.presenters.ApplicationPagingPresenter.MainView.View;
import stuffplotter.views.ApplicationPagingView;
import stuffplotter.views.global.MenuBarPanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuBarPresenter implements Presenter
{
	public interface MenuBarView
	{
		public Button getHomeBtn();
		public Button getAccountBtn();
		public Button getEventsBtn();
		public Button getFriendsBtn();
		public Button getAchievementsBtn();
		public Widget asWidget();
	}
	
	private final MenuBarView navigator;
	private final ApplicationPagingView pager;
		
	/**
	 * Constructor
	 * @pre
	 * @post
	 */
	public MenuBarPresenter()
	{
		this.navigator = new MenuBarPanel();
		this.pager = new ApplicationPagingView();
	}
	
	private void bind()
	{
		navigator.getHomeBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				pager.showView(View.HOME);
			}	
		});
		
		navigator.getAccountBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				pager.showView(View.ACCOUNT);
			}	
		});
		
		navigator.getEventsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				pager.showView(View.EVENTS);
			}	
		});
		
		navigator.getFriendsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				pager.showView(View.FRIENDS);
			}	
		});
		
		navigator.getAchievementsBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				pager.showView(View.ACHIEVEMENTS);
			}	
		});
		
	}
	
	@Override
	public void go(HasWidgets container)
	{
		bind();
		container.add(navigator.asWidget());
		container.add(pager.asWidget());
	}

}
