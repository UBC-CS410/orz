package stuffplotter.presenters;

import stuffplotter.views.global.TopBarPanel;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TopBarPresenter implements Presenter
{
	public interface TopBarView
	{
		public Widget asWidget();
		// TO DO
	}
	
	private final TopBarView header;
	
	public TopBarPresenter()
	{
		this.header = new TopBarPanel();
	}
	
	@Override
	public void go(final HasWidgets container)
	{
		container.add(header.asWidget());
	}

}
