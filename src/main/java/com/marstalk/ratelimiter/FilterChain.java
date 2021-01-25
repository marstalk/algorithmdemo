package com.marstalk.ratelimiter;

public interface FilterChain {

    void doFilter(ServletRequest request, ServletResponse response);

}
