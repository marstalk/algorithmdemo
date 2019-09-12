package com.marstalk.concurrent.join;


public class MainDemo {

    /**
     * Thread are three Threads: T1 T2 T3, make sure T3 is run after T2, and T2 is run after T1.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new CountDownThread("T1", 5, null);
        Thread T2 = new CountDownThread("T2", 5, T1);
        Thread T3 = new CountDownThread("T3", 5, T2);

        T1.start();
        T2.start();
        T3.start();

        T3.join();
    }

}
