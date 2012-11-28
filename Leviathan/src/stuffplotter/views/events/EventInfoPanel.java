package stuffplotter.views.events;


import stuffplotter.client.EventCreationPageVisitor;
import stuffplotter.presenters.EventInfoPresenter.EventInfoView;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Class to display the general information for an event during its creation.
 */
public class EventInfoPanel extends SimplePanel implements EventSubmittable, EventInfoView
{
	private static final String NO_RESULTS = "No results found.";
	private EventInfoInputPanel eventInputPanel;
	private EventLocationMapPanel mapPanel;
	private EventLocationSearchPanel mapSearchPanel;
	
	/**
	 * Constructor for the EventInfoPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventInfoPanel()
	{
		super();
		this.initializeUI();
	}

	/**
	 * Method to initialize the UI.
	 * @pre true;
	 * @post true;
	 */
	private void initializeUI()
	{
		HorizontalPanel infoHolder = new HorizontalPanel();
		eventInputPanel = new EventInfoInputPanel();
		//mapPanel = new EventLocationMapPanel();
		//mapSearchPanel = new EventLocationSearchPanel();
		infoHolder.add(eventInputPanel);
		//infoHolder.add(mapPanel);
		//this.add(infoHolder);
	}
	
	/**
	 * Method to retrieve the EventInfoInputPanel containing the general information for the
	 * event.
	 * @pre true;
	 * @post true;
	 * @return the EventInputPanel for the EventInfoPanel.
	 */
	public EventInfoInputPanel getEventInfoInputPanel()
	{
		return this.eventInputPanel;
	}
	
	/**
	 * Method to accept an EventCreationPageVisitor and have it perform certain tasks.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	@Override
	public void accept(EventCreationPageVisitor eventVisitor)
	{
		eventVisitor.visit(this);
	}

	@Override
	public HasClickHandlers getClearBtn()
	{
		return this.mapSearchPanel.getClearButton();
	}

	@Override
	public HasClickHandlers getSearchBtn()
	{
		return this.mapSearchPanel.getSearchButton();
	}

	@Override
	public HasClickHandlers getNextBtn()
	{
		return this.getNextBtn();
	}

	@Override
	public HasClickHandlers getBackBtn()
	{
		return this.getBackBtn();
	}

	@Override
	public void setLocationData(JsArray<Placemark> locations)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSearchInput()
	{
		return this.mapSearchPanel.getUserInput();
	}

	@Override
	public void clearResults()
	{
		this.eventInputPanel.clearResults();
		this.mapSearchPanel.defaultPageResults();
		this.mapPanel.clearResults();
	}

	@Override
	public void setFailResult()
	{
		this.mapSearchPanel.defaultPageResults();
		this.eventInputPanel.clearResults();
		this.mapSearchPanel.setNumOfResults(NO_RESULTS);
	}

	@Override
	public void nextResult()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void previousResult()
	{
		// TODO Auto-generated method stub
		
	}
}
