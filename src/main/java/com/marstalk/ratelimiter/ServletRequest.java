package com.marstalk.ratelimiter;

public class ServletRequest {
    private String msg;

    public ServletRequest(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ServletRequest{" +
                "msg='" + msg + '\'' +
                '}';
    }

}
