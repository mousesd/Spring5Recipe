package net.homenet;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

public class MailMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws MessageConversionException {
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage map = (MapMessage) message;
        return new Mail(map.getString("mailId")
            , map.getString("country")
            , map.getDouble("weight"));
    }
}
