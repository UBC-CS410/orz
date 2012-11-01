package stuffplotter.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Class to display the search panel for the location of an event.
 */
public class EventLocationSearchPanel extends PopupPanel
{
	/**
	 * Constructor for EventLocationSearchPanel.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventLocationSearchPanel()
	{
		super();
		HorizontalPanel searchElementsHolder = new HorizontalPanel();
		TextBox searchBox = new TextBox();
		Button searchButton = new Button("Search");
		this.initializeSearchButton(searchBox, searchButton);
		searchElementsHolder.add(searchBox);
		searchElementsHolder.add(searchButton);
		this.add(searchElementsHolder);
	}
	
	/**
	 * Helper method to add clickHandler to search button.
	 * @pre searchBox != null && searchButton != null;
	 * @post true;
	 * @param searchBox - the TextBox to get input from the user for a location.
	 * @param searchButton - the Button to press when submitting query.
	 */
	private void initializeSearchButton(TextBox searchBox, Button searchButton)
	{
		final TextBox searchInput = searchBox;
		searchButton.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Window.alert(searchInput.getText());
			}
		});
	}
}
