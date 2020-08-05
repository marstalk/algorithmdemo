package com.marstalk.jdk8.basic.hashMap;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

/**
 * https://stackoverflow.com/questions/35838739/linkedhashmap-ordering
 */
public class LinkedHashMapDemo7 {

    /**
     * LinkedList是有顺序的
     */
    @Test
    public void accessOrderTrue() {
        //accessOrder = false
        //意思是insertionOrder（put方法决定了顺序，多次put不影响第一次put定下来的顺序）
        LinkedHashMap<String, String> m = new LinkedHashMap<>();
        m.put("1", "A");
        m.put("2", "B");
        m.put("3", "C");
        System.out.println(m);

        //get对顺序没有影响
        m.get("2");
        System.out.println(m);

        //第二次put对原本的顺序没有影响
        m.put("2", "BB");
        System.out.println(m);

    }

    @Test
    public void accessOrderFalse() {
        //accessOrder = true
        //accessOrder = access,
        //意思是get会影响顺序，会把对应的值移动到末尾
        //第二次put同一个key，也会影响顺序，对对应的值移动到末尾。
        //意思是队列头部的第一个元素是LRU。这是myBatis中的LRUCache的基本原理（再配合removeEldestEntry来使用）
        LinkedHashMap<String, String> m = new LinkedHashMap<>(8, 0.75F, true);
        m.put("1", "A");
        m.put("2", "B");
        m.put("3", "C");
        System.out.println(m);

        //对顺序有影响，"2"移动到了末尾
        m.get("2");
        System.out.println(m);

        //put对顺序有影响，"1"移动到了末尾。
        m.put("1", "AA");
        System.out.println(m);
    }

}
