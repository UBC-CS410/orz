/**
 * 
 */
package stuffplotter.presenters;

import stuffplotter.presenters.MenuBarPresenter.MenuBarView;
import stuffplotter.presenters.TopBarPresenter.TopBarView;
import stuffplotter.views.MasterView;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class MasterPresenter implements Presenter
{
	
	public interface MasterViewer
	{
		public TopBarView getTopPanel();
		public MenuBarView getMenuPanel();
		public Widget asWidget();
	}
	
	private final MasterView masterView;
	
	public MasterPresenter()
	{
		this.masterView = new MasterView();
	}

	@Override
	public void go(final HasWidgets container)
	{
		Presenter topBarPresenter = new TopBarPresenter();
		topBarPresenter.go((HasWidgets) masterView);
		
		Presenter menuBarPresenter = new MenuBarPresenter();
		menuBarPresenter.go((HasWidgets) masterView);
		
		container.add(masterView.asWidget());
	}

}
