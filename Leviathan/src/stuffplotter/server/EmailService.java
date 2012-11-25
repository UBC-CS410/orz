package stuffplotter.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import stuffplotter.shared.Event;

/**
 * This is the EmailService class. It will handle sending out emails from
 * our admin
 * 
 * @author Matt
 *
 */
public class EmailService {
	final private String ADMIN_USERNAME = "stuffplotter000@gmail.com";
	final private String PASSWORD = "HappyMeal";
	private Properties  props = new Properties();
	Session session;

	
	public EmailService(){
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host" , "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session = Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(ADMIN_USERNAME, PASSWORD);
			}
		});
	}
	
	/**
	 * Sends an email to the new user, welcoming them into stuff plotter.
	 * 
	 * @pre sends an email to a user not in the data store
	 * @post Sends an email to the new user
	 * @param newUser		- New user to be added into our datastore
	 * @param formerUser	- Current user logged in emailing
	 */
	public void sendNewUser(String newUser, String formerUser){
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ADMIN_USERNAME));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(newUser));
			message.setSubject("StuffPlotter - "+formerUser+" wants to add you as a Friend");
			message.setContent("Hello "+newUser+",\n"+formerUser+" wants to add you as a friend. Please log into Stuffplotter at stuffplotter.appspot.com\nSee ya there,\nStuff Plotter Team", "text/html; charset=utf-8");
			
			Transport.send(message);
			
		}
		catch(MessagingException e)
		{
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}
	
	public void sendEvent(Event event){
		InternetAddress[] recipientEmails = new InternetAddress[event.getInvitees().size()+1];

		try
		{
			for(int i=0; i<event.getInvitees().size();i++){
				recipientEmails[i] = new InternetAddress(event.getInvitees().get(i));
			}
			recipientEmails[event.getInvitees().size()] = new InternetAddress(event.getOwner());
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ADMIN_USERNAME));
			message.setRecipients(Message.RecipientType.TO, recipientEmails);
			message.setSubject("StuffPlotter - You have been invited to an Envent");
			message.setContent("Hello You have been invited to an Event, Woopee!!!", "text/html; charset=utf-8");
			
			Transport.send(message);
			
		}
		catch(MessagingException e)
		{
			System.out.println(e.toString());
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	

}
