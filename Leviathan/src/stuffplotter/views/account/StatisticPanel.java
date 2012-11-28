package stuffplotter.views.account;

import stuffplotter.bindingcontracts.AccountStatisticModel;
import stuffplotter.presenters.AccountStatisticPresenter.AccountStatisticView;
import stuffplotter.views.util.InfoPanel;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display a given AccountStatistic.
 */
public class StatisticPanel extends VerticalPanel implements AccountStatisticView
{
	private InfoPanel userLevel;
	private InfoPanel userExperience;
	private InfoPanel numOfLogins;
	private InfoPanel numOfHostedEvents;
	private InfoPanel numOfParticpatedEvents;
	private InfoPanel numOfComments;
	private InfoPanel numOfFriends;
	private InfoPanel numOfRates;
	private Label errorMessage;
	
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
		this.numOfLogins = new InfoPanel("Number of Page Views", "");
		this.numOfHostedEvents = new InfoPanel("Number of Hosted Events", "");
		this.numOfParticpatedEvents = new InfoPanel("Number of Participated Events", "");
		this.numOfRates = new InfoPanel("Number of Events Rated","");
		this.numOfComments = new InfoPanel("Number of Comments", "");
		this.numOfFriends = new InfoPanel("Number of Friends", "");
		this.errorMessage = new Label();
		this.add(this.userLevel);
		this.add(this.userExperience);
		//this.add(this.numOfLogins);
		this.add(this.numOfHostedEvents);
		this.add(this.numOfParticpatedEvents);
		this.add(this.numOfComments);
		this.add(this.numOfRates);
		this.add(this.numOfFriends);
		this.add(errorMessage);
	}
	
	/**
	 * Set the statistic data to display.
	 * @pre true;
	 * @post true;
	 * @param model - the model to display on the StatisticPanel.
	 */
	@Override
	public void setStatisticData(AccountStatisticModel model)
	{
		this.userLevel.setValue(String.valueOf(model.getUserLevel()));
		this.userExperience.setValue(String.valueOf(model.getUserExperience()));
		this.numOfLogins.setValue(String.valueOf(model.getNumberOfLogins()));
		this.numOfHostedEvents.setValue(String.valueOf(model.getNumberOfHostedEvents()));
		this.numOfParticpatedEvents.setValue(String.valueOf(model.getNumberOfParticipatedEvents()));
		this.numOfComments.setValue(String.valueOf(model.getNumberOfComments()));
		this.numOfRates.setValue(String.valueOf(model.getNumberOfRates()));
		this.numOfFriends.setValue(String.valueOf(model.getNumberOfFriends()));
	}

	@Override
	public void displayFailMessage()
	{
		this.errorMessage.setText("Failed to retrieve account statistics, please refresh the page" +
								  "to try again.");
	}
	
	@Override
	public void removeFailMessage()
	{
		this.errorMessage.setText("");
	}
}
