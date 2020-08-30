package com.marstalk.concurrent.reorder;

/**
 * 饿汉模式下来解决单例问题。
 */
public class SingletonDemo3 {
    private static SingletonDemo3 singletonDemo3 = new SingletonDemo3();

    public static SingletonDemo3 getInstance(){
        /**
         * 多个线程并发问题，但是SingletonDemo3只保证会加载一次（JVM通过加锁来实现），而在加载的过程中，
         * 已经把instance实例准备好了。
         */
        return singletonDemo3;
    }
    
}
