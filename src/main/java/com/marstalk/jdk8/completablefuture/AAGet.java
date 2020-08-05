package com.marstalk.jdk8newfeature.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.testng.annotations.Test;

/**
 * 主动完成计算，意思是通过主动去获取计算结果。<br>
 *     当主动去获取的时候，会存在任务尚未完成的情况。<br>
 */
public class AAGet extends BaseCompletableFutureTest {

    @Test
    public void get() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> doTask("taskOne"), executor);
        try {
            /**
             * 这个任务需要3秒才能够完成，所以这里会阻塞，直到任务完成。<br>
             * 需要捕捉两个异常InterruptedException和ExecutionException
             */
            String str = supplyAsync.get();
            println(Thread.currentThread().getName() + " get result " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        println(Thread.currentThread().getName());
        /**
         * output: <br>
         * myThread0---doing the task taskOne<br>
         * myThread0---finish the task taskOne<br>
         * main---main get result [finished]taskOne<br>
         * main---main<br>
         */
    }

    @Test
    public void getWithException() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> doTaskWithException("taskOne"));
        try {
            /**
             * 这个任务会因为被除数是0而抛出运行时异常。
             */
            String str = supplyAsync.get();
            println(Thread.currentThread().getName() + " get result " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            println(Thread.currentThread().getName() + " get ExecutionException " + e.getMessage());
        }
        println(Thread.currentThread().getName() + Thread.currentThread().getId());
        /**
         * output: <br>
         * ForkJoinPool.commonPool-worker-1---doing the task taskOne <br>
         * main---main get ExecutionException java.lang.ArithmeticException: / by zero <br>
         * main---main1 <br>
         */
    }

    @Test
    public void testGetNowWithDefaultValue() {
        //提交一个需要3秒才能完成的任务。
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> doTask("taskOne"));

        //getNow不会阻塞，因为此刻任务尚未完成，所以获得是"This is Defaultvalue"
        String str = supplyAsync.getNow("This is DefaultValue");
        println(str);
        /**
         * output: <br>
         * main---This is DefaultValue <br>
         * ForkJoinPool.commonPool-worker-1---doing the task taskOne <br>
         */
    }

    @Test
    public void testGetNow() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> doTask("taskOne"));

        //主线程睡眠6秒，保证任务已经结束了。
        sleep(6);

        //此刻任务已经结束，str则是任务的计算结果。
        String str = supplyAsync.getNow("This is DefaultValue");
        println(str);
        /**
         * output: <br>
         * ForkJoinPool.commonPool-worker-1---doing the task taskOne
         * ForkJoinPool.commonPool-worker-1---finish the task taskOne
         * main---[finished]taskOne
         */
    }

    @Test
    public void testGetNowWithException() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> doTaskWithException("taskOne"));

        //主线程睡眠6秒，保证让任务线程触发了ArithmeticException
        sleep(6);

        //这个时候，任务已经因为异常而结束了，getNow方法会抛出java.util.concurrent.CompletionException。
        String str = supplyAsync.getNow("This is DefaultValue");
        println(str);

        /**
         * ForkJoinPool.commonPool-worker-1---doing the task taskOne
         * java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
         * 	at java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:273)
         * 	at java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:280)
         * 	at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1592)
         * 	at java.util.concurrent.CompletableFuture$AsyncSupply.exec(CompletableFuture.java:1582)
         * 	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
         * 	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
         * 	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
         * 	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
         * Caused by: java.lang.ArithmeticException: / by zero
         * 	at com.marstalk.jdk8newfeature.completablefuture.BaseCompletableFutureTest.doTaskWithException(BaseCompletableFutureTest.java:38)
         * 	at com.marstalk.jdk8newfeature.completablefuture.AAGet.lambda$testGetNowWithException$3(AAGet.java:84)
         * 	at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1590)
         * 	... 5 more
         */
    }

}
