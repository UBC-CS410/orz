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

	/**
	 * Retrieve the account service.
	 * @pre true;
	 * @post true;
	 * @return the account service.
	 */
	public AccountServiceAsync getAccountService()
	{
		return this.accountService;
	}

	/**
	 * Retrieve the event service.
	 * @pre true;
	 * @post true;
	 * @return the event service.
	 */
	public EventServiceAsync getEventService()
	{
		return this.eventService;
	}

	/**
	 * Retrieve the record service.
	 * @pre true;
	 * @post true;
	 * @return the record service.
	 */
	public RecordServiceAsync getRecordService()
	{
		return this.recordService;
	}
}
