package net.homenet;

import org.springframework.jms.annotation.JmsListener;

public class MailListener {
    @JmsListener(destination = "mail.queue")
    public void onMessage(Mail mail) {
        System.out.println("Mail #" + mail.getMailId() + " received");
    }
}
