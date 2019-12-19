package com.marstalk.concurrent.d4_daemon;

/**
 * 这个例子说明，守护线程的finally语句是不可靠的。不要依赖守护线程的finally语句做资源关闭的事情。
 * 2019.12.15
 */
public class DaemonFinally {
    public static void main(String[] args) {
        Thread nonDaemonThread = new Thread(() -> {
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("thread finally run, isDaemon " + Thread.currentThread().isDaemon());
            }
        });
        nonDaemonThread.start();

        Thread daemonThread = new Thread(() -> {
            try {
                Thread.sleep(2_000);//睡眠2秒，此时，还有nonDaemonThread在运行，daemon还活着，会执行finaly语句。
//                Thread.sleep(5_000);//睡眠5秒，在第三秒的时候，nonDaemonThread已经terminated，所以，daemonThread也会terminated。并不会睡眠5秒，也不会执行finally语句。
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("daemonThread finally run, isDaemon " + Thread.currentThread().isDaemon());
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();


    }
}
