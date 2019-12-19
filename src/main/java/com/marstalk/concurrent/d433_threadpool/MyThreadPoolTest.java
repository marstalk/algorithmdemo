package com.marstalk.concurrent.d433_threadpool;

public class MyThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool tp = new MyThreadPool(5);
        for (int i = 0; i < 100; i++) {
            tp.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("query DB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
