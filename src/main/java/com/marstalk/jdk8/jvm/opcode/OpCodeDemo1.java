package com.marstalk.jdk8.jvm.opcode;

public class OpCodeDemo1 {
    public static void main(String[] args) {
        String name = "louisl";
        String a = greeting(name);
    }

    private static String greeting(String name) {
        System.out.println("Hello, " + name);
        return name + " hello;";
    }


}
