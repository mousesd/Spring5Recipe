package net.homenet;

import org.springframework.kafka.annotation.KafkaListener;

public class MailListener {
    @KafkaListener(topics = "mails")
    public void onMessage(Mail mail) {
        System.out.println("Received: " + mail);
    }
}
