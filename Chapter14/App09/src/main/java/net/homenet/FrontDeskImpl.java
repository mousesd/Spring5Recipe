package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.MapMessage;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {
    @Override
    public void sendMail(Mail mail) {
        Map<String, Object> map = new HashMap<>();
        map.put("mailId", mail.getMailId());
        map.put("country", mail.getCountry());
        map.put("weight", mail.getWeight());

        assert getJmsTemplate() != null;
        getJmsTemplate().convertAndSend(map);
    }
}
