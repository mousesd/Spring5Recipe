package net.homenet.s1;

import java.util.Date;

public class SampleRunnableImpl implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.printf("Hello at %s\n", new Date());
    }
}
