package net.homenet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("Duplicates")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BookshopConfiguration.class);

        Bookshop bookshop = context.getBean(Bookshop.class);

        //# This problem is known as a dirty read because a transaction may read values that are "dirty"
        //# To avoid the dirty read problem, you should raise the isolation level to READ_COMMITTED
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

        //# This problem is known as a nonrepeatable read because transaction may read different values for the same field.
        //# To avid the nonrepeatable read problem, you should raise the isolation level to REPEATABLE_READ
        //Thread thread1 = new Thread(() -> bookshop.checkStock("0001"), "Thread 1");
        //Thread thread2 = new Thread(() -> {
        //    try {
        //        bookshop.increaseStock("0001", 5);
        //    } catch (RuntimeException ignored) { }
        //}, "Thread 2");
        //
        //thread1.start();
        //try {
        //    Thread.sleep(5000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //thread2.start();
    }
}
