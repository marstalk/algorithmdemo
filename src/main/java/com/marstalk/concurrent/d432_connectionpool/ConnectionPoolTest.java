package com.marstalk.concurrent.d432_connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static int threadCount = 1000;
    static int eachThreadCount = 10;

    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end = new CountDownLatch(threadCount);

    static AtomicInteger gotCount = new AtomicInteger();
    static AtomicInteger notGotCount = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < threadCount; i++) {
            new Thread(new ConnRunner(eachThreadCount, gotCount, notGotCount)).start();
        }
        start.countDown();

        end.await();
        System.out.println("total invoke " + threadCount * eachThreadCount);
        System.out.println("got counts " + gotCount.get());
        System.out.println("not got counts " + notGotCount);

    }

    static class ConnRunner implements Runnable {
        private int count;
        private AtomicInteger gotCount;
        private AtomicInteger notGotCount;


        public ConnRunner(int count, AtomicInteger gotCount, AtomicInteger notGotCount) {
            this.count = count;
            this.gotCount = gotCount;
            this.notGotCount = notGotCount;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < count; i++) {
                Connection connection = pool.getConnection(0);
                if (null == connection) {
                    notGotCount.getAndIncrement();
                } else {
                    try {
                        connection.createStatement();
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        pool.releaseConnection(connection);
                        gotCount.getAndIncrement();
                    }
                }
            }
            end.countDown();
        }
    }

}
