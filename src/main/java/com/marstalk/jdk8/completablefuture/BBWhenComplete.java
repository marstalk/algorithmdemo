package com.marstalk.jdk8newfeature.completablefuture;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 被动处理任务结果：当任务结束或者异常时触发。<br>
 * whenComplete： 同步触发，即触发的方法在任务执行的线程中执行。
 * whenCompleteAsyn：异步触发，即触发的方法在另外的线程中执行。---也可能在同一个线程中，即提交给线程池异步执行可能会是同一个线程。
 * exceptionally
 *
 */
public class BBWhenComplete extends BaseCompletableFutureTest {

    @Test
    public void whenComplete(){

        // 提交一个任务到Executor中，并得到CompletableFuture对象。任务在Executor中执行。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> doTask("taskOne"), executor);

        // 1. whenComplete：当【异步任务】在executor中的某个线程【thread0】正常结束之后，或者有异常，都会触发此方法，而且此方法在【thread0】中执行。
        CompletableFuture<String> future1dot2 = future1.whenComplete((str, throwable) -> println("do more work2 when " + str + " is finished"));
        CompletableFuture<String> future1dot1 = future1.whenComplete((str, throwable) -> println("do more work when " + str + " is finished"));

        // whenComplete处理完之后，会返回一个CompletableFuture对象，他是【异步任务】的结果或者异常。
        // 相当于这个【异步任务】的结果或者异常可以一级一级的传递下去，而不发生改变，而相对的，如果是handle则会发生改变。
//        println(" future1 == future2 ?? " + (future1dot1 == future1));

        println(future1.join());
        println(future1dot1.join());
        println(future1dot2.join());

    }

    @Test
    public void whenCompleteAsync(){
        // 提交一个任务到Executor中，并得到CompletableFuture对象。任务在Executor中执行。
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> doTask("taskOne"), executor);

        // 2. whenComplete：当异步任务在线程thread0正常结束之后，或者有异常，都会触发此方法，且此方法在thread0中执行。同1.
        future1.whenComplete(((aBoolean, throwable) -> System.out.println(Thread.currentThread().getName() + " handle result2 when complete" + aBoolean)));

        // 3. whenComplete：当异步任务在线程thread0正常结束之后，或者有异常，都会触发此方法，且此方法在executor中执行。
        future1.whenCompleteAsync(
                        (aBoolean, throwable) -> System.out.println(Thread.currentThread().getName() + " handle result3 when completeAsync" + aBoolean),
                        executor);

        // 3. whenComplete：当异步任务在线程thread0正常结束之后，或者有异常，都会触发此方法，且此方法在executor中执行。同3
        future1.whenCompleteAsync(
                        (aBoolean, throwable) -> System.out.println(Thread.currentThread().getName() + " handle result4 when completeAsync" + aBoolean),
                        executor);

        future1.exceptionally(throwable -> {
            System.out.println("exception");
            return "false";
        });

    }


    @Test
    public void testWhenCompleteWithExcpetion() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " do something");
            try {
                Thread.sleep(5 * 1000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }, executor);

        future.whenComplete((aBoolean, throwable) -> System.out
                        .println(Thread.currentThread().getName() + " handle result when complete" + aBoolean + throwable.getMessage()));
        future.whenComplete(((aBoolean, throwable) -> System.out
                        .println(Thread.currentThread().getName() + " handle result2 when complete" + aBoolean + throwable.getMessage())));

        future.whenCompleteAsync(
                        (aBoolean, throwable) -> System.out
                                        .println(Thread.currentThread().getName() + " handle result3 when completeAsync" + aBoolean + throwable.getMessage()),
                        executor);
        future.whenCompleteAsync(
                        (aBoolean, throwable) -> System.out
                                        .println(Thread.currentThread().getName() + " handle result4 when completeAsync" + aBoolean + throwable.getMessage()),
                        executor);

        future.exceptionally(throwable -> {
            System.out.println("exception");
            return false;
        });

        System.in.read();
    }
}
