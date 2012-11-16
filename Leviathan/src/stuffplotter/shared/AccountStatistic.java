package stuffplotter.shared;

import java.io.Serializable;

import javax.persistence.Id;

public class AccountStatistic implements Serializable
{
	@Id
	private String accountEmail;
	private int numberOfLogins;
	private int numberOfHostedEvents;
	private int numberOfParticipatedEvents;	
	
	public AccountStatistic()
	{
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
	}
	
	public AccountStatistic(String user)
	{
		this.accountEmail = user;
		this.numberOfLogins = 0;
		this.numberOfHostedEvents = 0;
		this.numberOfParticipatedEvents = 0;
	}
	
	public void increamentLogin()
	{
		int login = this.numberOfLogins;
		login++;
		this.numberOfLogins = login;
	}
	
	public void increamentHostedEvents()
	{
		int host = this.numberOfHostedEvents;
		host++;
		this.numberOfHostedEvents = host;
	}
	
	public void increamentParticipatedEvents()
	{
		int participated = this.numberOfParticipatedEvents;
		participated++;
		this.numberOfParticipatedEvents = participated;
	}

	public int getNumberOfLogins()
	{
		return numberOfLogins;
	}

	public void setNumberOfLogins(int numberOfLogins)
	{
		this.numberOfLogins = numberOfLogins;
	}

	public int getNumberOfHostedEvents()
	{
		return numberOfHostedEvents;
	}

	public void setNumberOfHostedEvents(int numberOfHostedEvents)
	{
		this.numberOfHostedEvents = numberOfHostedEvents;
	}

	public int getNumberOfParticipatedEvents()
	{
		return numberOfParticipatedEvents;
	}

	public void setNumberOfParticipatedEvents(int numberOfParticipatedEvents)
	{
		this.numberOfParticipatedEvents = numberOfParticipatedEvents;
	}
	
}
