package com.marstalk.ratelimiter;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器限流，即在单位时间内统计流量，如果超过流量即可返回。
 * 计数器每个单位事件都会重置。
 * 例子中， 流量限制在1秒现在maxFlow。
 *
 * @author shanzhonglaosou
 */
public class CounterLimiter extends AbstractLimiter {
    private static final int INIT_FLOW = 0;
    private final AtomicInteger flow;

    public CounterLimiter(int maxFlow) {
        super(maxFlow);
        flow = new AtomicInteger(INIT_FLOW);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                flow.set(INIT_FLOW);
            }
        }, 0, 1000);
    }

    @Override
    public void limit(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        if (flow.get() < MAX_FLOW) {
            flow.incrementAndGet();
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
