package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {
    @Override
    public void sendMail(Mail mail) {
        assert getJmsTemplate() != null;
        getJmsTemplate().convertAndSend(mail);
    }
}
