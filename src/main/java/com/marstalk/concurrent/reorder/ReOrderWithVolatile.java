package com.marstalk.concurrent.reorder;

public class ReOrderWithVolatile {
    private int a;

    //使用volatile也可以解决重排序带来的多线程问题，
    //volatile有三个特性：1）内存可见性；2）原子性；3）volatile建立的happens-before。
    //其中第3）点是在JSR-133中新增的。
    private volatile boolean flag;

    public void write() {
        a = 1;

        //因为volatile修饰了flag，那么就建立了一个happens-before：那么简单地说，这行代码之前的所有代码再怎么重排序，也不肯能重排到这行代码之后。
        flag = true;
    }

    public int read() {
        if (flag) {//同样的，这里也是因为volatile的修饰，也建立了happens-before：这行之后的代码再怎么重排序，也不可能重排到这行代码之前。
            return a * 2;
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ReOrderWithVolatile reOrderExample = new ReOrderWithVolatile();
        Thread t1 = new Thread(() -> {
            reOrderExample.write();
        });
        Thread t2 = new Thread(() -> {
            int result = reOrderExample.read();
            System.out.println(result);
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
