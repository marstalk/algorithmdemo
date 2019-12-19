package com.marstalk.concurrent.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 提交一个任务(Callable/Runnable)给线程池，如果在在运行过程抛异常了，会是怎么样一种情况
 */
public class ThreadPoolWithException {
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100));

    public static void main(String[] args) throws InterruptedException {

        threadPool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " running without try catch");
            int abc = Integer.parseInt("abc");
            System.out.println(Thread.currentThread().getName() + " finish");
        });

        Thread.sleep(1000);
        System.out.println("threadPool core size " + threadPool.getCorePoolSize());
        System.out.println(Thread.currentThread().getName() + " finish sleep 1 second");

        threadPool.submit(() -> {
            System.out.println("running with try catch");
            try {
                int abc = Integer.parseInt("abc");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish");
        });

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " finish");
        System.out.println("threadPool core size " + threadPool.getCorePoolSize());
    }

}
