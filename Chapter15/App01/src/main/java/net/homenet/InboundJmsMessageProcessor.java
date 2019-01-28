package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Map;

public class InboundJmsMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundJmsMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundMessage) {
        Map<String, Object> message = inboundMessage.getPayload();
        logger.info("FirstName: {}, LastName: {}, Id: {}"
            , message.get("firstName")
            , message.get("lastName")
            , message.get("id"));

        // You can imagine what we could do here:
        // Put the record into the database, call a webservice, write it to a file, etc...
    }
}
