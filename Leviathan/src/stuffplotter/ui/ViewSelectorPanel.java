package stuffplotter.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Class to display the links to change the view for the application.
 */
public class ViewSelectorPanel extends HorizontalPanel
{
	private Button homeBtn;
	private Button accountBtn;
	private Button eventsBtn;
	private Button achievementsBtn;
	
	/**
	 * Constructor for the ViewSelectorPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public ViewSelectorPanel()
	{
		super();
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.homeBtn = new Button("Home");
		this.accountBtn = new Button("Account");
		this.eventsBtn = new Button("Events");
		this.achievementsBtn = new Button("Achievements");
		
		this.add(this.homeBtn);
		this.add(this.accountBtn);
		this.add(this.eventsBtn);
		this.add(this.achievementsBtn);
	}
	
	/**
	 * Retrieves the Home button.
	 * @pre true;
	 * @post true;
	 * @return the home button.
	 */
	public Button getHomeBtn()
	{
		return this.homeBtn;
	}

	/**
	 * Retrieves the Account button.
	 * @pre true;
	 * @post true;
	 * @return the account button.
	 */
	public Button getAccountBtn()
	{
		return this.accountBtn;
	}

	/**
	 * Retrieves the Events button.
	 * @pre true;
	 * @post true;
	 * @return the events button.
	 */
	public Button getEventsBtn()
	{
		return this.eventsBtn;
	}

	/**
	 * Retrieves the achievements button.
	 * @pre true;
	 * @post true;
	 * @return the achievements button.
	 */
	public Button getAchievementsBtn()
	{
		return this.achievementsBtn;
	}
}
