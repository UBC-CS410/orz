package stuffplotter.client.services;

import com.google.gwt.core.shared.GWT;

/**
 * Class to hold all the services required for the application.
 */
public class ServiceRepository
{
	private AccountServiceAsync accountService;
	private EventServiceAsync eventService;
	private RecordServiceAsync recordService;
	
	/**
	 * Constructor for the ServiceRepository.
	 * @pre true;
	 * @post true;
	 */
	public ServiceRepository()
	{
		accountService = GWT.create(AccountService.class);
		eventService = GWT.create(EventService.class);
		recordService = GWT.create(RecordService.class);
	}
}
