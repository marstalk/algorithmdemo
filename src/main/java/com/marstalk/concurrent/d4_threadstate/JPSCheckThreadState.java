package com.marstalk.concurrent.d4_threadstate;

import java.util.concurrent.locks.LockSupport;

/**
 * 通过jps查看进程pid，然后通过stack pid，就能看到线程的状态。
 * Created by louisliu on 12/23/18.
 */
public class JPSCheckThreadState {

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (true) {
            }
        }, "mars_run").start();//Runnable

        new Thread(() -> {
            LockSupport.park();
        }, "mars_park").start();//WAITING (parking) 需要其他启程通知才能unpark

        synchronized (JPSCheckThreadState.class) {
            new Thread(() -> {
                synchronized (JPSCheckThreadState.class) {
                    System.out.println("hello");
                }
            }, "mars_synchronized").start();//BLOCKED (on object monitor)

            Thread.sleep(60_1000);// 主线程 TIMED_WAITING (sleeping)
        }


        Thread.sleep(600_1000);

    }
}
