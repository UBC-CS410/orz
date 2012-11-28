package stuffplotter.presenters;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EventFriendSelectionPresenter implements Presenter
{
	public interface EventFriendSelectionView
	{
		/**
		 * Retrieve the EventFriendSelectionView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventFriendSelectionView as a widget.
		 */
		public Widget asWidget();
	}
	
	private final EventFriendSelectionView display;
	
	/**
	 * Constructor for the EventFriendSelectionPresenter.
	 * @pre display != null;
	 * @post true;
	 * @param display - the friend selection view to display.
	 */
	public EventFriendSelectionPresenter(EventFriendSelectionView display)
	{
		this.display = display;
	}
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(display.asWidget());
	}
}
