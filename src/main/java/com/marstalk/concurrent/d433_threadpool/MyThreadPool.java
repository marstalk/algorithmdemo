package com.marstalk.concurrent.d433_threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private LinkedList<Job> jobs = new LinkedList<>();
    private static int WORKER_COUNT_MAX = 10;
    private static int WORKER_COUNT_MIN = 1;
    private AtomicInteger threadId = new AtomicInteger();

    public MyThreadPool(int initialSize) {
        assert initialSize > 0;
        addWorkers(initialSize);
    }

    @Override
    public void submit(Job job) {
        synchronized (jobs) {
            jobs.addLast(job);
            jobs.notify();
            System.out.println(Thread.currentThread().getName() + " submit job " + job + " and notify");
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        if (num + workers.size() > WORKER_COUNT_MAX) {
            System.out.println("exceed thread limitation maximum");
        }
        for (int i = 0; i < num; i++) {
            Thread t = new Thread(new Worker(), "my thread" + threadId.getAndIncrement());
            t.start();
        }
    }

    @Override
    public void removeWorkers(int num) {
        if (workers == null) {
            return;
        }
        if (workers.size() - num < WORKER_COUNT_MIN) {
            System.out.println("exceed thread limitation minimum");
        }
        for (int i = 0; i < num; i++) {
            workers.get(i).shutdown();
        }
    }

    @Override
    public int getJobSize() {
        return 0;
    }

    class Worker implements Runnable {
        private boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " blocked ");
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    System.out.println(Thread.currentThread().getName() + " running job " + job);
                    job.run();
                }
            }
        }

        public void shutdown() {
            this.running = false;
        }
    }

}
