package net.homenet;

import net.homenet.configuration.BackOfficeConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackOfficeMain {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(BackOfficeConfiguration.class);
    }
}
