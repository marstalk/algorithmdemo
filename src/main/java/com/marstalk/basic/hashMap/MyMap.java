package com.marstalk.basic.hashMap;

public interface MyMap<K, V> {
    void put(K k, V v);

    V get(K k);

    int size();
}
