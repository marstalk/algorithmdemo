package com.marstalk.jdk8.bytecode;

import org.openjdk.jol.info.ClassLayout;
import org.testng.annotations.Test;

public class ByteCodeTest {
    @Test
    public void string1(){
        String a = "java";
        String b = "ja" + "va";
        boolean c = a == b;
        System.out.println(c);

        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }

    public void i(){
        int a = 1;
    }
}
