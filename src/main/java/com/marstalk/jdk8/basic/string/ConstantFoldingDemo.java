package com.marstalk.jdk8.basic.string;

public class ConstantFoldingDemo {
    public static final String abc = "abc";
    private String name;

    public static void main(String[] args) {
        String a = "a";
        String otherA = a;
        System.out.println(a == "a");//true
        System.out.println(a == otherA);

        final String b = "b";
        System.out.println(b == "b");//true
    }
}
