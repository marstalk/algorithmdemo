package com.marstalk.jdk8.completablefuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.testng.annotations.Test;

public class A1CompletaleFuture extends BaseCompletableFutureTest {

    @Test
    public void runAsync() {
        /**
         * runAsync，提交一个没有返回值的任务。 默认使用ForkJoinPool.commonPool()来执行
         */
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            doTask("noReturnValue");
        });
    }

    @Test
    public void runAsyncWithExecutor() throws IOException {
        /**
         * 提交一个没有返回值的任务，使用自定义的executor。 【建议生产代码使用自定义executor】
         */
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            doTask("noReturnValue");
        }, executor);
        sleep(6);
        /**
         * output:<br>
         * myThread0---doing the task noReturnValue <br>
         * myThread0---finish the task noReturnValue <br>
         */
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        /**
         * supplyAsync是有返回值的。<br>
         * 可以指定Executor，<br>
         * 默认使用ForkJoinPool.commonPool()来执行<br>
         */
        CompletableFuture<String> withReturnValue = CompletableFuture.supplyAsync(() -> doTask("withReturnValue"));
        println(withReturnValue.get());
        sleep(6);
        /**
         * ForkJoinPool.commonPool-worker-1---doing the task withReturnValue<br>
         * ForkJoinPool.commonPool-worker-1---finish the task withReturnValue<br>
         * main---[finished]withReturnValue<br>
         */
    }

}
