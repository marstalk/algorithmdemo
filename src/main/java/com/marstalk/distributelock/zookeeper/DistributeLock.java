package com.marstalk.distributelock.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * zookeeper实现的公平队列。
 *
 * @author Mars
 * Created on 11/28/2019
 */
public class DistributeLock implements Lock {
    private ZkClient zkClient;
    private static String lockPath = "/distributeLock";

    public DistributeLock() {
        zkClient = new ZkClient("127.0.0.1:2181");
        if (!zkClient.exists(lockPath)) {
            zkClient.createPersistent(lockPath);
        }
    }

    @Override
    public void lock() {
        if (!tryLock()) {
            blockAndWatch();
            lock();
        }
        System.out.println(Thread.currentThread().getId() + " 获得分布式锁");
    }

    @Override
    public boolean tryLock() {

        //创建临时节点，为什么是临时呢，
        // 当我们的程序因为各种原因挂掉之后，zookeeper会自动删除该节点，并发出事件，让等待线程可以继续。
        //从而不会造成死锁。
        String currentNode = zkClient.createEphemeralSequential(lockPath + "/", 996);

        List<String> children = zkClient.getChildren(lockPath);
        Collections.sort(children);
        if (currentNode.equalsIgnoreCase(children.get(0))) {
            //当前线程创建的节点，是最小的，获得锁。
            return true;
        } else {
            //获取上一次节点
//            int index = Collections.binarySearch(children, currentNode);
//            preNode = lockPath + "/" + children.get(index - 1);
            return false;
        }
    }

    private void blockAndWatch() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + " deleted");
                countDownLatch.countDown();
            }
        };
        zkClient.subscribeDataChanges(lockPath, listener);
        //监听完之后，再去判断文件是否
        //线程走到这里，可能锁已经释放了。所以要在判断一次。
        if (!tryLock()) {
            try {
                countDownLatch.await();
                //唤醒之后。继续线程running
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        zkClient.unsubscribeDataChanges(lockPath, listener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        boolean delete = zkClient.delete(lockPath);
        System.out.println("unlock: " + delete);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

//    public static void main(String[] args) {
//        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
////        String s = zkClient.create("/louisl", 33, CreateMode.PERSISTENT);
////        boolean delete = zkClient.delete("/louisl");
////        System.out.println(delete);
//        zkClient.createEphemeral(path, 99);
//        zkClient.createEphemeral(path, 99);
//    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        zkClient.close();
    }
}
