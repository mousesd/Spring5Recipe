package net.homenet;

import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class MailListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        MapMessage map = (MapMessage) message;
        try {
            Mail mail = new Mail(map.getString("mailId")
                , map.getString("country")
                , map.getDouble("weight"));
            System.out.println("Mail #" + mail.getMailId() + " received");
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
}
