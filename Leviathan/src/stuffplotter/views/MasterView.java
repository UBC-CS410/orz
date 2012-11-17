package stuffplotter.views;

import stuffplotter.presenters.AppController.MasterViewer;
import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.global.MenuBarPanel;
import stuffplotter.views.global.TopBarPanel;

public class MasterView implements MasterViewer
{
	private TopBarPanel topBarPanel;
	private MenuBarPanel menuBarPanel;
	private ApplicationPagingView mainPagingPanel;
	
	/**
	 * Constructor for the MasterView.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public MasterView()
	{
		
	}
	
	@Override
	public TopBarView getTopPanel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuBarView getMenuPanel()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MainView getMainView()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
