package net.homenet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class FrontDeskImpl implements FrontDesk {
    @Override
    public void sendMail(Mail mail) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.222.128:61616");
        Destination destination = new ActiveMQQueue("mail.queue");

        try (
            Connection conn = factory.createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination)
        ) {
            MapMessage message = session.createMapMessage();
            message.setString("mailId", mail.getMailId());
            message.setString("country", mail.getCountry());
            message.setDouble("weight", mail.getWeight());
            producer.send(message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
