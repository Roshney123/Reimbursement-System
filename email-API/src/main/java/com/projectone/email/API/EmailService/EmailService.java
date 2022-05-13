package com.projectone.email.API.EmailService;

import com.projectone.email.API.EmailRepository.EmailRepository;
import com.projectone.email.API.Emailmodel.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Service class between EmailController and EmailRepository
 */
@Service
public class EmailService {

    EmailRepository emailRepository;
    @Value("${gmail.username}")
    private String username;
    @Value("${gmail.password}")
    private String password;
    public EmailMessage emailMessage= new EmailMessage("roshney.kuriakose@gmail.com","Status ","Reassigned");
    /**
     * Service Method to send email message to specific user
     // * @param emailmessage refers to the message read from json body
     * @return emailmessage sent by method
     * @throws AddressException when given address is invalid
     * @throws MessagingException when a message is invalid
     */
    public String sendMail(String m_id) throws AddressException, MessagingException {
        if (m_id == null) {
            throw new AddressException("Mail Id Cannot be null");
        } else {

            Logger logger = LoggerFactory.getLogger(EmailService.class);
            logger.info("EmailService sending message ");


            Properties props = new Properties();//setting properties to send the mail
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username, false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m_id));
            msg.setSubject("Status of your Application");
            emailMessage.setSubject("Action Required:Your Reimbursement Application is Reassigned ,Apply with a reduced amount");
            msg.setContent(emailMessage.subject, "text/html");
            logger.info(emailMessage.subject);
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailMessage.subject, "text/html");

            logger.info(emailMessage.subject);
            Transport.send(msg);


            logger.info("EmailMessage Sent successfully");

            return "Email Sent Successfully";
        }

    }
}