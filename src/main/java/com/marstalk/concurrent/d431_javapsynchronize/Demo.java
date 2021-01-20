package com.marstalk.concurrent.d431_javapsynchronize;

import org.openjdk.jol.info.ClassLayout;

/**
 * 这个Demo主要用于演示如何使用jol包（Java object layout）来查看java对象的三个部分的数据：
 * 1，对象头 固定长度 4字节*3
 * 2，示例数据 非固定长度。
 * 3，对齐填充。--可选，当size为8的倍数时，不需要。不为8的倍数时，需要，比如有一个boolan(只占一个字节。)的属性。
 * 一个是为了GC回收效率
 * 一个是为了匹配计算机硬件吧//TODO
 * <p>
 * 依赖jol包。
 */
public class Demo {
    private static MySyn mySyn = new MySyn();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(mySyn).toPrintable());
        synchronized (mySyn) {
            System.out.println(ClassLayout.parseInstance(mySyn).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(mySyn).toPrintable());

        System.out.println(ClassLayout.parseClass(Demo.class).toPrintable());
        synchronized (Demo.class) {
            System.out.println(ClassLayout.parseClass(Demo.class).toPrintable());
        }
        System.out.println(ClassLayout.parseClass(Demo.class).toPrintable());

        //
        System.out.println(System.getProperties());
    }

    private static class MySyn {
        private int i = 996;
        private boolean b = false;
        private String name = "marsdddddddddddddddddddddddddddddddddddddddddddd";
        private Object o = new Object();
    }

}

