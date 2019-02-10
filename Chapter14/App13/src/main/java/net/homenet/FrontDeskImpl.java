package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("Duplicates")
public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {
    @Override
    @Transactional
    public void sendMail(Mail mail) {
        assert getJmsTemplate() != null;
        getJmsTemplate().convertAndSend(mail);
    }
}
