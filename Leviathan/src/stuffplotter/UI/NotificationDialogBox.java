package stuffplotter.UI;

import stuffplotter.misc.CloseClickHandler;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display a notification after an async call comes back.
 */
public class NotificationDialogBox extends DialogBox
{
	/**
	 * Constructor for NotificationDialogBox.
	 * @pre task != null && message != null;
	 * @post this.isVisible() == true;
	 * @param task - the name of the task the notification is for.
	 * @param message - description of the async call outcome.
	 */
	public NotificationDialogBox(String task, String message)
	{
		super();
		this.initializeUI(message);
		this.center();
		this.setText(task);
		this.setGlassEnabled(true);
	}
	
	/**
	 * Helper method to initialize the elements of the notification window.
	 * @pre message != null;
	 * @post true;
	 * @param message - 
	 */
	private void initializeUI(String message)
	{
		VerticalPanel contentHolder = new VerticalPanel();
		contentHolder.add(new Label(message));
		Button closeBtn = new Button("Close");
		closeBtn.addClickHandler(new CloseClickHandler(this));
		contentHolder.add(closeBtn);
		this.add(contentHolder);
	}
}
