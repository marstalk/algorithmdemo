package com.marstalk.concurrent.reorder;

public class SingletonDemo {
    private static SingletonDemo instance;
    private SingletonDemo(){}
    public SingletonDemo getInstance(){
        if (null == instance) {//线程二走到这里，可能会拿到一个尚未初始化好的对象。
            synchronized (SingletonDemo.class) {
                if (null == instance) {
                    /**
                     * 这行代码可能会被初始化
                     */
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
