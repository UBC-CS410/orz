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
	private AccountStatsServiceAsync statsService;
	
	/**
	 * Constructor for the ServiceRepository.
	 * @pre true;
	 * @post true;
	 */
	public ServiceRepository()
	{
		this.accountService = GWT.create(AccountService.class);
		this.eventService = GWT.create(EventService.class);
		this.recordService = GWT.create(RecordService.class);
		this.statsService = GWT.create(AccountStatsService.class);
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
	
	/**
	 * Retrieve the account statistics service.
	 * @pre true;
	 * @post true;
	 * @return the account statistics service.
	 */
	public AccountStatsServiceAsync getStatsService()
	{
		return this.statsService;
	}
}
