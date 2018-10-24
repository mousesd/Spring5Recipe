package net.homenet.s1;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorSample {
    public static void main(String[] args) throws Throwable {
        Runnable task = new SampleRunnableImpl();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        if (cachedThreadPool.submit(task).get() == null) {
            System.out.printf("The cachedThreadPool has succeeded at %s\n", new Date());
        }

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        if (fixedThreadPool.submit(task).get() == null) {
            System.out.printf("The fixedThreadPool has succeeded at %s\n", new Date());
        }

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        if (singleThreadPool.submit(task).get() == null) {
            System.out.printf("The singleThreadPool has succeeded at %s\n", new Date());
        }

        if (cachedThreadPool.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE)) {
            System.out.println("Job has finished!");
        }

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        if (scheduledThreadPool.schedule(task, 30, TimeUnit.SECONDS).get() == null) {
            System.out.printf("The scheduleThreadPool has succeed at %s\n", new Date());
        }

        scheduledThreadPool.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}
