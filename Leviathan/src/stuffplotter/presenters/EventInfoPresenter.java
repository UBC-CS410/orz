package stuffplotter.presenters;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EventInfoPresenter implements Presenter
{
	public interface EventInfoView
	{
		/**
		 * Retrieve the clear button for reseting the display for location search.
		 * @pre true;
		 * @post true;
		 * @return the clear button for reseting the display for location search.
		 */
		public HasClickHandlers getClearBtn();
		
		/**
		 * Retrieve the search button for searching the google map.
		 * @pre true;
		 * @post true;
		 * @return the search button for searching the google map.
		 */
		public HasClickHandlers getSearchBtn();
		
		/**
		 * Retrieve the next button for iterating through search results. 
		 * @pre true;
		 * @post true;
		 * @return the next button for iterating through search results.
		 */
		public HasClickHandlers getNextBtn();
		
		/**
		 * Retrieve the previous button for iterating through search results.
		 * @pre true;
		 * @post true;
		 * @return the previous button for iterating through serach results.
		 */
		public HasClickHandlers getBackBtn();
		
		/**
		 * Set the location data for the display.
		 * @pre location != null;
		 * @post true;
		 * @param location - the location to set to the display.
		 */
		public void setLocationData(Placemark location);
		
		/**
		 * Retrieve the input from the user.
		 * @pre true;
		 * @post true;
		 * @return the input from the user with white space trimmed.
		 */
		public String getSearchInput();
		
		/**
		 * Enable the next button for the location search results.
		 * @pre true;
		 * @post true;
		 */
		public void enableNextBtn();
		
		/**
		 * Disable the next button for the location search results.
		 * @pre true;
		 * @post true;
		 */
		public void disableNextBtn();
		
		/**
		 * Enable the back button for the location search results.
		 * @pre true;
		 * @post true;
		 */
		public void enableBackBtn();
		
		/**
		 * Disable the back button for the location search results.
		 * @pre true;
		 * @post true;
		 */
		public void disableBackBtn();
		
		/**
		 * Clear the search results from the view.
		 * @pre true;
		 * @post true;
		 */
		public void clearResults();
		
		/**
		 * Set the message for number of results found.
		 * @pre true;
		 * @post true;
		 */
		public void setNumberOfResults(int resultsFound);
		
		/**
		 * Set the view to a failed search result view.
		 * @pre true;
		 * @post true;
		 */
		public void setFailResult();
		
		/**
		 * Retrieve the EventInfoView as a widget.
		 * @pre true;
		 * @post true;
		 * @return the EventInfoView as a widget.
		 */
		public Widget asWidget();
	}
	
	private final EventInfoView display;
	private JsArray<Placemark> locationsFound;
	private int numOfLocations;
	private int currentIndex;
	
	/**
	 * Constructor for the EventInfoPresenter.
	 * @pre display != null;
	 * @post true;
	 * @param display - the display for inputting information for the event.
	 */
	public EventInfoPresenter(EventInfoView display)
	{
		this.display = display;
	}
	
	/**
	 * Helper method to bind the handlers.
	 * @pre true;
	 * @post true;
	 */
	private void bind()
	{
		this.display.getClearBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				display.clearResults();
			}
		});
		
		this.display.getSearchBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				LocationCallback callback = new LocationCallback()
				{
					@Override
					public void onFailure(int statusCode)
					{
						display.setFailResult();
					}

					@Override
					public void onSuccess(JsArray<Placemark> locations)
					{						
						if(locations != null && locations.length() > 0)
						{
							locationsFound = locations;
							currentIndex = 0;
							numOfLocations = locations.length();
							display.setLocationData(locations.get(0));
							display.setNumberOfResults(numOfLocations);
							if(locations.length() != 1)
							{
								display.enableNextBtn();
							}
						}
						else
						{
							display.setFailResult();
						}
					}
				};
				
				Geocoder geocoder = new Geocoder();
				geocoder.getLocations(display.getSearchInput(), callback);
			}
		});
		
		this.display.getNextBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				currentIndex++;
				display.setLocationData(locationsFound.get(currentIndex));
				
				if(currentIndex == numOfLocations - 1)
				{
					display.disableNextBtn();
				}
				
				display.enableBackBtn();
			}
		});
		
		this.display.getBackBtn().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				currentIndex--;
				display.setLocationData(locationsFound.get(currentIndex));
				
				if(currentIndex == 0)
				{
					display.disableBackBtn();
				}
				
				display.enableNextBtn();
			}
		});
	}
	
	@Override
	public void go(HasWidgets container)
	{
		this.bind();
		container.add(this.display.asWidget());
	}
}
