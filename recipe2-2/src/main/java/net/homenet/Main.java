package net.homenet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context
            = new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product battery = context.getBean("getBattery", Product.class);
        Product disc = context.getBean("getDisc", Product.class);

        System.out.println(battery);
        System.out.println(disc);
    }
}
