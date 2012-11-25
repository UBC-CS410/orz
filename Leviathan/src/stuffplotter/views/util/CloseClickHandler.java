package stuffplotter.views.util;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Custom ClickHandler class to close a given PopupPanel.
 */
public class CloseClickHandler implements ClickHandler
{
	private PopupPanel panel;

	/**
	 * Constructor to set the panel that the CloseClickHandler will close.
	 * @pre panel != null;
	 * @post true;
	 * @param panel - the panel to close when the handler receives a
	 * 				  ClickEvent.
	 */
	public CloseClickHandler(PopupPanel panel)
	{
		super();
		this.panel = panel;
	}
	
	/**
	 * Overridden onClick event to close the PopupPanel CloseClickHandler
	 * contains.
	 * @pre event != null;
	 * @post this.panel.isVisible() == false;
	 * @param event - the ClickEvent received.
	 */
	@Override
	public void onClick(ClickEvent event)
	{
		this.panel.hide();
		this.panel.removeFromParent();
	}
}
