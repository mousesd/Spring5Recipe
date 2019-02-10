package net.homenet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

public class InboundJmsMessageProcessor {
    private final Logger logger = LoggerFactory.getLogger(InboundJmsMessageProcessor.class);

    //# 1.@Headers 이용
    //@ServiceActivator
    //public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundMessage
    //    , @Headers Map<String, Object> headers) {
    //
    //    Map<String, Object> message = inboundMessage.getPayload();
    //    logger.info("FirstName: {}, LastName: {}, Id: {}"
    //        , message.get("firstName")
    //        , message.get("lastName")
    //        , message.get("id"));
    //
    //    for (Map.Entry<String, Object> header : headers.entrySet()) {
    //        logger.info("{} : {}", header.getKey(), header.getValue());
    //    }
    //
    //    // You can imagine what we could do here:
    //    // Put the record into the database, call a webservice, write it to a file, etc...
    //}

    //# 2.@Header 이용
    @ServiceActivator
    public void handleIncomingJmsMessage(Message<Map<String, Object>> inboundMessage
        , @Header(MessageHeaders.ID) String uuid
        , @Header(MessageHeaders.TIMESTAMP) String timeStamp) {

        Map<String, Object> message = inboundMessage.getPayload();
        logger.info("FirstName: {}, LastName: {}, Id: {}"
            , message.get("firstName")
            , message.get("lastName")
            , message.get("id"));

        logger.info("Id: {}, TimeStamp: {}", uuid, timeStamp);

        // You can imagine what we could do here:
        // Put the record into the database, call a webservice, write it to a file, etc...
    }
}
