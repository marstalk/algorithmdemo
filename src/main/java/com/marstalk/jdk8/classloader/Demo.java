package com.marstalk.jdk8.classloader;

import java.io.IOException;
import java.io.InputStream;

public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String c = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(c);
                    if (resourceAsStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[resourceAsStream.available()];
                    resourceAsStream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        Class<ForTest> aClass = (Class<ForTest>) classLoader.loadClass("com.marstalk.jdk8.classloader.ForTest");
        Object o = aClass.newInstance();
        System.out.println("ForTest classloader " + o.getClass().getClassLoader());
        System.out.println(o instanceof ForTest);
        ForTest forTest = new ForTest();
        System.out.println(forTest instanceof ForTest);
    }
}
