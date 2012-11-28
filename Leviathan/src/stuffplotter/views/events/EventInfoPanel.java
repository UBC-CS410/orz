package stuffplotter.views.events;


import stuffplotter.client.EventCreationPageVisitor;
import stuffplotter.presenters.EventInfoPresenter.EventInfoView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class to display the general information for an event during its creation.
 */
public class EventInfoPanel extends SimplePanel implements EventSubmittable, EventInfoView
{
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
		VerticalPanel mapHolder = new VerticalPanel();
		this.eventInputPanel = new EventInfoInputPanel();
		this.mapPanel = new EventLocationMapPanel();
		this.mapSearchPanel = new EventLocationSearchPanel();
		mapHolder.add(this.mapPanel);
		mapHolder.add(this.mapSearchPanel);
		infoHolder.add(eventInputPanel);
		infoHolder.add(mapHolder);
		this.add(infoHolder);
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
		return this.mapSearchPanel.getNextBtn();
	}

	@Override
	public HasClickHandlers getBackBtn()
	{
		return this.mapSearchPanel.getPreviousBtn();
	}

	@Override
	public void setLocationData(Placemark location)
	{
		this.eventInputPanel.setCoordinates(location.getPoint());
		this.eventInputPanel.setLocationText(location.getAddress());
		this.mapPanel.viewLocation(location.getPoint());
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
		this.mapPanel.clearResults();
		this.eventInputPanel.clearResults();
		this.mapSearchPanel.setFailResult();
	}

	@Override
	public void enableNextBtn()
	{
		this.mapSearchPanel.enableNextBtn();
	}

	@Override
	public void disableNextBtn()
	{
		this.mapSearchPanel.disableNextBtn();
	}

	@Override
	public void enableBackBtn()
	{
		this.mapSearchPanel.enableBackBtn();
	}

	@Override
	public void disableBackBtn()
	{
		this.mapSearchPanel.disableBackBtn();	
	}

	@Override
	public void setNumberOfResults(int resultsFound)
	{
		this.mapSearchPanel.setNumOfResults(resultsFound);
	}
}
