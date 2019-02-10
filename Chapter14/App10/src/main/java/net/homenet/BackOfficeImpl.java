package net.homenet;

import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.jms.support.JmsUtils;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {
    @Override
    public Mail receiveMail() {
        assert getJmsTemplate() != null;
        Map map = (Map) getJmsTemplate().receiveAndConvert();

        assert map != null;
        return new Mail((String) map.get("mailId")
            , (String) map.get("country")
            , (Double) map.get("weight"));
    }
}
