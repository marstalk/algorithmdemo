package com.marstalk.concurrent.d432_notifyAndWait;

/**
 * 2019.12.15
 * <p>
 * object.wait()
 * object.notify()/notifyAll() 这是个很复杂的，整个过程理解起来还是比较费劲。
 * <p>
 * 伪代码展示使用套路：
 * Object notification = new Object()
 * <p>
 * //消费者
 * method(){
 * synchronized(notification){
 * if(判断条件){//防止维唤醒。
 * notification.wait();//释放锁，进入对待队列，然后进入阻塞队列，等待notify()/notifyAll()，一旦接收到，则竞争锁。
 * }
 * //处理共享变量。
 * }
 * }
 * <p>
 * //生产者
 * method(){
 * synchronized(notification){
 * //处理共享变量。
 * notification.notify();
 * //notification.notifyAll();
 * }
 * }
 */
public class WaitAndNotifyDemo2 {
    public static void main(String[] args) {
        Foo f = new Foo();
    }
}

class Foo {

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
