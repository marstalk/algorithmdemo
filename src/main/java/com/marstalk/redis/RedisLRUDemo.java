package com.marstalk.redis;

import java.util.LinkedHashMap;
import java.util.Map;

public class RedisLRUDemo {
    public static void main(String[] args) {
//        System.out.println(Math.ceil(20 / 0.75));

        RedisLRU<String, String> lru = new RedisLRU<>(2);
        for (int i = 0; i < 10; i++) {
            lru.put(i + "", i + "");
            System.out.println(lru.get("0"));
        }
        System.out.println(lru.keySet());
    }
}


class RedisLRU<K, V> extends LinkedHashMap<K, V> {
    private final int cachSize;
    public RedisLRU(int cacheSize) {
        //accessOrder=true: 表示让linkedHashMap按照访问顺序进行排序，最近访问的放在头部，最老访问的放在尾部。
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.cachSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        System.out.println(eldest);
        //当map中的数量大于指定的缓存的个数的时候，就自动删除尾部的数据。
        return size() > cachSize;
    }
}


