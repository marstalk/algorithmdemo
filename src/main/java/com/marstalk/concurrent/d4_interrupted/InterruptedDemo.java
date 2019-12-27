package com.marstalk.concurrent.d4_interrupted;

/**
 * 这个用来测试interrupted的功能和特性。
 * 注：t1是一个线程实例。
 * t1.interrupt(): 给线程1加上中断标志。
 * t1.isInterrupted(): clearState = false, 不会清理标志。
 * Thread.interrupted(): clearState = true， 清理标志。
 * <p>
 * 以下代码就是验证这个的，其实可以直接看源码，就是知道了。
 *
 */
public class InterruptedDemo {
    public static void main(String[] args) {
        //TODO
        Thread.currentThread().interrupt();
        System.out.println("t1.isInterrupted(): " + Thread.currentThread().isInterrupted());
        System.out.println("t1.isInterrupted(): " + Thread.currentThread().isInterrupted());

        System.out.println("Thread.interrupted(): " + Thread.interrupted());
        System.out.println("Thread.interrupted(): " + Thread.interrupted());
        /**
         * t1.isInterrupted(): true
         * t1.isInterrupted(): true
         * Thread.interrupted(): true
         * Thread.interrupted(): false
         */
    }
}
