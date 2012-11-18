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
	 * @param container - the container to add the views to (in general).
	 */
	public void go(final HasWidgets container);
}
