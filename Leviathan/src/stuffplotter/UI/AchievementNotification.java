/**
 * 
 */
package stuffplotter.UI;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * @author farez
 * Class for an achievement notification
 */
public class AchievementNotification extends PopupPanel {

	/**
	 * Creates a popup notifying user of the achievement
	 * @param autoHide
	 */
	public void APopup(boolean autoHide) {
		setWidget(new Label("You've achieved something!"));
		this.add(new Button("View Achievements"));
	}
	
	
}
