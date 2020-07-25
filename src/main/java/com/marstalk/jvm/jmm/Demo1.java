package com.marstalk.jvm.jmm;

import java.lang.reflect.Field;

/**
 * 这个Demo主要解释，这个jvm内存模型。
 *
 * @author Mars
 * Created on 11/30/2019
 */
public class Demo1 {
    /**
     * static修饰的，说明这是类对象的属性，存放在方法区中，然后因为类型是int（基本类型+reference类型），所以，直接存在的是1
     */
    private static int si = 1;

    /**
     * static修饰的，同上。多一个final，不能被修改。
     */
    private static final int sfi = 2;

    /**
     * static修饰的，说明是类对象的属性，存放在方法区，然后因为是对象，所以存放的是reference类型，值是内存地址。
     * 在类加载的时候，也就是通过收集代码而来创建的<clinit>方法初始化的时候，创建类对象的，会在【堆】中，创建Student对象，并指向这个对象。
     */
    private static Student student = new Student();

    /**
     * static修饰的，说明是类的属性，存放在方法区中，因为是对象，所以存放的是reference类型，值是内存地址。
     * 并没有赋值。所以reference的值是null
     */
    private static Student student2;

    /**
     * 对象属性，类对象Class<Demo1>只存放了相关元数据信息，Field对象是i
     */
    private int i = 3;

    /**
     *
     */
    private String s = "s";
    private Student stu;

    static {
        System.out.println("static block");
    }

    public Demo1() {
        System.out.println("new Object");
    }

    public static void main(String[] args) {

        Class<Demo1> demo1Class = Demo1.class;//触发类加载。
        Field[] declaredFields = demo1Class.getDeclaredFields();//查看类对象的员信息。
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        //
        int a = 3;
        int b = a;

        int c = a + 1;
        int d = a + b;

        Demo1 demo1 = new Demo1();
        Student student = new Student();
    }

}
