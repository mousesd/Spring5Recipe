package net.homenet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class FrontDeskImpl implements FrontDesk {
    private static final String QUEUE_NAME = "mail.queue";

    @Override
    public void sendMail(Mail mail) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.222.128");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("password");
        connectionFactory.setPort(5672);

        try (
            Connection conn = connectionFactory.newConnection();
            Channel channel = conn.createChannel()
        ) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = new ObjectMapper().writeValueAsString(mail);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Send: " + message);
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
