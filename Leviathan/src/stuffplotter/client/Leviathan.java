package stuffplotter.client;

import java.util.Date;

import stuffplotter.UI.AvailabilitySubmitter;
import stuffplotter.UI.EventCreationDialogBox;
import stuffplotter.UI.FriendFinderDialogBox;
import stuffplotter.shared.Account;

import com.bradrydzewski.gwt.calendar.client.Calendar;
import com.bradrydzewski.gwt.calendar.client.CalendarViews;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickEvent;
import com.bradrydzewski.gwt.calendar.client.event.TimeBlockClickHandler;
import com.google.api.gwt.services.calendar.shared.Calendar.EventsContext.ListRequest;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Leviathan implements EntryPoint
{
	
	private final String redirectUrl = (GWT.isProdMode()) ? GWT.getHostPageBaseURL() : GWT.getHostPageBaseURL() + "Leviathan.html?gwt.codesvr=127.0.0.1:9997";
	private Account account = null;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		
		AccountServiceAsync accountService = GWT.create(AccountService.class);
		
		accountService.login(redirectUrl, new AsyncCallback<Account>()
		{
	        public void onFailure(Throwable error)
	        {
	        	
	        }

	        public void onSuccess(Account result)
	        {
	          account = result;
	          if(account.inSession())
	          {
	        	  loadUI();
	          }
	          else
	          {
	        	  Window.Location.assign(account.getLogin());
	          }
	        }
		});
	}
	
	public void loadUI()
	{
		// testing calendar stuff
		Calendar calendar = new Calendar();
		calendar.setWidth("500px");
		calendar.setHeight("400px");

		//Displays the month View
		calendar.setView(CalendarViews.MONTH);
		calendar.addTimeBlockClickHandler(new TimeBlockClickHandler<Date>()
		{
			@Override
			public void onTimeBlockClick(TimeBlockClickEvent<Date> event)
			{
				// TODO Auto-generated method stub
				Window.alert(Integer.toString(event.getTarget().getDate()));
			}
		});
		
		// testing map stuff
		MapWidget map = new MapWidget(LatLng.newInstance(49, -123), 8);
		map.setSize("500px", "500px");
		map.setScrollWheelZoomEnabled(true);
		map.addControl(new LargeMapControl3D());
		
		// testing Toy Level System
		final LevelSystem lvlSys = new LevelSystem();

		VerticalPanel lvlView = new VerticalPanel();
		final Button addExpBtn = new Button("Up Vote");
		final Label lvlLabel = new Label("Level: 1");
		final Label expLabel = new Label("Experience: 0");
		
		lvlView.add(lvlLabel);
		lvlView.add(expLabel);
		lvlView.add(addExpBtn);
		
		addExpBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				lvlSys.addExperience(50);
				expLabel.setText("Experience: " + Integer.toString(lvlSys.getCurrentExperience()));
				lvlLabel.setText("Level: " + Integer.toString(lvlSys.getCurrentLevel()));
			}	
		});
		
		final Button availBtn = new Button("Avail");
		availBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				AvailabilitySubmitter availSubmitter = new AvailabilitySubmitter();
				availSubmitter.show();
			}
		});
		
		final Button createEventBtn = new Button("Create Event");
		createEventBtn.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				EventCreationDialogBox eventCreation = new EventCreationDialogBox();
				eventCreation.show();
			}
		});

		final Button findFriends = new Button("Find Friends");
		findFriends.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				FriendFinderDialogBox friendDialog = new FriendFinderDialogBox();
				friendDialog.show();
			}	
		});
		
		com.google.api.gwt.services.calendar.shared.Calendar testCalendar = GWT.create(com.google.api.gwt.services.calendar.shared.Calendar.class);
		ListRequest calRequest = testCalendar.events().list(Window.prompt("Input Email Address", "example@example.com"));
		Window.alert(calRequest.toString());
		
		HorizontalPanel calMapHolder = new HorizontalPanel();
		calMapHolder.add(calendar);
		calMapHolder.add(map);
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("calMapContainter").add(calMapHolder);
		RootPanel.get("addExp").add(lvlView);
		RootPanel.get("eventCreation").add(createEventBtn);
		RootPanel.get("availSub").add(availBtn);
		RootPanel.get("friendFinder").add(findFriends);
	}
}