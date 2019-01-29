package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        JmsTemplate jsmTemplate = context.getBean(JmsTemplate.class);
        jsmTemplate.convertAndSend("recipe15-2", customer);

        context.close();
    }
}
