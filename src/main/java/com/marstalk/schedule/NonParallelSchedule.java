package com.marstalk.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@EnableAsync
public class NonParallelSchedule {

    private static final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    private static final int count = 20000;
    private static Lock lock = new ReentrantLock();

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
        int i = 1000;
        List<String> list = new ArrayList<>();
        while (i > 0) {
            i--;
            list.add(queue.poll());
        }

        test(list);

        System.out.println("------------End Schedule");
    }

    private void test(List<String> list) {
        lock.lock();
        for (String str : list) {
            System.out.println(Thread.currentThread().getThreadGroup().getName() + Thread.currentThread().getId() + Thread.currentThread().getName() + " Poll from Queue: " + str);
            try {
                Thread.sleep(240);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock.unlock();

    }

    @Scheduled(fixedRate = 1000_000)
    public void offerTask(){
        int index = 0;
        while (index++ < count) {
            queue.add(index + "");
        }
    }
}
