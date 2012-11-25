package stuffplotter.client;

import java.util.Date;
import java.util.List;

import stuffplotter.views.events.EventCreationPageVisitor;
import stuffplotter.views.events.EventDateSelectionPanel;
import stuffplotter.views.events.EventInfoInputPanel;
import stuffplotter.views.events.EventInfoPanel;
import stuffplotter.views.events.FriendSelectionPanel;

/**
 * Class that is a visitor for any panel that implements the EventSubmittable interface.
 * Verifies all required input for each page is input and notifies user if something is
 * missing.
 */
public class EventCreationPageValidator implements EventCreationPageVisitor
{
	public boolean isPageValid;
	
	/**
	 * Constructor for the EventCreationPageValidator.
	 * @pre true;
	 * @post true;
	 */
	public EventCreationPageValidator()
	{
		isPageValid = true;
	}
	
	@Override
	public void visit(EventInfoPanel eventInfoPanel)
	{
		EventInfoInputPanel infoPanel = eventInfoPanel.getEventInfoInputPanel();
		infoPanel.clearErrorMessage();
		
		if(infoPanel.getName().equals(""))
		{
			isPageValid = false;
			infoPanel.displayErrorMessage();
		}
	}

	@Override
	public void visit(EventDateSelectionPanel dateSelectionPanel)
	{
		dateSelectionPanel.clearErrorMessage();
		List<Date> datesSelected = dateSelectionPanel.getTimeSheetPanel().retrieveSubmission();
		if(datesSelected.isEmpty())
		{
			isPageValid = false;
			dateSelectionPanel.displayErrorMessage();
		}
	}

	@Override
	public void visit(FriendSelectionPanel friendSelection)
	{
		//Do nothing as there are no values to validate on this page.
	}
	
	/**
	 * Tells if the EventSubmittable contains valid input.
	 * @pre true;
	 * @post true;
	 * @return true if the page is valid; false otherwise.
	 */
	public boolean isPageValid()
	{
		return this.isPageValid;
	}
}
