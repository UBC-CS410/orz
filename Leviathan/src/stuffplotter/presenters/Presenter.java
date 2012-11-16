package stuffplotter.presenters;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Interface used by all presenters.
 */
public interface Presenter
{
	/**
	 * Initialize the application.
	 * @pre true;
	 * @post true;
	 * @param container - the container to set as the View associated with the AppController
	 * 					  or the container to set the View for (for children presenters).
	 */
	public void go(final HasWidgets container);
}
