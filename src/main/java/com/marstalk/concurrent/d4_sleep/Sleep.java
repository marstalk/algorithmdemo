package com.marstalk.concurrent.d4_sleep;

/**
 * 这个例子，是为了证明Thread.sleep不会释放锁。
 * 而相对的，wait()则会释放锁。
 */
public class Sleep {
    public static void main(String[] args) throws InterruptedException {
        MyRun myRun = new MyRun();
        new Thread(myRun, "thread1").start();
        Thread.sleep(1000);
        new Thread(myRun, "thread2").start();
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " begin to sleep");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end sleep");
            }
        }
    }

}
