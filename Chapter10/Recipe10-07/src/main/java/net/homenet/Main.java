package net.homenet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookshopConfiguration.class);

        Bookshop bookshop = context.getBean(Bookshop.class);
        Thread thread1 = new Thread(() -> {
            try {
                bookshop.increaseStock("0001", 5);
            } catch (RuntimeException ignored) { }
        }, "Thread 1");
        Thread thread2 = new Thread(() -> bookshop.checkStock("0001"), "Thread 2");

        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}
