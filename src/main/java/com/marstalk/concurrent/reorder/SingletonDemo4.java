package com.marstalk.concurrent.reorder;

/**
 * 不用static不能不能实现单例？ 无法。 如果singleton方法是静态的，那么singleton成员必须是静态的 如果singleton方法是动态的，那么则必须使用构造器来，但是跟构造器私有化有冲突
 */
public class SingletonDemo4 {
//    private SingletonDemo4 singletonDemo4;
//
//    private SingletonDemo4() {
//    }
//
//    public static synchronized SingletonDemo4 singleton(){
//        if (null == singletonDemo4) {
//            singletonDemo4 = new SingletonDemo4();
//        }
//        return singletonDemo4;
//    }
}