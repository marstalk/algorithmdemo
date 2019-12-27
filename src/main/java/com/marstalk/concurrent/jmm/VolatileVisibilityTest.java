package com.marstalk.concurrent.jmm;

/**
 * 这个Demo演示了数据的可见性。
 * Volatile 保证可见性
 *
 * @author Mars
 * Created on 12/2/2019
 */
public class VolatileVisibilityTest {
    private static boolean flag = false;

    /**
     * volatile（底层语言实现-c/汇编）能够保证两件事：
     * 1， 对此修饰符修饰的变量进行【读】取的时候：
     * 如果其他线程有修改过，则从主内存读取。（因为工作内存中的数据，已经失效-->通过总线嗅探机制）
     * 如果其他线程没有修改过，则从工作内存中取。
     * 2， 对此修饰符修饰的变量进行【写】操作的时候：
     * 立刻竞争锁将数据写回到主内存中（lock, store, write, unlock)。
     * 在write的时候会经过总线，会被总线嗅探到。而一旦嗅探到，则将该数据在其他线程的副本设置为过期（失效）。
     * <p>
     * 可以查看这段代码的汇编语言来了解下
     */
//    private static volatile boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "1");
            /**
             * 【不带volatile】
             * !flag这行代码，在底层做了这一串事情：
             *  1， 从线程内存中use,
             *  2， 如果工作内存中没有，则从主内存中read出来load到线程内存, 然后use。
             *
             *  如果flag没有被volatile修饰，则flag变量不会被其他线程过期，会一直在这里循环。
             *  如果flag被volatile修饰，那么工作内存中的flag会被失效，然后从主内存中读取，读取到值为true，停止循环。
             */
            while (!flag) {
            }
            System.out.println(Thread.currentThread().getName() + "2");
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "3");
            /**
             * 【不带volatile】
             * flag = true: 这行代码在底层做了几件事：
             * 1, 从线程内存中取值，没有，则从主内存中取
             * 2, 然后use操作，assign操作，结束后，工作内存中的值，就变成了true。
             * 但是！！线程并不会马上将数据经过总线写回到主内存中，而是继续执行剩余代码，当此方法结束之后才会做这个事情。
             */
            flag = true;
            System.out.println(Thread.currentThread().getName() + "4");

            /**
             * 方法结束后，将线程内存的值store并且write到主内存。
             */
        }).start();

        Thread.sleep(1000);
        new Thread(() -> {
            /**
             * 代码走到这里，主内存中的flag已经被改变为了true。
             * 所有这里不会死循环。
             */
            System.out.println(Thread.currentThread().getName() + "5");
            while (!flag) {
            }
            System.out.println(Thread.currentThread().getName() + "6");
        }).start();


    }
}
