package stuffplotter.views;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import stuffplotter.presenters.AppController.MasterViewer;
import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.global.MenuBarPanel;
import stuffplotter.views.global.TopBarPanel;

public class MasterView extends VerticalPanel implements MasterViewer
{
	private TopBarView topBarView;
	private MenuBarView menuBarView;
	
	/**
	 * Constructor for the MasterView.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public MasterView()
	{
		this.topBarView = new TopBarPanel();
		this.menuBarView = new MenuBarPanel();		
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

	/**
	 * Returns this as a widget so that other views can add this
	 * @pre true;
	 * @post true;
	 * @return this;
	 */
	public Widget asWidget()
	{
		return this;
	}
}
