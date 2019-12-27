package com.marstalk.concurrent.distributeLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DistributeLockTest {
    private static ZookeeperFairLock zkLock = new ZookeeperFairLock();
    private static int i = 0;
    private static Lock rlock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        Thread[] threads = new Thread[count];

        long begin = System.currentTimeMillis();
        for (int j = 0; j < count; j++) {
            Thread t = new Thread(() -> {
//                noLock();
//                tryReentrantLock();
//                tryZKLock();
                tryZkLock();
            });
            threads[j] = t;
            t.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        //竞争同一个节点的情况下（即有惊群效应的情况下）累加20000，花费2分钟
        System.out.println(DistributeLockTest.i + " spends " + (System.currentTimeMillis() - begin));
    }

    private static void tryZkLock() {
        zkLock.lock();
        try {
            DistributeLockTest.i++;
        } finally {
            zkLock.unlock();
        }
    }

    private static void tryReentrantLock() {
        rlock.lock();
        try {
            i++;
        } finally {
            rlock.unlock();
        }
    }

    private static void noLock() {
        i++;
    }

}
