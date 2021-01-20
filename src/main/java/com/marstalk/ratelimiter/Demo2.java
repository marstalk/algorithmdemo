package com.marstalk.ratelimiter;

import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Demo2 {

    @Test
    public void test() {
        /**
         * 1, 模拟用户请求6000个，1毫秒一个请求的速率请求（这种情况比较理想化，有待提高）//TODO
         * 2, 使用线程池来模拟servlet容器。在filter中中使用CounterLimiter进行计数拦截，fail-fast
         * 3, 统计通过的流量
         */

        //TODO 区分是线程池abort还是limiter block
        ExecutorService executorService = new ThreadPoolExecutor(10,
                10,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(4000));

        IntStream.range(0, 6000).forEach(i -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });
    }
}
