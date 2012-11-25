package stuffplotter.views.account;

import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.views.util.InfoPanel;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display a given AccountStatistic.
 */
public class StatisticPanel extends VerticalPanel
{
	private InfoPanel userLevel;
	private InfoPanel userExperience;
	private InfoPanel numOfLogins;
	private InfoPanel numOfHostedEvents;
	private InfoPanel numOfParticpatedEvents;
	private InfoPanel numOfFriends;	
	
	/**
	 * Constructor for the StatisticPanel.
	 * @pre true;
	 * @post true;
	 */
	public StatisticPanel()
	{
		this.initializeUI();
	}
	
	/**
	 * Helper method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		this.userLevel = new InfoPanel("Level", "");
		this.userExperience = new InfoPanel("Experience", "");
		this.numOfLogins = new InfoPanel("Number of logins", "");
		this.numOfHostedEvents = new InfoPanel("Number of hosted events", "");
		this.numOfParticpatedEvents = new InfoPanel("Number of participated evnets", "");
		this.numOfFriends = new InfoPanel("Number of friends", "");
		this.add(this.userLevel);
		this.add(this.userExperience);
		this.add(this.numOfLogins);
		this.add(this.numOfHostedEvents);
		this.add(this.numOfParticpatedEvents);
		this.add(this.numOfFriends);
	}
	
	/**
	 * Set the statistic data to display.
	 * @pre true;
	 * @post true;
	 * @param model - the model to display on the StatisticPanel.
	 */
	public void setStatisticData(AccountStatisticModel model)
	{
		this.userLevel.setValue(String.valueOf(model.getUserLevel()));
		this.userExperience.setValue(String.valueOf(model.getUserExperience()));
		this.numOfLogins.setValue(String.valueOf(model.getNumberOfLogins()));
		this.numOfHostedEvents.setValue(String.valueOf(model.getNumberOfHostedEvents()));
		this.numOfParticpatedEvents.setValue(String.valueOf(model.getNumberOfParticipatedEvents()));
		this.numOfFriends.setValue(String.valueOf(model.getNumberOfFriends()));
	}
}
