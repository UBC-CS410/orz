package stuffplotter.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Class for paging in the EventCreationDialogBox class.
 */
public class EventCreationPager extends DeckPanel
{
	/**
	 * Constructor for the EventCreationPager.
	 * @pre true;
	 * @post this.isVisible() == true;
	 */
	public EventCreationPager()
	{
		super();
		VerticalPanel page1 = new VerticalPanel();
		VerticalPanel page2 = new VerticalPanel();
		HorizontalPanel page1Btns = new HorizontalPanel();
		HorizontalPanel page2Btns = new HorizontalPanel();
		Button nextBtn = new Button("Next");
		Button backBtn = new Button("Back");
		Button cancelBtn = new Button("Cancel");
		
		this.initializeButtons(nextBtn, backBtn, cancelBtn);
	
		page1Btns.add(nextBtn);
		page1Btns.add(cancelBtn);
		page1.add(new Label("Page 1"));
		page1.add(new CheckBox());
		page1.add(new TextBox());
		page1.add(page1Btns);
		
		page2Btns.add(backBtn);
		page2Btns.add(cancelBtn);
		page2.add(new Label("Page 2"));
		page2.add(new CheckBox());
		page2.add(new TextBox());
		page2.add(page2Btns);
		this.add(page1);
		this.add(page2);
		this.showWidget(0);
	}
	
	/**
	 * Helper method to initialize the buttons for the EventCreationPager.
	 * @pre next != null && back != null && cancel != null;
	 * @post
	 * @param next - the next button.
	 * @param back - the back button.
	 * @param cancel - the cancel button.
	 */
	private void initializeButtons(Button next, Button back, Button cancel)
	{
		next.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				next();
			}
		});
		
		back.addClickHandler(new ClickHandler()
		{	
			@Override
			public void onClick(ClickEvent event)
			{
				back();
			}
		});
		
		// TO DO: Cancel Button
	}
	
	// Temporary methods to test deck panel
	private void next()
	{
		this.showWidget(1);
	}
	private void back()
	{
		this.showWidget(0);
	}
}
