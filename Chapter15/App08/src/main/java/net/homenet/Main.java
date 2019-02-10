package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class Main {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1234L);
        customer.put("firstName", "Marten");
        customer.put("lastName", "Deinum");

        JmsTemplate jsmTemplate = context.getBean(JmsTemplate.class);
        jsmTemplate.convertAndSend("recipe15-2", customer);

        System.out.println("Press [Enter] to stop");
        System.in.read();

        context.close();
    }
}
