package Server;

import java.util.ArrayList;
import java.util.Properties;
	
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Data.Email;

/**
 * Email helper class sets up the session and manages email sending for the server
 * @author Justin, Magnus, Robert
 */
public class EmailHelper {

	/**
	 * Email configuration properties 
	 */
	Properties properties = new Properties();
	
	/**
	 * Email class holds data for email data including password for sending
	 */
	Email email;
	
	/**
	 * List of recipients for email sending
	 */
	ArrayList<String> recieverList;
	
	/**
	 * Constructs Email helper with Email and recieverList 
	 * @param email email being sent
	 * @param recieverList list of people receiving email 
	 */
	public EmailHelper(Email email, ArrayList<String> recieverList)
	{
		this.email = email;
		this.recieverList = recieverList;
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}
	
	/**
	 * Create the email session and ask for authentication for email 
	 * @return Session of email 
	 */
	private Session createSession()
	{
		Session session = Session.getInstance(properties, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication (email.getFrom(), email.getPassword());
			}
		});
		return session;
	}
	
	/**
	 * Send email in Email data field to all recipients in recieverList 
	 * @return true if email sent, false if email not sent properly
	 */
	public boolean sendEmail() 
	{
		try 
		{
			Message message = new MimeMessage(createSession());
			message.setFrom(new InternetAddress(email.getFrom()));
			String[] recipients = recieverList.toArray(new String[recieverList.size()]);
			String recipientString = "";
			
			for(int i = 0; i < recieverList.size(); i++)
			{
				recipientString += recipients[i] + ",";
			}
			recipientString = recipientString.substring(0, recipientString.length() - 1);
			
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientString));
			message.setSubject(email.getSubject());
			message.setText(email.getContent());
			
			Transport.send(message);
			return true;
		}
		catch(MessagingException e)
		{
			return false;
		}
	}
}
