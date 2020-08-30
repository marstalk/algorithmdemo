package com.marstalk.jdk8.jvm.chapter7.fourpointone;

import java.io.IOException;
import java.io.InputStream;

/**
 * 判断一个对象是不是该类的实例：instanceof， 有一个前提：
 * 同一个加载器加载的
 *
 * 这个例子对比Demo7dot8有一个明显不同的地方，也是唯一的地方：
 * 自定义加载器重写的是findClass方法。
 * 这个方法只有在父类加载器加载失败的情况下才会去调用。这是推荐的使用方式。
 * 使得自定义类加载器仍然符合双亲委派模型。
 *
 */
public class Demo7dot9 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String className = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(className);
                    if (null == resourceAsStream) {
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

        Class<?> aClass = classLoader.loadClass("com.marstalk.jdk8.jvm.chapter7.fourpointone.Demo7dot9");
        Object o = aClass.newInstance();

        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());
        System.out.println(o instanceof Demo7dot9);

    }
}
