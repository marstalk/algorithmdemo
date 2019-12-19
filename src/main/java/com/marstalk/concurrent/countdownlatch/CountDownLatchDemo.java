package com.marstalk.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Mars
 * Created on 2018/12/23
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        Thread waiterThread = new Thread(waiter, "waiter");
        Thread decrementerThread = new Thread(decrementer, "decrementer");

        waiterThread.start();
        decrementerThread.start();

        Thread.sleep(4000);


    }


}

class Waiter implements Runnable {
    private CountDownLatch countDownLatch;

    public Waiter(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("waiter run()");
    }
}

class Decrementer implements Runnable {
    private CountDownLatch countDownLatch;

    public Decrementer(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        this.countDownLatch.countDown();
        System.out.println("decrementer count" + countDownLatch.getCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.countDownLatch.countDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countDownLatch.getCount());

        this.countDownLatch.countDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countDownLatch.getCount());
        this.countDownLatch.countDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(countDownLatch.getCount());

    }
}