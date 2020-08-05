package com.marstalk.jdk8.completablefuture;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.BeforeClass;

public class BaseCompletableFutureTest {
    private static AtomicInteger a = new AtomicInteger();
    ThreadPoolExecutor executor;

    @BeforeClass
    public void beforeClass() {
        executor = new ThreadPoolExecutor(4, 4, 1000, TimeUnit.MINUTES, new LinkedBlockingQueue<>(5),
                        r -> new Thread(null, r, "myThread" + a.getAndIncrement()));
    }

    protected String doTask(String taskName) {
        println("doing the task " + taskName);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("finish the task " + taskName);
        return "[finished]" + taskName;
    }

    protected String doTaskWithException(String taskName) {
        println("doing the task " + taskName);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1 / 0;
        println("finish the task " + taskName);
        return "[exception]" + taskName;
    }

    protected void println(String str) {
        System.out.println(Thread.currentThread().getName() + "---" + str);
    }

    protected void sleep(int second){
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
