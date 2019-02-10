package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import javax.jms.MapMessage;
import java.io.File;

public class InboundMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundMessageProcessor.class);

    @ServiceActivator
    public void handleIncomingFileMessage(Message<File> message) throws Exception {
        File filePayload = message.getPayload();
        logger.debug("Absolute path: {}, size: {}", filePayload.getAbsolutePath(), filePayload.length());
    }
}
