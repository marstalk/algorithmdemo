package com.marstalk.concurrent.reorder;

/**
 * 懒汉模式下使用静态内部类来解决单例问题。
 */
public class SingletonDemo2 {

    private static class SingletonHolder{
        public static SingletonDemo2 instance = new SingletonDemo2();
    }
    private SingletonDemo2(){}
    public static SingletonDemo2 getInstance(){
        /**
         * 多个线程并发问题，但是SingletonHolder只保证会加载一次（JVM通过加锁来实现），而在加载的过程中，
         * 已经把instance实例准备好了。
         */
        return SingletonHolder.instance;
    }
    
}
