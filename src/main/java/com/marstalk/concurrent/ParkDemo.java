package com.marstalk.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by louisliu on 11/2/19.
 */
public class ParkDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

                LockSupport.park();

            }
        };
    }

}
