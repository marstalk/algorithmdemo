package com.marstalk.concurrent.d4_interrupted;

/**
 *
 */
public class InterruptedDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("sleepThread finish sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
                //【许多】方法再抛出InterruptedException之后，会被虚拟机清除标志位。
                System.out.println("sleepThread is interrupted, interrupted status " + Thread.currentThread().isInterrupted());
            }
        }, "sleepThread");
        sleepThread.start();

        Thread.sleep(1000);

        System.out.println("interrupt sleep thread");
        sleepThread.interrupt();

        Thread.sleep(4_000);


    }
}
