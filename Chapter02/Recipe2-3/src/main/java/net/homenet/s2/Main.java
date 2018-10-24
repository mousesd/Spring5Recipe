package net.homenet.s2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext("net.homenet.s2");
        SequenceService service = context.getBean(SequenceService.class);

        System.out.println(service.generate("IT"));
        System.out.println(service.generate("IT"));
    }
}
