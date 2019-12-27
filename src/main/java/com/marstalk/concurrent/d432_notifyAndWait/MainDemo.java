package com.marstalk.concurrent.d432_notifyAndWait;

/**
 * @author Mars
 * Created on 2018/12/19
 */
public class MainDemo {

    private static Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            System.out.println("begin to wait");
            try {
                o.wait();
                System.out.println("after wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();

        for (int i = 0; i < 3; i++) {
            System.out.println("main thread count before notify " + i);
            Thread.sleep(1000);
        }

        o.notify();

        for (int i = 0; i < 3; i++) {
            System.out.println("main thread count after notify " + i);
            Thread.sleep(1000);
        }

    }

}
