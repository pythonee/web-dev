/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;


import java.security.Security;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeMessage;

import pojo.Mail;

/**
 *
 * @author pythonee
 */
public class MailService {
    private final static String from = "ee562.ebank@gmail.com";

    public static void send(Mail mail) 
            throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        final String username = "ee562.ebank@gmail.com";
        final String password = "onedefour";
        
        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(mail.getMail_to()));
        message.setSubject(mail.getSubject());
        message.setText("Your new password is: \n"+mail.getMsg());

        Transport.send(message);
    }
}

