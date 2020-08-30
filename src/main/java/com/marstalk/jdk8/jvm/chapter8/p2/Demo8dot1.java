package com.marstalk.jdk8.jvm.chapter8.p2;

/**
 * 这个例子需要在JVM运行参数加上：
 * -verbose:gc
 */
public class Demo8dot1 {
    public static void main(String[] args) {

        /**
        //1.
        byte[] placeHolder = new byte[64 * 1024 * 1024];

        //GC (System.gc())  69546K->66376K(125952K), 0.0029757 secs]
         //         * [Full GC (System.gc())  66376K->66285K(125952K), 0.0079031 secs]
         //         * 并没有释放空间，
         //         * 这个很好解释，因为这个数组对象还在GCRoot（局部变量）的可抵法范围内。
        System.gc();
        */

        /**
        //2
        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
         //字面上看，placeHolder已经无法再抵达。应该被回收，
        //但是根据规范来说，placeHolder仍然是这个方法的局部变量，这个数组对象仍然可达。
         //在 -xint 解释执行的模式下，这个数组不会回收。
         //但是在JIT模式下，当这段代码被便已成为本地代码之后，这个数组就能被回收了。
        System.gc();
        */


        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();




    }
}
