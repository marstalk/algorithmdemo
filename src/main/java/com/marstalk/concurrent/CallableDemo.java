package com.marstalk.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Mars
 * Created on 2018/12/19
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        MyCallable myCallable = new MyCallable();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(myCallable);

        System.out.println("mainThread..");
        System.out.println(future.get());
        System.out.println("mainThread 2 ..");
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


