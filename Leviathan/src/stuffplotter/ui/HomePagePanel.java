package stuffplotter.ui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the home page view.
 */
public class HomePagePanel extends SimplePanel
{
	/**
	 * Constructor for the HomePagePanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public HomePagePanel()
	{
		super();
		this.add(new Label("Home"));
	}
}
