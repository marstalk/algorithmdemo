package com.marstalk.ratelimiter;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Demo {

    @Test
    public void test() {
        Filter filterWithCoutLimiter = new Filter() {
            AbstractLimiter limiter = null;

            @Override
            public void init() {
                limiter = new CounterLimiter(100);
            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
                limiter.limit(request, response, chain);
            }
        };

        filterWithCoutLimiter.init();
        long start = System.currentTimeMillis();
        AtomicInteger integer = new AtomicInteger(0);

        AtomicInteger threadId = new AtomicInteger();
        ExecutorService pool = new ThreadPoolExecutor(10,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                r -> new Thread(r, "myPool-" + threadId.getAndIncrement()));

        IntStream.range(0, 4000).forEach(i -> {
            try {
                /**
                 * 相当于4秒涌入4000个请求。
                 */
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * 交给Tomcat线程池
             */
            pool.execute(() -> filterWithCoutLimiter.doFilter(new ServletRequest(i + ""), new ServletResponse(), (request, response) -> {
                integer.incrementAndGet();
                System.out.println("request: " + request.getMsg() + " pass, execute thread name: " + Thread.currentThread().getName());
            }));
        });

        System.out.println("total elapse: " + (System.currentTimeMillis() - start));
        System.out.println("total passed req: " + integer.get());
    }

}
