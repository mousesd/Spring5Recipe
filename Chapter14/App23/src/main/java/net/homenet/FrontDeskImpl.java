package net.homenet;

import org.springframework.amqp.rabbit.core.RabbitGatewaySupport;

public class FrontDeskImpl extends RabbitGatewaySupport implements FrontDesk {
    @Override
    public void sendMail(Mail mail) {
        getRabbitOperations().convertAndSend(mail);
    }
}
