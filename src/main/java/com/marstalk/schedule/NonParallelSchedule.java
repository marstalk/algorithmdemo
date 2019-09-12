package com.marstalk.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@EnableAsync
public class NonParallelSchedule {

    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    private static final int count = 100;

    static{
        for (int i = 0; i < count; i++) {
            queue.add(i + "");
        }
        System.out.println("Size of Queue: " + queue.size());
    }

    @Scheduled(fixedRate = 5_000)
    @Async
    public void pollTask() {
        System.out.println("-----------Start new Schedule");
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName() + " Poll from Queue: " + queue.poll());
        }
        System.out.println("------------End Schedule");
    }

    @Scheduled(fixedRate = 1000_000)
    public void offerTask(){
        int index = 0;
        while (index++ < count) {
            queue.add(index + "");
        }
    }
}
