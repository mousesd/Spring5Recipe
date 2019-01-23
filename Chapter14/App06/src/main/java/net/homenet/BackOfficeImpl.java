package net.homenet;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;

@SuppressWarnings("Duplicates")
public class BackOfficeImpl implements BackOffice {
    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public Mail receiveMail() {
        MapMessage message = (MapMessage) jmsTemplate.receive();
        try {
            if (message == null)
                return null;

            return new Mail(message.getString("mailId"), message.getString("country"), message.getDouble("weight"));
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
