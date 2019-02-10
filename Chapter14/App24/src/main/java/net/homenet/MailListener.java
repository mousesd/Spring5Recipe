package net.homenet;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MailListener {
    @RabbitListener(queues = "mail.queue")
    public void onMessage(Mail mail) {
        System.out.println("Received: " + mail);
    }
}
