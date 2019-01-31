package net.homenet;

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.Map;

public class InboundJmsMessageToCustomerTransformer {
    @Transformer
    public Message<Customer> transformJmsMessageToCustomer(Message<Map<String, Object>> message) {
        Map<String, Object> payload = message.getPayload();
        Customer customer = new Customer((Long) payload.get("id")
            , (String) payload.get("firstName")
            , (String) payload.get("lastName")
            , (String) payload.get("telephone")
            , (Float) payload.get("creditScore"));

        return MessageBuilder.withPayload(customer)
            .copyHeadersIfAbsent(message.getHeaders())
            .setHeaderIfAbsent("randomlySelectedForSurvey", Math.random() > 0.5)
            .build();
    }
}
