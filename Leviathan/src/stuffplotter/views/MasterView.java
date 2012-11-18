package stuffplotter.views;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.presenters.ApplicationPagingPresenter.MainView;
import stuffplotter.presenters.MasterPresenter.MasterViewer;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.global.MenuBarPanel;
import stuffplotter.views.global.TopBarPanel;

public class MasterView extends VerticalPanel implements MasterViewer
{
	private TopBarView topBarView;
	private MenuBarView menuBarView;
	private MainView mainView; 
	
	/**
	 * Constructor for the MasterView.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public MasterView()
	{
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		// MasterView contains other Views, but Views can only be added via asWidget() in go()
		this.topBarView = new TopBarPanel();
		this.menuBarView = new MenuBarPanel();
		this.mainView = new ApplicationPagingView();
	}
		
	@Override
	public TopBarView getTopPanel()
	{
		return this.topBarView;
	}

	@Override
	public MenuBarView getMenuPanel()
	{
		return this.menuBarView;
	}

	@Override
	public MainView getMainPanel()
	{
		return this.mainView;
	}
	
	@Override
	public Widget asWidget()
	{
		return this;
	}
}
