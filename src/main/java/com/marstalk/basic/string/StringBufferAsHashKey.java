package com.marstalk.basic.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 如果使用mutable的类型作为hashMap或者HashSet的key会怎么样？
 * 下面的例子说明，会照成HashSet无法正常的功能。
 * @author shanzhonglaosou
 */
public class StringBufferAsHashKey {
    public static void main(String[] args) {
        Set<StringBuilder> set = new HashSet<>();
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("abcdef");

        set.add(sb1);
        set.add(sb2);

        sb1.append("def");

        set.forEach(System.out::println);
    }
}
