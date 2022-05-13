package com.projectone.email.API.Emailmodel;

/**
 * Model for EmailMessage
 */
public class EmailMessage {

    public String to_address;
    public String subject;
    public String body;

   public EmailMessage(String to_address, String subject, String body){}


  public String getTo_address() {
      return to_address;
  }
    public void setTo_address(String to_address) {
        this.to_address = to_address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
