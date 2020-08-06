package com.marstalk.jdk8.basic.hashMap;

import java.util.HashMap;

public class EqualsDemo2 {
    public static void main(String[] args) {
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("a", "aValue");
        map1.put("b", "bValue");


        HashMap<Object, Object> map2 = new HashMap<>(33);
        map2.put("a", "aValue");
        map2.put("b", "bValue");

        System.out.println(map1.equals(map2));

        HashMap<Object, Object> map3 = new HashMap<>();
        map3.put("map", map1);


        HashMap<Object, Object> map4 = new HashMap<>();
        map4.put("map", map2);

        System.out.println(map3.equals(map4));
    }
}
