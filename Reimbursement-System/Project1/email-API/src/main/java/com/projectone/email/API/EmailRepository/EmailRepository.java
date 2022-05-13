package com.projectone.email.API.EmailRepository;

import com.projectone.email.API.Emailmodel.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Interface between EmailService and EmailMessage
 */
public interface EmailRepository extends JpaRepository<EmailMessage,Integer> {

   String saveMail();

    }

