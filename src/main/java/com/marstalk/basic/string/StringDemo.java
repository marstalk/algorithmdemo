package com.marstalk.basic.string;

/**
 * 这个好理解，String是immutable的，不可更改。
 * 而且是值传递。
 * @author shanzhonglaosou
 */
public class StringDemo {
    public static void main(String[] args) {
        String abc = "abc";
        appendStr(abc);
        System.out.println(abc);
    }

    private static String appendStr(String a){
        /**
         * 创建了一个新的字符串abcaaa，
         * 然后局部变量指向这个新的字符串。
         */
        a += "aaa";
        return a;
    }
}
