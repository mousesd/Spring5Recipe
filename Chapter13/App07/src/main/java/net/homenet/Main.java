package net.homenet;

import net.homenet.configuration.FileReplicatorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FileReplicatorConfiguration.class);
    }
}
