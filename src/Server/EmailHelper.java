package Server;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Data.Email;

public class EmailHelper {

	Properties properties = new Properties();
	Email email;
	
	public EmailHelper(Email email)
	{
		this.email = email;
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}
	
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
	
	public void sendEmail() 
	{
		try 
		{
			Message message = new MimeMessage(createSession());
			message.setFrom(new InternetAddress(email.getFrom()));
			String[] recipients = email.geTo().toArray(new String[email.geTo().size()]);
			String recipientString = "";
			
			for(int i = 0; i < email.geTo().size(); i++)
			{
				recipientString += recipients[i] + ",";
			}
			recipientString = recipientString.substring(0, recipientString.length() - 1);
			
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(recipientString));
			message.setSubject(email.getSubject());
			message.setText(email.getContent());
			
			Transport.send(message);
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
		}
	}
}
