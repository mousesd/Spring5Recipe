package net.homenet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class BackOfficeImpl implements BackOffice {
    @Override
    public Mail receiveMail() {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
        Destination destination = new ActiveMQQueue("mail.queue");

        try (
            Connection conn = factory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(destination)
        ) {
            conn.start();
            MapMessage message = (MapMessage) consumer.receive();
            return new Mail(message.getString("mailId")
                , message.getString("country")
                , message.getDouble("weight"));
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
