package workspace;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * @author Mark Robinson
 */
public class SendMessage {
	public void Send(String mess, String sendTo) {
		String host = "smtp.gmail.com";
		String from = "codecornerapps@gmail.com";
		String pass = "password";

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = { sendTo };
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setText(mess);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			JOptionPane.showMessageDialog(null, "Message Sent!", "Successful",
					1);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}