package com.marstalk.designpattern.singletonPattern;

/**
 * @author shanzhonglaosou
 */
public class SingletonPattern2 {
    /**
     * 静态成员，在类加载阶段，就会在clinit方法中上锁执行，这过程就包括new SingletonPattern2()，保证了是单例。
     * 这种方式简单，但是如果单例的实例化不仅仅只是通过构造器来实现的，那么则必须使用静态代码块。
     *
     */
    private static SingletonPattern2 singletonPattern = new SingletonPattern2();

    private SingletonPattern2() {
    }

    /**
     * 在调用静态方式时（invokestatic）会先去加载类。而再加载类的过程中，已经把单例new出来了。
     * @return
     */
    public static SingletonPattern2 getInstance() {
        return singletonPattern;
    }
}
