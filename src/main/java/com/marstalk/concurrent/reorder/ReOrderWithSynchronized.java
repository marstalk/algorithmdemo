package com.marstalk.concurrent.reorder;

public class ReOrderWithSynchronized {
    private int a;
    private boolean flag;

    /**
     * synchronized临界区内，可以进行重排序。
     */
    public synchronized void write() {
        a = 1;
        flag = true;
    }

    /**
     * 使用同一把锁，即可以解决多线程下的问题，也可以继续使用重排序。
     * @return
     */
    public synchronized int read() {
        if (flag) {
            return a * 2;
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ReOrderWithSynchronized reOrderExample = new ReOrderWithSynchronized();
        Thread t1 = new Thread(() -> {
            reOrderExample.write();
        });
        Thread t2 = new Thread(() -> {
            int result = reOrderExample.read();
            System.out.println(result);
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
