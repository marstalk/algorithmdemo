package com.marstalk.designpattern.singletonPattern;

/**
 * @author shanzhonglaosou
 */
public class SingletonPattern {
    private static volatile SingletonPattern singletonPattern;

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance() {
        if (null == singletonPattern) {
            synchronized (SingletonPattern.class) {
                if (null == singletonPattern) {
                    singletonPattern = new SingletonPattern();
                }
            }
        }
        return singletonPattern;
    }
}
