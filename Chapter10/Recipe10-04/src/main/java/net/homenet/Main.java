package net.homenet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookshopConfiguration.class);

        Bookshop bookshop = context.getBean(Bookshop.class);
        bookshop.purchase("0001", "user1");
    }
}
