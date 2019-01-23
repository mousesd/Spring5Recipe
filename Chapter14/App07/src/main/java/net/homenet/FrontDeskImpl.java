package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.MapMessage;

@SuppressWarnings("Duplicates")
public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {
    @Override
    public void sendMail(final Mail mail) {
        assert getJmsTemplate() != null;
        getJmsTemplate().send(session -> {
                MapMessage message = session.createMapMessage();
                message.setString("mailId", mail.getMailId());
                message.setString("country", mail.getCountry());
                message.setDouble("weight", mail.getWeight());
                return message;
        });
    }
}
