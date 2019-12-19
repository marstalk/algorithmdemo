package com.marstalk.concurrent.d433_threadpool;

public interface ThreadPool<Job extends Runnable> {
    /**
     * 提交一个runnable给线程池执行。
     *
     * @param job
     */
    void submit(Job job);

    /**
     * 关闭线程池。
     */
    void shutdown();

    /**
     * 增加线程池中的线程数量
     *
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少线程池中的线程数量
     *
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 获取当前正在排队的Job的总量
     *
     * @return
     */
    int getJobSize();

}
