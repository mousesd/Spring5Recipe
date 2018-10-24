package net.homenet.s3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("Duplicates")
class Main {
    public static void main(String[] args) {
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

        Product discountBattery = context.getBean("discountFactoryBeanAaa", Product.class);
        Product discountCdRw = context.getBean("discountFactoryBeanCdRw", Product.class);
        Product discountDvdRw = context.getBean("discountFactoryBeanDvdRw", Product.class);

        ShoppingCart cart3 = context.getBean(ShoppingCart.class);
        cart3.addItem(discountBattery);
        cart3.addItem(discountCdRw);
        cart3.addItem(discountDvdRw);
        System.out.println("Shopping cart3 contains " + cart3.getProducts());
    }
}
