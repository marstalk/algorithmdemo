package com.marstalk.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by louisliu on 11/2/19.
 *
 * CAS: compare and swap:
 * 比较交换，意思是我手里有两张牌，A牌和B牌，如果你手中的牌跟我的B牌一样，那我就把A牌给你，你的牌变成A，结束；
 * 如果你的牌跟我的B牌不一样，那我等你，直到你的牌跟我的B牌一样，然后把A牌给你，你的牌变成A，结束。
 * 1， 这中间会有一个不限时间长度的等待过程，
 * 2， 比较牌和换牌的这两个动作，要保证原子性，就是说，你服务我的时候，只能服务我一个人，不能同时服务多个人。
 * @author louisl
 */
public class CustomLock {
    /**
     * volatile: 这个关键字，保证内存可见性，什么意思呢？
     * 1：locked
     * 0：unlocked
     */
    volatile int status=0;

    public void lock(){
        // 自旋
        while (!compareAndSet(0, 1)) {
            //不断循环，直到status被赋值为1。
            /**
             * 缺点：CPU一直在占用。
             * 让得不到锁得线程让出CPU。
             * wait： 的目的是线程通信，与Synchronize一起使用，并不是用来做阻塞。
             * yield：可以让出CPU，但是有个缺点：就是某个线程让出CPU之后，仍然又可能被CPU调用。
             * sleep：时间固定，时间如何设置？
             * park：
             */
        }
    }

    /**
     * 如果status的值跟expecct一样，那么将status的值设置为newValue，
     * 注意：这里必须保证原子操作。
     * JDK 已经帮我们实现了。
     * @param expect
     * @param newValue
     * @return
     */
    private boolean compareAndSet(int expect, int newValue) {
        AtomicInteger ai = new AtomicInteger(status);
        return ai.compareAndSet(expect,  newValue);
    }

    public void unlock(){
        while(!compareAndSet(1, 0)){
            /**
             * 缺点：谁都可以关闭这个锁，
             */
        }
    }


    public static void main(String[] args) {

    }

}
