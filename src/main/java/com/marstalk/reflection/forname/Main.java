package com.marstalk.reflection.forname;

import java.util.Map;

/**
 * @author marstalk: jiacheng524@live.cn
 * @date 2018-06-19 20:33:18
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class hashMap = Class.forName("java.util.HashMap");
        Map<String, String> testMap = (Map<String, String>) hashMap.newInstance();
        testMap.put("key", "value");
        System.out.println(testMap);


        hashMap.getDeclaredConstructors();
    }
}
