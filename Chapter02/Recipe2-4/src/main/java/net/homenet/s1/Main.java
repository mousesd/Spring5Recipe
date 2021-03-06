package net.homenet.s1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(SequenceConfiguration.class, PrefixConfiguration.class);

        SequenceGenerator generator = context.getBean(SequenceGenerator.class);
        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());
    }
}
