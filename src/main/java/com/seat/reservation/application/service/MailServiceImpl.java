package com.seat.reservation.application.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

@Service
public class MailServiceImpl {

    private Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final String MAIL_MIME_TYPE = "text/html";

/*
    public void getAmazonSESEmailConfigurations(String to, String bodyContent, String subject) {

        int port = 587;
        String host = "smtp.gmail.com";
        String fromName = "Seat reservation confirmation";
        String user = "seatreservation0@gmail.com";
        String pwd = "1qaz2wsxA";
        Transport transport = null;

        try {
            Properties props = new Properties();
            // required for gmail
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            // or use getDefaultInstance instance if desired...

            Session session = Session.getInstance(props,     new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user,pwd);
                }
            });
            transport = session.getTransport("smtp");

            */
/** Create a message with the specified information. *//*

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user, fromName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setContent(bodyContent, MAIL_MIME_TYPE);

            //transport.connect(host, port, user, pwd);
            // Send the email.
            Transport.send(msg);
            System.out.println("success");
        } catch (Exception e) {
            System.out.println("AuthenticationFailedException - for authentication failures");
            e.printStackTrace();
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (MessagingException me) {
                log.error("The email was not sent.", me);
            }
        }
    }
*/

public void sendMail(String to, String bodyContent, String subject) {

    // Set required configs
    String fromUser = "seatreservation0@gmail.com";
    String host = "smtp.gmail.com";
    String port = "587";
    String password = "1qaz2wsxA";

    // Set system properties
    Properties properties = System.getProperties();
    properties.put("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", port);
    properties.setProperty("mail.smtp.user", fromUser);
    properties.setProperty("mail.smtp.password", password);
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.starttls.enable", "true");

    // Get the default Session object.
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(fromUser, password);
        }
    });

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);
        // Set from email address
        message.setFrom(new InternetAddress(fromUser, "Seat reservation confirmation"));
        // Set the recipient email address mulpitl
/*        InternetAddress[] addresses = new InternetAddress[recipients.size()];
        for (int i = 0; i < recipients.size(); i++) {
            addresses[i] = new InternetAddress(recipients.get(i));
        }*/
       // message.addRecipients(MimeMessage.RecipientType.TO, to);
        //Single recipient
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        // Set email subject
        message.setSubject(subject);
        // Set email body
        message.setText(bodyContent);
        // Set configs for sending email
        log.info("Sending Email...");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, fromUser, password);
        // Send email
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        log.info("Email sent : " + to);
        System.out.println("done");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (AddressException e) {
        e.printStackTrace();
    } catch (javax.mail.MessagingException e) {
        e.printStackTrace();
    }catch (Exception e){
        e.printStackTrace();
    }
}

}
