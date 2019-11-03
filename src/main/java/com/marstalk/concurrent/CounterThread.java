package com.marstalk.concurrent;

/**
 * @author Mars
 * Created on 2018/12/24
 */
public class CounterThread implements Runnable {
    private volatile boolean on = true;

    @Override
    public void run() {
        int i = 0;
        while (on && !Thread.currentThread().isInterrupted()) {
            i++;
        }
        System.out.println(i);
    }

    public void cancel() {
        this.on = false;
    }
}
