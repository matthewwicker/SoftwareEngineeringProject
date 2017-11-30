package EmailNotifications;
import Entities.Promo;
import Entities.User;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;

import DatabaseAccess.UserDBManager;

import javax.activation.*;  
import javax.activation.*;

public class SendEmail {
	public static int REGISTRATION_CONFIRMATION = 1;
	public static int PURCHASE_CONFIRMATION = 2;
	public static int SHIPMENT_CONFIRMATION = 3;
	public static int ACCOUNT_STATUS_CHANGED_SUSPENDED = 4;
	public static int ACCOUNT_STATUS_CHANGED_UNSUSPENDED = 5;
	public static int SUBBED_PROMOTIONS = 6;
	public static int UNSUBBED_PROMOTIONS = 7;
	public static int ACCOUNT_VERIFICATION = 8;
	public SendEmail() {}
	public void actuallySendEmail(User u, int email_type) {
			String from = "ITSBOOKZB"; 
			String pass = "ITSBOOKZB123"; 
			String to = u.getEmail();
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	        props.put("mail.smtp.password", pass);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);
	        String subject = "";
	        String body = "";
	        //Create new message body and to and from messages
	       
	        	if(email_type == REGISTRATION_CONFIRMATION) {
	        		subject = "Thanks for registering with BOOKZ!";
	        		body = this.generateRegistrationConfirmationMessage(u);
	        	}
	        	else if(email_type == PURCHASE_CONFIRMATION) {
	        		subject = "Thanks for making a purchase with BOOKZ!";
	        		body = this.generatePurchaseConfirmationMessage(u);
	        	}
	        	else if(email_type == SHIPMENT_CONFIRMATION) {
	        		subject = "We have shipped your BOOKZ order!";
	        		body = this.generateShipmentConfirmationMessage(u);
	        	}
	        	else if(email_type == ACCOUNT_STATUS_CHANGED_SUSPENDED) {
	        		subject = "We have suspended your BOOKZ account";
	        		body = this.generateAccountStatusSuspendedMessage(u);
	        	}
	        	else if(email_type == ACCOUNT_STATUS_CHANGED_UNSUSPENDED) {
	        		subject = "We have UNsuspended your BOOKZ account. You're welcome.";
	        		body = this.generateAccountStatusUNSuspendedMessage(u);
	        	}
	        	else if(email_type == SUBBED_PROMOTIONS) {
	        		subject = "Thanks for subscribing for Bookz Promotions";
	        		body = this.generateSubPromotionMessage(u);
	        	}
	        	else if(email_type == UNSUBBED_PROMOTIONS) {
	        		subject = "Unsubscribed from Bookz Promotion emails";
	        		body = this.generateUnsubPromotionMessage(u);
	        	}
	        	else if(email_type == ACCOUNT_VERIFICATION) {
	        		subject = "Thanks for verifying your account with Bookz!";
	        		body = this.generateVerificationMessage(u);
	        	}

	        try {
	            message.setFrom(new InternetAddress(from));
	            InternetAddress toAddress = new InternetAddress();

	            toAddress = new InternetAddress(to);
	            message.addRecipient(Message.RecipientType.TO, toAddress);

	            message.setSubject(subject);
	            message.setText(body);
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, from, pass);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        } 
	}
	
	
	public void sendPromotionalEmail(User u, Promo p) {
		String from = "ITSBOOKZB"; 
		String pass = "ITSBOOKZB123"; 
		String to = u.getEmail();
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        String subject = "NEW Promo from BOOKZ: " + p.getCode();
        String body = "Hi "+ u.getFname() +", \n \n" + 
        				"We at Bookz have just added a new promotion, and we wanted you to be the first to know! "
        				+ "You can now use the promo code to get " + p.getPercentOff() + "% off select books (ISBN - " + p.getISBN()+" ) \n"
        				+"\n Have a wonderful day! \n \n Best, \n Bookz team";
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress();

            toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        } 
}

	
	
	
	private String generateRegistrationConfirmationMessage(User u) {
		UserDBManager UManager = new UserDBManager();
		ArrayList<User> newUser = UManager.searchUsers("email", u.getEmail());
		int validationcode = newUser.get(0).getUid();
		String message = "Hi " + u.getFname() + ", \n \n "
				+ "Thanks for registering with books! Next time you log in, please confirm your account by quickly \n"
				+ "entering the following confirmation number:  \n CONFIRMATION NUMBER: " + validationcode + "\n \n Have a great day! \n"
				+ "Bookz team";
		return message;
	}
	private String generatePurchaseConfirmationMessage(User u) {return "EMAIL CONTENT";}
	private String generateShipmentConfirmationMessage(User u) {return "EMAIL CONTENT";}
	private String generateAccountStatusSuspendedMessage(User u) {return "EMAIL CONTENT";}
	private String generateAccountStatusUNSuspendedMessage(User u) {return "EMAIL CONTENT";}
	private String generateSubPromotionMessage(User u) {
		String message = "Hi " + u.getFname() + ", \n \n "
				+ "Thanks for subscribing to get Bookz promotional emails! We hope you enjoy all the awesome offers we have! \n"
				+ "\n \n Have a great day! \n"
				+ "Bookz team";
		return message;
		
	}
	private String generateUnsubPromotionMessage(User u) {
		String message = "Hi " + u.getFname() + ", \n \n "
				+ "We are sorry to see you unsubscribing from Bookz promotional emails. :( We hope you continue to enjoy all the awesome offers we have! \n"
				+ "\n \n Have a great day! \n"
				+ "Bookz team";
		return message;
	}
	
	private String generateVerificationMessage(User u) {
		String message = "Hi " + u.getFname() + ", \n \n "
				+ "Thanks so much for verifying your account. Now we know you aren't a robot, or a blood sucking zombie, or any of the awesome things"
				+ " you can read about in our bookz! \n"
				+ "\n \n Have a great day! \n"
				+ "Bookz team \n\n OH Hey, just a little fun fact about you account, that verification code you send (" + u.getUid() +") "
						+ "can be used as your login too! Just a cool little shortcut :)";
		return message;
	}
	
	public static void main(String[] args) {
		User us = new User();
		SendEmail s = new SendEmail();
		s.actuallySendEmail(us, 1);
	}
}
