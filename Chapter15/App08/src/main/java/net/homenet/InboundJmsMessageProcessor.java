package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

public class InboundJmsMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundJmsMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingCustomerMessage(Message<Customer> inboundMessage, @Headers Map<String, Object> headers) throws Exception {
        Customer customer = inboundMessage.getPayload();
        logger.info("Customer: {} ", customer);

        for (Map.Entry<String, Object> header : headers.entrySet()) {
            logger.info("{}:{}", header.getKey(), header.getValue());
        }

        if (customer.getId() == 1234L) {
            InvalidCustomerException e = new InvalidCustomerException();
            e.setCustomerId(customer.getId());
            throw e;
        }
    }
}
