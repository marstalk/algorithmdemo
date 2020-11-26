package com.marstalk.concurrent.future;


import javax.script.ScriptException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 这个例子主要展示FutureTask的基本用法
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Boolean> futureTask = new FutureTask<>(new Task());
        new Thread(futureTask).start();
        Boolean result = futureTask.get();
        System.out.println(result);
    }
    static class Task implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            System.out.println("task.call()");
            Thread.sleep(2000);
            return true;
        }
    }
}
