package com.projectone.email.API.EmailController;


import com.projectone.email.API.EmailService.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import javax.mail.internet.AddressException;

/**
 * Controller to send an email message to specific users.
 */
@RestController
public class EmailController {
    @Autowired
    EmailService emailService;
    @RequestMapping(value = "/send/{m_id}",method = RequestMethod.PUT)
/**
 * @param emailmessage refers to the message to be sent
 * Message to be sent is getting from post method
 */

    public String sendEmail(@RequestBody @PathVariable String m_id) throws AddressException, MessagingException {
        Logger logger = LoggerFactory.getLogger( EmailController.class);
        logger.info("Request from controller");

        logger.info(m_id);
        String s=emailService.sendMail(m_id);

        return s;
    }


}
