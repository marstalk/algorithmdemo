package com.marstalk.concurrent.d432_connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {
    private LinkedList<Connection> connections;

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            connections = new LinkedList<>();
            for (int i = 0; i < initialSize; i++) {
                connections.add(ConnectionDriver.getConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (connections) {
            connections.addLast(connection);
            connections.notifyAll();
        }
    }

    public Connection getConnection(long mills) {
        synchronized (connections) {
            if (mills <= 0) {
                //不超时。
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return connections.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                while (connections.isEmpty() && future - System.currentTimeMillis() > 0) {
                    try {
                        connections.wait(mills);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //返回null或者connection
                return connections.pollFirst();
            }
        }
    }
}
