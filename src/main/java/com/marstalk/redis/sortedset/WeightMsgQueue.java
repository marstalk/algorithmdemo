package com.marstalk.redis.sortedset;

import redis.clients.jedis.Jedis;

/**
 * 权重消息队列的redis实现<br/>
 * 使用sortedSet的score排序特性：<br/>
 * score of normal message is 1<br/>
 * score of median level message is 2<br/>
 * score of high level message is 3<br/>
 */
public class WeightMsgQueue {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.zadd("msg", 1.0, "hello");
        jedis.zadd("msg", 3.0, "java");
        jedis.zadd("msg", 2.0, "shanzhonglaosou");
        //TODO 继续完成权重消息队列

    }
}
