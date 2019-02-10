package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

public class InboundJmsMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundJmsMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingCustomerMessage(Message<Customer> inboundMessage) {
        Customer customer = inboundMessage.getPayload();
        logger.info("Customer: {} ", customer);
    }
}
