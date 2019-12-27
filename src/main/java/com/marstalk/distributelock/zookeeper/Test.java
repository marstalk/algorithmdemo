package com.marstalk.distributelock.zookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mars
 * Created on 11/28/2019
 */
public class Test {
    private static int i = 0;
    private static AtomicInteger ai = new AtomicInteger(0);

    private static Lock lock = new ReentrantLock();
    private static int l = 0;

    private static DistributeLock distributeLock = new DistributeLock();

    public static void main(String[] args) throws InterruptedException {
        //100000个线程，对i进行+1，按道理最后，i=100000，但实际上，结果会比100000要小。

        int count = 600;
        CountDownLatch cdl = new CountDownLatch(count);
        for (int j = 0; j < count; j++) {
            new Thread(() -> {

//                //非线程安全代码块
//                i++;
//
//                ai.getAndIncrement();//原子类
//
//                lock.lock();
//                try{
//                    l++;
//                }finally {
//                    lock.unlock();
//                }
                distributeLock.lock();
                try {
                    l++;
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    distributeLock.unlock();
                }
                cdl.countDown();
            }).start();
        }

        cdl.await();
        System.out.println(i);
        System.out.println(ai);
        System.out.println(l);
    }
}
