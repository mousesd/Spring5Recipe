package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("Duplicates")
class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("global", "winter");
        context.scan("net.homenet");
        context.refresh();

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

        Cashier cashier = context.getBean(Cashier.class);
        cashier.checkout(cart1);
        cashier.checkout(cart2);
    }
}
