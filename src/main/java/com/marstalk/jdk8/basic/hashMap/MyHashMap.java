package com.marstalk.jdk8.basic.hashMap;

public class MyHashMap<K, V> implements MyMap<K, V> {


    private Entry<K, V>[] entries;
    private static final int DEFAULT_SIZE = 1 << 4;

    public MyHashMap() {
        this.entries = new Entry[DEFAULT_SIZE];
    }

    @Override
    public void put(K k, V v) {
        int hashCode = k.hashCode();
        int index = hashCode % entries.length - 1;
        if (null != entries[index]) {
            Entry<K, V> preEntry = entries[index];
            entries[index] = new Entry<>(k, v, hashCode, preEntry);
            return;
        }
        entries[index] = new Entry<>(k, v, hashCode, null);
    }

    @Override
    public V get(K k) {
        if (entries.length == 0) {
            return null;
        }
        int hashCode = k.hashCode();
        int index = hashCode % entries.length - 1;
        Entry<K, V> entry = entries[index];
        for (Entry<K, V> e = entry; e != null; e = e.next) {
            if (e.hashCode == hashCode && e.getK().equals(k)) {
                return e.getV();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private class Entry<K, V> {
        private K k;
        private V v;
        private int hashCode;
        private Entry<K, V> next;

        public Entry(K k, V v, int hashCode, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hashCode = hashCode;
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
