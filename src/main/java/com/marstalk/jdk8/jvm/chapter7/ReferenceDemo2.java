package com.marstalk.jdk8.jvm.chapter7;

/**
 * 被动引用
 * 代码中虽然出现了某个类，但是虚拟机在一下三种情况并不会初始化该类：
 * 1）引用父类静态字段。
 * 2）创建该类的数组的时候
 * 3）引用的是该类的常量：static final
 */
public class ReferenceDemo2 {

    public static void main(String[] args) {
        String name = Son.firstName;

        Son[] sons = new Son[2];

        String str = Son.str;

    }

}
