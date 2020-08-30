package com.marstalk.jdk8.basic.hashMap;

import java.util.HashMap;

//write your own linkedHashMap
public class MyLinkedHashMap extends HashMap {
    static class Node implements HashMap.Entry {
        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }
    }

}
