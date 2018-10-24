package net.homenet.s2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product battery = context.getBean("battery", Product.class);
        Product cdrw = context.getBean("cdrw", Product.class);
        Product dvdrw = context.getBean("dvdrw", Product.class);

        ShoppingCart cart1 = context.getBean(ShoppingCart.class);
        cart1.addItem(battery);
        cart1.addItem(cdrw);
        System.out.println("Shopping cart1 contains " + cart1.getProducts());

        ShoppingCart cart2 = context.getBean(ShoppingCart.class);
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart2 contains " + cart2.getProducts());

        Resource resource = new ClassPathResource("discounts.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);

        System.out.println("And don't forget out discounts!");
        System.out.println(props);
    }
}
