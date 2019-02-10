package net.homenet;

import org.springframework.kafka.annotation.KafkaListener;

public class MailListener {
    @KafkaListener(topics = "mails")
    public void onMessage(String mail) {
        System.out.println("Received: " + mail);
    }
}
