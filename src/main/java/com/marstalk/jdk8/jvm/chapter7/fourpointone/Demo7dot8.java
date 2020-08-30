package com.marstalk.jdk8.jvm.chapter7.fourpointone;

import java.io.IOException;
import java.io.InputStream;

/**
 * 判断一个对象是不是该类的实例：instanceof， 有一个前提：
 * 同一个加载器加载的
 * 注意这个例子中重新的方法是loadClass而不是findClass。
 * 这两者有很大的区别，请参看Demo7dot9
 *
 */
public class Demo7dot8 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            /**
             *  重写loadClass会破坏双亲委派模型。
             * @param name
             * @return
             * @throws ClassNotFoundException
             */
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
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

        Class<?> aClass = classLoader.loadClass("com.marstalk.jdk8.jvm.chapter7.fourpointone.Demo7dot8");
        Object o = aClass.newInstance();

        System.out.println("自定义加载器的加载器是：" + classLoader.getClass().getClassLoader());
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());

        //自定义加载器不需要做额外的配置，默认的parent class loader 就是AppClassLoader
        System.out.println(o.getClass().getClassLoader().getParent());
        System.out.println(o instanceof com.marstalk.jdk8.jvm.chapter7.fourpointone.Demo7dot8);

    }
}
