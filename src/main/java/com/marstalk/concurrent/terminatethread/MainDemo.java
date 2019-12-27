package com.marstalk.concurrent.terminatethread;

import com.marstalk.concurrent.CounterThread;

import java.util.concurrent.TimeUnit;

/**
 * @author Mars
 * Created on 2018/12/24
 * this demo shows two ways to stop a thread gracefully.
 * 1, interrupt()
 * 2, controll with boolean
 */
public class MainDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new CounterThread(), "counterThread1");
        one.start();
        TimeUnit.SECONDS.sleep(1);
        one.interrupt();

        CounterThread towRunnable = new CounterThread();
        Thread two = new Thread(towRunnable, "counterThread2");
        two.start();
        TimeUnit.SECONDS.sleep(1);
        towRunnable.cancel();
    }
}


