package net.homenet;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.MapMessage;

@SuppressWarnings("Duplicates")
public class FrontDeskImpl implements FrontDesk {
    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendMail(final Mail mail) {
        jmsTemplate.send(session -> {
                MapMessage message = session.createMapMessage();
                message.setString("mailId", mail.getMailId());
                message.setString("country", mail.getCountry());
                message.setDouble("weight", mail.getWeight());
                return message;
        });
    }
}
