package com.marstalk.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZooKeeperDemo1 {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, null);
        final String myPath = "/zookeeperDemo1";

        //create
        zooKeeper.create(myPath, "test data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zooKeeper.getData(myPath, null, null));

        //update
        zooKeeper.setData(myPath, "test data modified".getBytes(), -1);
        System.out.println(zooKeeper.getData(myPath, null, null));

        //delete
        zooKeeper.delete(myPath, -1);
        System.out.println(zooKeeper.exists(myPath, null));

    }

}
