package net.homenet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BackOfficeImpl implements BackOffice {
    private static final String QUEUE_NAME = "mail.queue";

    @Override
    public Mail receiveMail() {
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
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    Mail mail = new ObjectMapper().readValue(body, Mail.class);
                    System.out.println("Received: " + mail);
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
