package com.marstalk.jdk8newfeature.completablefuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.testng.annotations.Test;

public class CCHandle extends BaseCompletableFutureTest {
    /**
     * 计算完成时的结果处理： handle handleAsync
     *
     * @throws IOException
     */
    @Test
    public void testHandle() throws IOException {
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " do something");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }, executor);

        // 1. handle相对于
        CompletableFuture<String> handle = future.handle((aBoolean, throwable) -> {
            System.out.println(Thread.currentThread().getName() + " handle result " + aBoolean);
            return "ok";
        });

        handle.whenComplete((s, throwable) -> System.out.println(Thread.currentThread().getName() + " handle result when complete " + s));
        System.in.read();
    }
}
