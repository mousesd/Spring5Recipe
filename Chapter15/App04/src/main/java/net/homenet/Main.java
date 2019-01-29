package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class);

        System.out.println("Press [Enter] to close.");
        System.in.read();
        context.close();
    }
}
