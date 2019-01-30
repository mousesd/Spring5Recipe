package net.homenet;

import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.util.Map;

public class InboundJmsMessageToCustomerTransformer {
    @Transformer
    public Customer transformJmsMessageToCustomer(Message<Map<String, Object>> message) {
        Map<String, Object> payload = message.getPayload();
        return new Customer((Long) payload.get("id")
            , (String) payload.get("firstName")
            , (String) payload.get("lastName")
            , (String) payload.get("telephone")
            , (Float) payload.get("creditScore"));
    }
}
