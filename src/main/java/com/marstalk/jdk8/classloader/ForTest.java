package com.marstalk.jdk8.classloader;

import com.marstalk.jdk8.MethodDispatch;

public class ForTest {
    public static ForTest2 forTest2;
    static {
        ForTest2 forTest2 = new ForTest2();
        ForTest.forTest2 = forTest2;
        Object o = new Object();
        System.out.println("Object classloader " + Object.class.getClassLoader());
        MethodDispatch methodDispatch = new MethodDispatch();
        System.out.println("MethodDispatch classloader " + MethodDispatch.class.getClassLoader());
        System.out.println("ForTest2 classloader " + ForTest2.class.getClassLoader());
        System.out.println("init forTest class");
    }
}
