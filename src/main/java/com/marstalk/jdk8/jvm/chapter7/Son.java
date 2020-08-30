package com.marstalk.jdk8.jvm.chapter7;

public class Son extends Father implements Human{

    public static final String str = "static fianl String";

    static{
        System.out.println("son init");
    }

}
