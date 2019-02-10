package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.MessageChannel;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AdditionConfiguration.class);
        MessageChannel request = context.getBean("request", MessageChannel.class);
        MessageChannel response = context.getBean("response", MessageChannel.class);

        SimpleMessagingGateway gateway = new SimpleMessagingGateway();
        gateway.setRequestChannel(request);
        gateway.setReplyChannel(response);
        gateway.setBeanFactory(context);
        gateway.afterPropertiesSet();
        gateway.start();

        Number result = gateway.convertSendAndReceive(new Operands(22, 4));
        System.out.printf("Result: %f%n", result.floatValue());

        context.close();
    }
}
