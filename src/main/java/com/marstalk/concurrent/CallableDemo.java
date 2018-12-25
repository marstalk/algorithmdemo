package com.marstalk.concurrent;

import java.util.concurrent.Callable;

/**
 * @author Mars
 * Created on 2018/12/19
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        MyCallable myCallable = new MyCallable();
        String call = myCallable.call();

        MyCallable myCallable2 = new MyCallable();
        String call2 = myCallable2.call();

        System.out.println(1);
        System.out.println(call);
        System.out.println(2);
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getId() + " is runing");
        int index = 0;
        while (index < 10) {
            Thread.sleep(1000);
            System.out.println(index++);
        }
        return "result from myCallable";
    }
}


