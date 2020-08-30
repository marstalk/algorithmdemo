package com.marstalk.jdk8.jvm.chapter7;

public class Daughter extends Father{

    static{
        System.out.println("init daughter");
    }

    public static String name = firstName + " lili";
}
