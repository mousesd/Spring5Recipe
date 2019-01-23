package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("Duplicates")
public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {
    @Override
    @Transactional
    public Mail receiveMail() {
        assert getJmsTemplate() != null;
        return (Mail) getJmsTemplate().receiveAndConvert();
    }
}
