package com.marstalk.concurrent.d432_notifyAndWait;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 就一个题，3个线程，每个线程打印 123，要求，不论3个线程谁先start，输出结果都必然是123，用wait notify
 *
 * @author Mars
 * Created on 12/1/2019
 */
public class WaitAndNotifyDemo1 {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger i = new AtomicInteger(0);
        AtomicBoolean increase = new AtomicBoolean(true);

        new ThreadIncrease(increase, i, lock).start();
        new ThreadTwo(increase, i, lock).start();
        Thread.sleep(1000);
        new ThreadOne(increase, i, lock).start();
        new ThreadThree(increase, i, lock).start();
    }
}

class ThreadOne extends Thread {
    private AtomicInteger i;
    private Object lock;
    private AtomicBoolean increase;

    public ThreadOne(AtomicBoolean increase, AtomicInteger i, Object lock) {
        super("one");
        this.i = i;
        this.lock = lock;
        this.increase = increase;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (i.intValue() != 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + i.intValue());
            increase.set(true);
        }
    }
}


class ThreadTwo extends Thread {
    private AtomicInteger i;
    private Object lock;
    private AtomicBoolean increase;

    public ThreadTwo(AtomicBoolean increase, AtomicInteger i, Object lock) {
        super("two");
        this.i = i;
        this.lock = lock;
        this.increase = increase;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (i.intValue() != 2) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + i.intValue());
            increase.set(true);
        }
    }
}

class ThreadThree extends Thread {
    private AtomicInteger i;
    private Object lock;
    private AtomicBoolean increase;

    public ThreadThree(AtomicBoolean increase, AtomicInteger i, Object lock) {
        super("three");
        this.i = i;
        this.lock = lock;
        this.increase = increase;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (i.intValue() != 3) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + i.intValue());
            increase.set(true);
        }
    }
}

class ThreadIncrease extends Thread {
    private AtomicInteger i;
    private AtomicBoolean increase;
    private Object lock;

    public ThreadIncrease(AtomicBoolean increase, AtomicInteger i, Object lock) {
        super("increase");
        this.i = i;
        this.lock = lock;
        this.increase = increase;
    }

    @Override
    public void run() {
        for (; ; ) {
            synchronized (lock) {
                if (increase.get()) {
                    i.getAndIncrement();
                    increase.set(false);
                }
                lock.notifyAll();
            }
            if (i.intValue() > 3) {
                break;
            }
        }
    }
}

