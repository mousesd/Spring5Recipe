package net.homenet;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;

public class MailListener {
    @JmsListener(destination = "mail.queue")
    public void onMessage(Map map) {
        Mail mail = new Mail((String) map.get("mailId")
            , (String) map.get("country")
            , (Double) map.get("weight"));
        System.out.println("Mail #" + mail.getMailId() + " received");
    }
}
