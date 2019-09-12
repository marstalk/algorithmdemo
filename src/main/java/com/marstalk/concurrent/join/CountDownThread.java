package com.marstalk.concurrent.join;

public class CountDownThread extends Thread {
    private int count;
    private Thread before;

    public CountDownThread(String name, int count, Thread before) {
        super(name);
        this.count = count;
        this.before = before;
        System.out.println(name + " is created");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            if (null != before) {
                before.join();
            }
            while (count > 0) {

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + count);
                count--;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
