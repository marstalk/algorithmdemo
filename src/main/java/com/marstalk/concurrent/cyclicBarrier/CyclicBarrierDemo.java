package com.marstalk.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 顾名思义是可以重复使用的屏障。
 * 构造器一定要传入一个该数字，cyclicBarrier每调用一次await()方法，则会减一，同时调用线程会不会阻塞完全取决于：
 * 该数字是否已经被见到了0，
 * 如果=0，那么所有的线程从await方法返回，继续执行。
 * 同时重置该数字。
 *
 * CyclicBarrier第二个构造参数是一个runnable，是由第一个调用await的线程来执行的。
 * @author Mars
 * Created on 2018/12/23
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,
                () -> System.out.println(Thread.currentThread().getName() + " " + "3"));

        new Thread(()->{
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + "1");
        }, "t1").start();

        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " " + "2");


        new Thread(()->{
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + "4");
        }, "t2").start();

        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + " " + "5");
    }
}


