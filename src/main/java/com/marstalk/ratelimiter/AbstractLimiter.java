package com.marstalk.ratelimiter;

public abstract class AbstractLimiter {
    protected final int MAX_FLOW;

    public AbstractLimiter(int maxflow) {
        this.MAX_FLOW = maxflow;
    }

    public abstract void limit(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain);

}
