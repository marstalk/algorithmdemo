package com.marstalk.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>这个Demo演示了corePoolSize, blockingQueue, maximumPoolSize之间的关系
 * <p>一开始，新的任务被提交到线程池后，线程池会创建新的线程来执行这些任务，直到线程的数量等于corePoolSize;
 * <li>可以提前把corePoolSize的线程创建好：executorService.prestartCoreThread();</li>
 * <p>当corePoolSize的线程都被征用之后，开始把新加入的任务放到blockingQueue中。
 * <p>当BlockingQueue被任务填满之后，线程池创建新的线程来执行后续新加入的任务，直到线程总数等于maximumPoolSize为止。
 * <p>如果继续有新的任务submit，则会被RejectHandler处理。
 * @author louisl
 */
public class FixedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger ai = new AtomicInteger();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(15, true), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my-threadPool-thread" + ai.getAndIncrement());
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("reject " + r.toString());
            }
        });
        executorService.prestartCoreThread();

        AtomicInteger a = new AtomicInteger();
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                    System.out.println("pool size: " + executorService.getPoolSize());
                    System.out.println("queue remains: " + executorService.getQueue().remainingCapacity());
                    System.out.println("threadName: " + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadName: " + Thread.currentThread().getName() + " task ends" + a.getAndIncrement());
            });
        }
    }
}
