package com.marstalk.algorithmdemo.consistenthash;

import java.util.Map;
import java.util.TreeMap;

/**
 * Dubbo中的一致性hash负载均衡中，
 * 使用了TreeMap的ceilingEntry特性，找到了invoker
 *
 */
public class CeilingMap {

    public static void main(String[] args) {
        TreeMap<Integer, String> m = new TreeMap<>();
        m.put(2, "two");
        m.put(4, "four");
        m.put(6, "six");
        m.put(8, "eight");
        System.out.println(m.ceilingEntry(3));

    }
}
