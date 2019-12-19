package com.marstalk.concurrent.jmm;

/**
 * volatile 不保证原子性
 *
 * @author Mars
 * Created on 12/3/2019
 */
public class VolatileAtmoicTest {

    private static int i = 0;

    private static void increase() {
        i++;
    }

//    private static synchronized void increase(){
//        i++;
//    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    increase();
                }
            });
            thread.start();
            threads[j] = thread;
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(i);
    }

}
