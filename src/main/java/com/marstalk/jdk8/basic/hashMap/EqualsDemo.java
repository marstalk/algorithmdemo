package com.marstalk.jdk8.basic.hashMap;

import java.util.HashMap;

public class EqualsDemo {
    public static void main(String[] args) {
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("a", "aValue");
        map1.put("b", "bValue");


        HashMap<Object, Object> map2 = new HashMap<>();
        map2.put("a", "aValue");
        map2.put("b", "bValue");
        map2.put("c", "bValue");

        System.out.println(map1.equals(map2));
    }
}
