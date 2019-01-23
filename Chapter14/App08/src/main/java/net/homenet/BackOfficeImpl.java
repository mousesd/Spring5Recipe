package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.MapMessage;

@SuppressWarnings("Duplicates")
public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {
    @Override
    public Mail receiveMail() {
        assert getJmsTemplate() != null;
        MapMessage message = (MapMessage) getJmsTemplate().receive();
        try {
            if (message == null)
                return null;

            return new Mail(message.getString("mailId"), message.getString("country"), message.getDouble("weight"));
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
