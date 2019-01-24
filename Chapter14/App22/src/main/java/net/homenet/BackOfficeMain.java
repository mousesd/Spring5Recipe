package net.homenet;

import net.homenet.configuration.BackOfficeConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);
        BackOffice backOffice = context.getBean(BackOffice.class);
        backOffice.receiveMail();
    }
}
