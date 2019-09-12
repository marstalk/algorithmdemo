package com.marstalk.concurrent.future;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        ArrayList<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Future<String> f = threadPoolExecutor.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    Thread.sleep(200);
//                    Thread.sleep(1000);
                    return "1";
                }
            });

            futures.add(f);
        }


        for (int i = 0; i < 3; i++) {
            System.out.println(futures.get(i).get(1000, TimeUnit.MILLISECONDS));
        }

        System.out.println(2);

    }

}
