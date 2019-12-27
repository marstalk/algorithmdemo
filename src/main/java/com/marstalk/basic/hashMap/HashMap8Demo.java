package com.marstalk.basic.hashMap;

public class HashMap8Demo {
    public static void main(String[] args) {
        System.out.println(tableSizeFor(5));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1 >>> 16);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
}
