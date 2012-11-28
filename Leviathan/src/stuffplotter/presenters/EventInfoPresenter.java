package stuffplotter.presenters;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EventInfoPresenter implements Presenter
{
	public interface EventInfoView
	{
		/**
		 * Retrieve the EventInfoView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventInfoView as a widget.
		 */
		Widget asWidget();
	}
	
	private final EventInfoView display;
	
	/**
	 * Constructor for the EventInfoPresenter.
	 * @pre display != null;
	 * @post true;
	 * @param display - the display for inputting information for the event.
	 */
	public EventInfoPresenter(EventInfoView display)
	{
		this.display = display;
	}
	
	
	@Override
	public void go(HasWidgets container)
	{
		container.add(this.display.asWidget());
	}
}
