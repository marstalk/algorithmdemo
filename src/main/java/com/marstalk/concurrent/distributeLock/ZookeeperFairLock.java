package com.marstalk.concurrent.distributeLock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用zookeeper的几个特性实现分布式锁：
 * 1，同一个节点下，只能有一个同名节点。
 * 2，zkClient可以创建临时节点，如果连接失效，则目录自动删除。
 * 3，zkClient可以监听目录的事件。
 * 4，zkClient可以创建有序的临时节点。
 * <p>
 * //第一步，实现非公平锁。
 */
public class ZookeeperFairLock implements Lock {
    private final String lockPath = "/lock2";
    private ZkClient zkClient;
    private String lockNode;

    public ZookeeperFairLock() {
        zkClient = new ZkClient("localhost:2181");
        try {
            zkClient.createPersistent(lockPath);
        } catch (Exception e) {
            log(lockPath + "already exists");
        }
    }

    /**
     * 尝试获取锁，如果获取不到，则进入队列
     */
    @Override
    public void lock() {
        acquired();
    }


    private void acquired() {

        String node = null;
        for (; ; ) {
            //监听节点。
            if (node == null) {
                node = zkClient.createEphemeralSequential(lockPath + "/", 0);
            }
            List<String> children = zkClient.getChildren(lockPath);
            log("all nodes " + children);
            if (children.size() == 1) {
                lockNode = node;
                log(" lock success when there is only one node");
                return;
            }

            Collections.sort(children);
            String currentNodeName = node.substring(node.lastIndexOf("/") + 1);
            log("sorted nodes " + children + " current node: " + currentNodeName);
            int i = Collections.binarySearch(children, currentNodeName);
            log("index is " + i);
            if (i == 0) {
                lockNode = node;
                log(currentNodeName + " lock success when node is head");
                return;
            }
            String preNodeName = children.get(i - 1);

            CountDownLatch cdl = new CountDownLatch(1);
            IZkDataListener listener = new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                    //do nothing
                    log("dateChange " + s);
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    log("dataDeleted " + s);
                    cdl.countDown();
                    log("count down");
                }
            };
            String preNode = lockPath + "/" + preNodeName;
            log("watching node: " + preNode);
            zkClient.subscribeDataChanges(preNode, listener);

            //如果前节点已经释放，那么当前节点获得锁。
            if (!zkClient.exists(preNode)) {
                zkClient.unsubscribeDataChanges(preNode, listener);
                lockNode = node;
                log(currentNodeName + " lock success when preNode is already deleted");
                return;
            } else {
                try {
                    log("blocked");
                    cdl.await();
                    log("continue after await");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 尝试获取锁，如果获取到，则返回true，否则返回false
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        String node = zkClient.createEphemeralSequential(lockPath + "/", 0);
        log(" create nodeSeq " + node);
        if (zkClient.countChildren(lockPath) == 1) {
            lockNode = node;
            log("lock success when there is only one node" + node);
            return true;
        } else {
            List<String> nodeList = zkClient.getChildren(lockPath);
            Collections.sort(nodeList);
            log(nodeList + " " + node);
            if ((lockPath + "/" + nodeList.get(0)).equalsIgnoreCase(node)) {
                lockNode = node;
                log("lock success2 when currentNode is head2 " + node);
                return true;
            }
        }

        log("try lock fail " + node);
        zkClient.delete(node);
        return false;
    }

    /**
     * 释放锁。
     */
    @Override
    public void unlock() {
        boolean result = zkClient.delete(lockNode);
        log(" delete " + lockNode + " unlock success " + result);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " " + msg);
    }
}
