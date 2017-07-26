package boundary;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailSender {
public static boolean sendMail(String from, String password, String message, String SubjectLine, String[] to)
{
	String host="smtp.gmail.com";
	Properties props=System.getProperties();
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.user", from);
	props.put("mail.smtp.password", password);
	props.put("mail.smtp.port", 587);
	props.put("mail.smtp.auth", true);
	
	Session session =Session.getDefaultInstance(props,null);
	
	MimeMessage mime=new MimeMessage(session);
	try {
		mime.setFrom(new InternetAddress(from));
		InternetAddress[] toAddress=new InternetAddress[to.length];
		for(int i=0;i<to.length;i++)
		{
			toAddress[i]=new InternetAddress(to[i]);
		}
		
		for(int i=0;i<toAddress.length;i++)
		{
			mime.addRecipient(RecipientType.TO, toAddress[i]);
			
		}
		mime.setSubject(SubjectLine);
		mime.setText(message);
		
		Transport transport=session.getTransport("smtp");
		transport.connect(host,from,password);
		transport.sendMessage(mime, mime.getAllRecipients());
		transport.close();
		return true;
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	return false;
	}
}
