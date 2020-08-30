package com.marstalk.concurrent.d432_notifyAndWait;

public class WaitAndNotifyDemo3 {
    private static Object obj = new Object();
    private static int A = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t3 = new Thread(()->{
            synchronized (obj){
                while (A == 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " msg is null, wait()");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " A == 1, handle message");
            }
        }, "t3");
        Thread t4 = new Thread(()->{
            synchronized (obj){
                A = 1;
                System.out.println(Thread.currentThread().getName() + " sent message");
                obj.notify();
            }
        }, "t4");

        t3.start();
        Thread.sleep(1000);//休息一秒，保证t3读取了共享变量到工作内存中，但是因为A==0，进入wait。

        t4.start();//将共享变量A设置为1，然后notify，因为只有t3在wait队列中，那么t3会被挪到阻塞队列中，
                   // 接着t4释放锁，
                    //t3获得锁，读取本地内存中的A副本，仍然是0，怎么办？因为synchronized具有volatile的语义。
    }
}
