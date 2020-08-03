package com.marstalk.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockJUC {

    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {

            lock1.tryLock();
            try{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lock();
                try{
                    System.out.println(Thread.currentThread().getName());
                }finally {
                    lock2.unlock();
                }
            }finally {
                lock1.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock2.lock();
            try{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lock();
                try{
                    System.out.println(Thread.currentThread().getName());
                }finally {
                    lock1.unlock();
                }
            }finally {
                lock2.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
