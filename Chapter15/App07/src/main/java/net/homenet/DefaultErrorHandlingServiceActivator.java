package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

public class DefaultErrorHandlingServiceActivator {
    private static final Logger logger = LoggerFactory.getLogger(DefaultErrorHandlingServiceActivator.class);

    @ServiceActivator
    public void handleThrowable(Message<Throwable> inboundMessage) throws Throwable {
        Throwable throwable = inboundMessage.getPayload();
        logger.info("Message: {}", throwable.getMessage(), throwable);

        if (throwable instanceof MessagingException) {
            logger.info("throwable instance of MessageException is true");
            // Do something with the original message
        } else {
            logger.info("throwable instance of MessageException is false");
            // It's something that was thrown in the execution of code in some component you created
        }
    }
}
