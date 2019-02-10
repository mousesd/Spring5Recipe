package net.homenet;

import net.homenet.configuration.FrontDeskConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FrontDeskMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FrontDeskConfiguration.class);
        FrontDesk frontDesk = context.getBean(FrontDesk.class);
        frontDesk.sendMail(new Mail("1234", "US", 1.5));
    }
}
