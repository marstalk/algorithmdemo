package com.marstalk.concurrent.lock;

public class DeadLockSynchronized {

    private static DeadLockSynchronized lock1 = new DeadLockSynchronized();
    private static DeadLockSynchronized lock2 = new DeadLockSynchronized();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
