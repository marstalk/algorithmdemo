package com.marstalk.distributelock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RedLock {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 尝试去获取锁，如果获取不到，则自选，直到超时。
     *
     * @param waitingTime
     */
    public boolean lock(String key, String owner, int expireMillis, int waitingTime) {
        long waitingMillis = waitingTime + System.currentTimeMillis();
        do {
            if (tryLock(key, owner, expireMillis)) {
                //锁上了，则返回该方法，使得该线程能够继续做事情，而不是自旋阻塞。
                return true;
            }
            //获取最新的毫秒值，如果小于等待事件，则继续自选，尝试获取锁。
        } while (waitingMillis > System.currentTimeMillis());

        //超时，则返回false，上锁失败。
        return false;
    }

    /**
     * setnx
     *
     * @return
     */
    public boolean tryLock(String key, String owner, int expireMillis) {
        String command = "return redis.call('set', KEYS[1], ARGV[1], 'PX', 'ARGV[2]', 'NX')";
        DefaultRedisScript<String> script = new DefaultRedisScript<>(command, String.class);
        String result = stringRedisTemplate.execute(script, Collections.singletonList(key), owner, String.valueOf(expireMillis));
        return "OK".equals(result);
    }


    /**
     * lua语法：
     * if ARGV[1] == redis.call('get', KEYS[1]) then
     * return redis.call('del', KEYS[1])
     * else
     * return 0
     * end
     *
     * @param key
     * @param owner
     */
    public boolean unlock(String key, String owner) {
        String command = "if ARGV[1] == redis.call('get', KEYS[1] then return redis.call('del', KEYS[1])) else return 0 end";
        DefaultRedisScript<Integer> script = new DefaultRedisScript<>(command, Integer.class);
        Integer result = stringRedisTemplate.execute(script, Collections.singletonList(key), owner);
        return result == 1;
    }
}
