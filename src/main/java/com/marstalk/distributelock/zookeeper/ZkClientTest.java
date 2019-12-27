package com.marstalk.distributelock.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author Mars
 * Created on 11/30/2019
 */
public class ZkClientTest {
    public static void main(String[] args) {
        final String testPath = "/test";
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");

        //临时节点在session结束之后，自动删除
        System.out.println("临时节点：/test 是否存在：" + zkClient.exists(testPath));

        //节点不能重复
        zkClient.createEphemeral(testPath);
        try {
            zkClient.createEphemeral(testPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can't create same node");
        }

        //对应命令行 deleteall
        zkClient.deleteRecursive("/hell");
        //有序节点： 先创建夫节点，再父节点之下创建有序节点。/hell/ vs /hell/a
        // /hell/ 节点的名字就是数字递增
        // /hell/a 节点的名字就是a+递增。
        zkClient.createPersistent("/hell");
        for (int i = 0; i < 10; i++) {
            String persistentSequential = zkClient.createPersistentSequential("/hell/", 1);
            System.out.println(persistentSequential);
        }
        System.out.println(zkClient.getChildren("/hell"));
        System.out.println("children count: " + zkClient.countChildren("/hell"));

//        //临时有序节点
//        final String ephemeralDir = "/myEphemeral";
//        zkClient.createEphemeral(ephemeralDir);
//        final String sequencePrefix = "/mySequence";
//        for (int i = 0; i < 10; i++) {
//            String ephemeralSequential = zkClient.createEphemeralSequential(ephemeralDir, 0);
//            System.out.println("创建临时节点：" + ephemeralSequential);
//        }
//
//        System.out.println(zkClient.getChildren(ephemeralDir));

        zkClient.close();
    }
}
