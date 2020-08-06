package com.marstalk.jdk8.basic.hashMap;

import java.util.HashMap;
import java.util.Map;

public class MyHashMapTest {

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
        System.out.println(map.get("s"));
        map.put("name", "mars");
        System.out.println(map.get("name"));
        System.out.println("my".hashCode() % 16);

        System.out.println("mysf".hashCode() % 16);
        map.put("mysf", "mysf");
        System.out.println(map.get("mysf"));

        System.out.println("mydd".hashCode() % 16);
        map.put("mydd", "mydd");
        System.out.println(map.get("mydd"));

        System.out.println("mycc".hashCode() % 16);
        map.put("mycc", "mycc");
        System.out.println(map.get("mycc"));

        System.out.println("myee".hashCode() % 16);
        map.put("myee", "myee");
        System.out.println(map.get("myee"));

        System.out.println("myaa".hashCode() % 16);
        map.put("myaa", "myaa");
        System.out.println(map.get("myaa"));

        System.out.println("myfd".hashCode() % 16);

        Map m = new HashMap();
        m.put("a", "c");
    }

}
