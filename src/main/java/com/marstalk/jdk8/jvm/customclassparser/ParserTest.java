package com.marstalk.jdk8.jvm.customclassparser;

import java.io.IOException;

public class ParserTest {
    public static void main(String[] args) throws IOException {
        ClassParser cp = new ClassParser();
        String path = "/Users/louisliu/IdeaProjects/algorithmdemo/target/classes/com/marstalk/jdk8/jvm/customclassparser/Test2.class";
        Clazz classFile = cp.parse(path);
        System.out.println(classFile);
    }
}
