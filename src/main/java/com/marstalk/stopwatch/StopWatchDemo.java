package com.marstalk.stopwatch;

import org.springframework.util.StopWatch;

public class StopWatchDemo {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("testId");

        stopWatch.start();
        Thread.sleep(4234);
        stopWatch.stop();

        stopWatch.start();
        Thread.sleep(300);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

    }
}
