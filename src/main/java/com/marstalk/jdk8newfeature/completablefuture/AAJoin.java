package com.marstalk.jdk8newfeature.completablefuture;

import java.util.concurrent.CompletableFuture;

import org.testng.annotations.Test;

public class AAJoin extends BaseCompletableFutureTest {

    @Test
    public void join() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> doTask("task1"));

        // join跟get一样会阻塞，但是并不需要捕获checked异常。
        // 获取返回值或者抛出一个uncheckException（CompletionException)
        String result = future.join();
        println(result);

        //output:
        //ForkJoinPool.commonPool-worker-1---doing the task task1
        //ForkJoinPool.commonPool-worker-1---finish the task task1
        //main---[finished]task1
    }

    @Test
    public void joinWithException(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> doTaskWithException("task1"));
        String result = future.join();
        println(result);
        //ForkJoinPool.commonPool-worker-1---doing the task task1
        //java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        //	at java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:273)
        //	at java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:280)
        //	at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1592)
        //	at java.util.concurrent.CompletableFuture$AsyncSupply.exec(CompletableFuture.java:1582)
        //	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
        //	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
        //	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
        //	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
        //Caused by: java.lang.ArithmeticException: / by zero
        //	at com.marstalk.jdk8newfeature.completablefuture.BaseCompletableFutureTest.doTaskWithException(BaseCompletableFutureTest.java:38)
        //	at com.marstalk.jdk8newfeature.completablefuture.AAJoin.lambda$joinWithException$1(AAJoin.java:26)
        //	at java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1590)
        //	... 5 more
    }
}
