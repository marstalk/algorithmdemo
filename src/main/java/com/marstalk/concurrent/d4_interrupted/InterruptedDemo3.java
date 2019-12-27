package com.marstalk.concurrent.d4_interrupted;

/**
 *
 */
public class InterruptedDemo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread busyThread = new Thread(() -> {
            long i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                /**
                 * 线程要自己检查中断标志位。
                 * 如果不检查，那么其他线程标记上也没有什么用。
                 */
                i++;
            }
            /**
             * 线程中断后，有机会完成一些事情。这样比stop()方法要好很多。
             */
            System.out.println(i);
        }, "sleepThread");
        busyThread.start();

        Thread.sleep(1000);

        System.out.println("interrupt busy thread");
        busyThread.interrupt();

        Thread.sleep(4_000);


    }
}
