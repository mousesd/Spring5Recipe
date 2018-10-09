package net.homenet.s2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        ApplicationContext context =
            new GenericXmlApplicationContext("appContext.xml");

        SequenceGenerator generator = context.getBean(SequenceGenerator.class);
        System.out.println(generator.getSequence());
        System.out.println(generator.getSequence());
    }
}
