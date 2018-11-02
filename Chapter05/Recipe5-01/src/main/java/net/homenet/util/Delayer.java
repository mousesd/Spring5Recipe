package net.homenet.util;

import java.util.Random;

public abstract class Delayer {
    private static final Random random = new Random();

    private Delayer() { }

    public static void delay(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void randomDelay() {
        int delay = random.nextInt(5500);
        delay(delay);
    }
}
