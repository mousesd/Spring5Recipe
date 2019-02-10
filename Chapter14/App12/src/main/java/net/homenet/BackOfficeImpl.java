package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.Map;

@SuppressWarnings("Duplicates")
public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {
    @Override
    public Mail receiveMail() {
        assert getJmsTemplate() != null;
        return (Mail) getJmsTemplate().receiveAndConvert();
    }
}
